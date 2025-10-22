#!/usr/bin/env bash
set -euo pipefail

# Colors
RED='\033[0;31m'; GREEN='\033[0;32m'; YELLOW='\033[1;33m'; BLUE='\033[0;34m'
PURPLE='\033[0;35m'; CYAN='\033[0;36m'; WHITE='\033[1;37m'; BOLD='\033[1m'
DIM='\033[2m'; NC='\033[0m'

: "${BASE_URL:=http://localhost:8081/api/v1}"
: "${TOKEN:=}"
TIMEOUT=30

HDR=(-H "Content-Type: application/json")
[ -n "$TOKEN" ] && HDR+=(-H "Authorization: Bearer $TOKEN")

# IDs
CURRENCY_ID=""; ORG_ID=""; PARTY_TYPE_ID=""; PARTY_ID=""
GOODS_GROUP_ID=""; GOODS_ID=""; PRODUCT_ID=""; PLAN_ID=""
ACCOUNT_TYPE_CASH_ID=""; ACCOUNT_TYPE_CUST_ID=""
ACCOUNT_CASH_ID=""; ACCOUNT_CUST_ID=""

hr(){ printf "%s\n" "────────────────────────────────────────────────────────"; }
banner(){ echo -e "\n${CYAN}╔══════════════════════════════════════════════════════════════════════════════╗${NC}\n${CYAN}║${WHITE} $1${CYAN}${NC}\n${CYAN}╚══════════════════════════════════════════════════════════════════════════════╝${NC}\n"; }
section(){ echo -e "\n${PURPLE}╔══════════════════════════════════════════════════════════════════════════════╗${NC}\n${PURPLE}║${WHITE} $1${PURPLE}${NC}\n${PURPLE}╚══════════════════════════════════════════════════════════════════════════════╝${NC}\n"; }
pp(){ jq -C . 2>/dev/null || cat; }

# Show all entities for a given type
show_entities() {
  local endpoint="$1"
  local type_name="$2"
  local key_field="$3"
  
  echo -e "${BLUE}📋 EXISTING ${type_name}S:${NC}" >&2
  local entities
  entities=$(curl -s "${HDR[@]}" "$BASE_URL/$endpoint" | jq -r ".content[] | \"  ID: \(.id), ${key_field}: \(.[\"$key_field\"])\"" 2>/dev/null || echo "  None found")
  echo "$entities" >&2
  echo "" >&2
}

# Check if entity exists and return ID
check_entity_exists() {
  local endpoint="$1"
  local field="$2"
  local value="$3"
  local entity_name="$4"
  local key_field="$5"
  
  # Show existing entities first
  show_entities "$endpoint" "$entity_name" "$key_field"
  
  echo -e "${BLUE}▶ CHECKING${NC} if $entity_name $value already exists..." >&2
  
  local existing_id
  existing_id=$(curl -s "${HDR[@]}" "$BASE_URL/$endpoint" | jq -r ".content[] | select(.$field==\"$value\") | .id" 2>/dev/null || echo "")
  
  if [ -n "$existing_id" ]; then
    echo -e "${YELLOW}⚠️  $entity_name $value already exists with ID: $existing_id${NC}" >&2
    echo "$existing_id"
  else
    echo -e "${GREEN}✅ $entity_name $value not found, will create new one${NC}" >&2
    echo ""
  fi
}

# Safe post function that doesn't exit on error
safe_post_id(){
  local path="$1"; local body="$2"; local desc="$3"
  local url="$BASE_URL$path"
  echo -e "${BLUE}▶ REQUEST${NC} POST $url" >&2
  [ -n "$body" ] && echo "$body" | pp >&2
  echo "" >&2
  
  local tmp; tmp="$(mktemp)"
  local status time
  status_and_time=$(curl -s -S -o "$tmp" "${HDR[@]}" -X POST "$url" -d "$body" \
                    -w "%{http_code} %{time_total}" --connect-timeout "$TIMEOUT" 2>/dev/null || echo "000 0.0")
  read -r status time <<<"$status_and_time"

  if [[ "$status" =~ ^2 ]]; then
    echo -e "${GREEN}◀ RESPONSE${NC} (status: $status, time: ${time}s)" >&2
    cat "$tmp" | pp >&2; echo "" >&2
    
    # Extract ID
    local id
    if id=$(cat "$tmp" | jq -er '.id | tostring' 2>/dev/null); then
      echo "$id"
    else
      echo -e "${RED}❌ FAIL${NC} - Could not extract id from response" >&2
      cat "$tmp" | pp >&2
      echo ""
    fi
  else
    echo -e "${RED}❌ FAIL${NC} - $desc (status: $status, time: ${time}s)" >&2
    if jq -e . >/dev/null 2>&1 <"$tmp"; then
      cat "$tmp" | pp >&2
    else
      cat "$tmp" >&2
    fi
    echo "" >&2
    echo ""
  fi
  rm -f "$tmp"
}

# Safe get function
safe_get_by_id(){
  local prefix="$1"; local id="$2"; local label="$3"
  if [ -z "$id" ]; then
    echo -e "${YELLOW}⚠️  Skipping GET for ${label} — empty id${NC}" >&2
    return 0
  fi
  
  local url="$BASE_URL$prefix/$id"
  echo -e "${BLUE}▶ REQUEST${NC} GET $url" >&2
  
  local tmp; tmp="$(mktemp)"
  local status time
  status_and_time=$(curl -s -S -o "$tmp" "${HDR[@]}" -X GET "$url" \
                    -w "%{http_code} %{time_total}" --connect-timeout "$TIMEOUT" 2>/dev/null || echo "000 0.0")
  read -r status time <<<"$status_and_time"

  if [[ "$status" =~ ^2 ]]; then
    echo -e "${GREEN}◀ RESPONSE${NC} (status: $status, time: ${time}s)" >&2
    cat "$tmp" | pp >&2; echo "" >&2
  else
    echo -e "${RED}❌ FAIL${NC} - get ${label} (status: $status, time: ${time}s)" >&2
    if jq -e . >/dev/null 2>&1 <"$tmp"; then
      cat "$tmp" | pp >&2
    else
      cat "$tmp" >&2
    fi
    echo "" >&2
  fi
  rm -f "$tmp"
}

# ===== CLEANUP FUNCTION =====
cleanup_all_data() {
  echo -e "${RED}🧹 CLEANING UP ALL EXISTING DATA...${NC}"
  
  # Get all entities and delete them in reverse dependency order
  echo -e "${BLUE}📋 Getting all existing entities...${NC}"
  
  # Get all accounts
  ACCOUNTS=$(curl -s "${HDR[@]}" "$BASE_URL/accounts" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$ACCOUNTS" ]; then
    count=$(echo "$ACCOUNTS" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count accounts...${NC}"
    for account_id in $ACCOUNTS; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/accounts/$account_id" >/dev/null 2>&1 || true
    done
  fi
  
  # Get all account types
  ACCOUNT_TYPES=$(curl -s "${HDR[@]}" "$BASE_URL/account-types" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$ACCOUNT_TYPES" ]; then
    count=$(echo "$ACCOUNT_TYPES" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count account types...${NC}"
    for account_type_id in $ACCOUNT_TYPES; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/account-types/$account_type_id" >/dev/null 2>&1 || true
    done
  fi
  
  # Get all plans
  PLANS=$(curl -s "${HDR[@]}" "$BASE_URL/plans" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$PLANS" ]; then
    count=$(echo "$PLANS" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count plans...${NC}"
    for plan_id in $PLANS; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/plans/$plan_id" >/dev/null 2>&1 || true
    done
  fi
  
  # Get all products
  PRODUCTS=$(curl -s "${HDR[@]}" "$BASE_URL/products" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$PRODUCTS" ]; then
    count=$(echo "$PRODUCTS" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count products...${NC}"
    for product_id in $PRODUCTS; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/products/$product_id" >/dev/null 2>&1 || true
    done
  fi
  
  # Get all goods
  GOODS=$(curl -s "${HDR[@]}" "$BASE_URL/goods" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$GOODS" ]; then
    count=$(echo "$GOODS" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count goods...${NC}"
    for goods_id in $GOODS; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/goods/$goods_id" >/dev/null 2>&1 || true
    done
  fi
  
  # Get all goods groups
  GOODS_GROUPS=$(curl -s "${HDR[@]}" "$BASE_URL/goods-groups" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$GOODS_GROUPS" ]; then
    count=$(echo "$GOODS_GROUPS" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count goods groups...${NC}"
    for goods_group_id in $GOODS_GROUPS; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/goods-groups/$goods_group_id" >/dev/null 2>&1 || true
    done
  fi
  
  # Get all parties
  PARTIES=$(curl -s "${HDR[@]}" "$BASE_URL/parties" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$PARTIES" ]; then
    count=$(echo "$PARTIES" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count parties...${NC}"
    for party_id in $PARTIES; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/parties/$party_id" >/dev/null 2>&1 || true
    done
  fi
  
  # Get all party types
  PARTY_TYPES=$(curl -s "${HDR[@]}" "$BASE_URL/party-types" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$PARTY_TYPES" ]; then
    count=$(echo "$PARTY_TYPES" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count party types...${NC}"
    for party_type_id in $PARTY_TYPES; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/party-types/$party_type_id" >/dev/null 2>&1 || true
    done
  fi
  
  # Get all organizational units
  ORG_UNITS=$(curl -s "${HDR[@]}" "$BASE_URL/organizational-units" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$ORG_UNITS" ]; then
    count=$(echo "$ORG_UNITS" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count organizational units...${NC}"
    for org_unit_id in $ORG_UNITS; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/organizational-units/$org_unit_id" >/dev/null 2>&1 || true
    done
  fi
  
  # Get all currencies
  CURRENCIES=$(curl -s "${HDR[@]}" "$BASE_URL/currencies" | jq -r '.content[] | .id' 2>/dev/null || echo "")
  if [ -n "$CURRENCIES" ]; then
    count=$(echo "$CURRENCIES" | wc -l)
    echo -e "${YELLOW}🗑️  Deleting $count currencies...${NC}"
    for currency_id in $CURRENCIES; do
      curl -s -X DELETE "${HDR[@]}" "$BASE_URL/currencies/$currency_id" >/dev/null 2>&1 || true
    done
  fi
  
  echo -e "${GREEN}✅ Cleanup completed! All existing data has been deleted.${NC}"
  echo ""
}

# ===== FLOW =====
banner "LedgerX OpenAPI-Aligned GL Demo"

# Clean up all existing data first
cleanup_all_data

section "Create: Currency (IRR)"
read -r -d '' BODY <<'JSON' || true
{
  "currencyCode":"IRR",
  "currencyNumCode":364,
  "swiftCode":"IRR",
  "currencyName":"ریال ایران",
  "symbol":"﷼",
  "decimalPrecision":0
}
JSON

CURRENCY_ID=$(check_entity_exists "currencies" "currencyCode" "IRR" "Currency" "currencyCode")
if [ -z "$CURRENCY_ID" ]; then
  CURRENCY_ID="$(safe_post_id "/currencies" "$BODY" "create currency")"
fi
echo -e "   ${DIM}id=${CURRENCY_ID}${NC}"

section "Create: Organizational Unit (HQ)"
read -r -d '' BODY <<'JSON' || true
{
  "organizationCode":"HQ",
  "organizationName":"دفتر مرکزی",
  "parentOrganizationId":null,
  "description":"مرکز اصلی",
  "status":"ACTIVE"
}
JSON

ORG_ID=$(check_entity_exists "organizational-units" "orgUnitCode" "HQ" "Organizational unit" "orgUnitCode")
if [ -z "$ORG_ID" ]; then
  ORG_ID="$(safe_post_id "/organizational-units" "$BODY" "create org unit")"
fi
echo -e "   ${DIM}id=${ORG_ID}${NC}"

section "Create: Party Type (CUSTOMER)"
read -r -d '' BODY <<'JSON' || true
{
  "typeCode":"CUSTOMER",
  "typeName":"مشتری",
  "description":"طرف حساب مشتری",
  "status":"ACTIVE"
}
JSON

PARTY_TYPE_ID=$(check_entity_exists "party-types" "typeCode" "CUSTOMER" "Party type" "typeCode")
if [ -z "$PARTY_TYPE_ID" ]; then
  PARTY_TYPE_ID="$(safe_post_id "/party-types" "$BODY" "create party type")"
fi
echo -e "   ${DIM}id=${PARTY_TYPE_ID}${NC}"

section "Create: Party (customer A)"
read -r -d '' BODY <<JSON || true
{
  "externalId":"CUST-12345",
  "partyTypeId":"$PARTY_TYPE_ID"
}
JSON

PARTY_ID=$(check_entity_exists "parties" "externalId" "CUST-12345" "Party" "externalId")
if [ -z "$PARTY_ID" ]; then
  PARTY_ID="$(safe_post_id "/parties" "$BODY" "create party")"
fi
echo -e "   ${DIM}id=${PARTY_ID}${NC}"

section "Create: Goods Group (Bank Services)"
read -r -d '' BODY <<'JSON' || true
{
  "groupCode":"BANK-SERV",
  "groupName":"خدمات بانکی - گروه کالای حسابداری",
  "status":"ACTIVE"
}
JSON

GOODS_GROUP_ID=$(check_entity_exists "goods-groups" "groupCode" "BANK-SERV" "Goods group" "groupCode")
if [ -z "$GOODS_GROUP_ID" ]; then
  GOODS_GROUP_ID="$(safe_post_id "/goods-groups" "$BODY" "create goods group")"
fi
echo -e "   ${DIM}id=${GOODS_GROUP_ID}${NC}"

section "Create: Goods (Bank Fee)"
read -r -d '' BODY <<JSON || true
{
  "goodsCode":"BANK-SERV-001",
  "goodsName":"کارمزد خدمات بانکی - کالای حسابداری",
  "goodsGroupId":"$GOODS_GROUP_ID",
  "status":"ACTIVE"
}
JSON

GOODS_ID=$(check_entity_exists "goods" "goodsCode" "BANK-SERV-001" "Goods" "goodsCode")
if [ -z "$GOODS_ID" ]; then
  GOODS_ID="$(safe_post_id "/goods" "$BODY" "create goods")"
fi
echo -e "   ${DIM}id=${GOODS_ID}${NC}"

section "Create: Product (Credit Purchase)"
read -r -d '' BODY <<'JSON' || true
{
  "productCode":"CREDIT-001",
  "productName":"خرید اعتباری",
  "description":"محصول مالی برای خرید اعتباری با تسویه دوره‌ای",
  "status":"ACTIVE"
}
JSON

PRODUCT_ID=$(check_entity_exists "products" "productCode" "CREDIT-001" "Product" "productCode")
if [ -z "$PRODUCT_ID" ]; then
  PRODUCT_ID="$(safe_post_id "/products" "$BODY" "create product")"
fi
echo -e "   ${DIM}id=${PRODUCT_ID}${NC}"

section "Create: Plan (Teachers Credit 2%)"
read -r -d '' BODY <<JSON || true
{
  "product":{
    "id": $PRODUCT_ID
  },
  "planCode":"CULTURAL-CREDIT-001",
  "planName":"طرح اعتباری فرهنگیان (۲٪ کارمزد)",
  "status":"ACTIVE"
}
JSON

PLAN_ID=$(check_entity_exists "plans" "planCode" "CULTURAL-CREDIT-001" "Plan" "planCode")
if [ -z "$PLAN_ID" ]; then
  PLAN_ID="$(safe_post_id "/plans" "$BODY" "create plan")"
fi
echo -e "   ${DIM}id=${PLAN_ID}${NC}"

section "Create: AccountType (CASH / ASSET / internal)"
read -r -d '' BODY <<JSON || true
{
  "typeCode":"CASH",
  "typeName":"حساب نقدی - دفتر کل",
  "description":"حساب داخلی صندوق/بانک برای ثبت دریافت/پرداخت نقدی",
  "accountGroup":"ASSET",
  "isInternal":true,
  "allowGoodsSubAccounts":false,
  "status":"ACTIVE",
  "allowedCurrencyIds":["$CURRENCY_ID"],
  "allowedOrgUnitIds":["$ORG_ID"]
}
JSON

ACCOUNT_TYPE_CASH_ID=$(check_entity_exists "account-types" "typeCode" "CASH" "Account type" "typeCode")
if [ -z "$ACCOUNT_TYPE_CASH_ID" ]; then
  ACCOUNT_TYPE_CASH_ID="$(safe_post_id "/account-types" "$BODY" "create account type (cash)")"
fi
echo -e "   ${DIM}id=${ACCOUNT_TYPE_CASH_ID}${NC}"

section "Create: AccountType (CUSTOMER_ACCOUNT / LIABILITY)"
read -r -d '' BODY <<JSON || true
{
  "typeCode":"CUSTOMER_ACCOUNT",
  "typeName":"حساب مشتری - نوع حساب بدهی",
  "description":"تعهدات مشتری در خرید اعتباری",
  "accountGroup":"LIABILITY",
  "isInternal":false,
  "allowGoodsSubAccounts":true,
  "status":"ACTIVE",
  "allowedCurrencyIds":["$CURRENCY_ID"],
  "allowedOrgUnitIds":["$ORG_ID"],
  "allowedPartyTypeIds":["$PARTY_TYPE_ID"]
}
JSON

ACCOUNT_TYPE_CUST_ID=$(check_entity_exists "account-types" "typeCode" "CUSTOMER_ACCOUNT" "Account type" "typeCode")
if [ -z "$ACCOUNT_TYPE_CUST_ID" ]; then
  ACCOUNT_TYPE_CUST_ID="$(safe_post_id "/account-types" "$BODY" "create account type (customer)")"
fi
echo -e "   ${DIM}id=${ACCOUNT_TYPE_CUST_ID}${NC}"

section "Create: Account (CASH)"
read -r -d '' BODY <<JSON || true
{
  "codeNumeric":1001,
  "codeString":"CASH-001",
  "titleKey":"account.cash.title",
  "typeCode":"CASH",
  "typeName":"حساب نقدی - دفتر کل",
  "organizationId":"$ORG_ID",
  "currencyId":"$CURRENCY_ID",
  "isInternal":true,
  "accountTypeId":"$ACCOUNT_TYPE_CASH_ID",
  "partyId":null,
  "planId":null,
  "parentAccountId":null,
  "balance":1000000.0,
  "status":"ACTIVE"
}
JSON

ACCOUNT_CASH_ID=$(check_entity_exists "accounts" "codeString" "CASH-001" "Account" "codeString")
if [ -z "$ACCOUNT_CASH_ID" ]; then
  ACCOUNT_CASH_ID="$(safe_post_id "/accounts" "$BODY" "create cash account")"
fi
echo -e "   ${DIM}id=${ACCOUNT_CASH_ID}${NC}"

section "Create: Account (Customer Liability)"
read -r -d '' BODY <<JSON || true
{
  "codeNumeric":210001,
  "codeString":"CUST-ACC-0001",
  "titleKey":"account.customer.liability",
  "typeCode":"CUSTOMER_ACCOUNT",
  "typeName":"حساب بدهی مشتری",
  "organizationId":"$ORG_ID",
  "currencyId":"$CURRENCY_ID",
  "isInternal":false,
  "accountTypeId":"$ACCOUNT_TYPE_CUST_ID",
  "partyId":"$PARTY_ID",
  "planId":"$PLAN_ID",
  "parentAccountId":null,
  "balance":0.0,
  "status":"ACTIVE"
}
JSON

ACCOUNT_CUST_ID=$(check_entity_exists "accounts" "codeString" "CUST-ACC-0001" "Account" "codeString")
if [ -z "$ACCOUNT_CUST_ID" ]; then
  ACCOUNT_CUST_ID="$(safe_post_id "/accounts" "$BODY" "create customer liability account")"
fi
echo -e "   ${DIM}id=${ACCOUNT_CUST_ID}${NC}"

section "GET spot checks"
safe_get_by_id "/currencies" "$CURRENCY_ID" "currency"
safe_get_by_id "/organizational-units" "$ORG_ID" "org unit"
safe_get_by_id "/accounts" "$ACCOUNT_CASH_ID" "cash account"
safe_get_by_id "/accounts" "$ACCOUNT_CUST_ID" "customer account"

section "Final Summary"
echo -e "${GREEN}🎯 FINAL ENTITY SUMMARY:${NC}"
echo -e "  Currency ID: ${CURRENCY_ID:-'NOT CREATED'}"
echo -e "  Org Unit ID: ${ORG_ID:-'NOT CREATED'}"
echo -e "  Party Type ID: ${PARTY_TYPE_ID:-'NOT CREATED'}"
echo -e "  Party ID: ${PARTY_ID:-'NOT CREATED'}"
echo -e "  Goods Group ID: ${GOODS_GROUP_ID:-'NOT CREATED'}"
echo -e "  Goods ID: ${GOODS_ID:-'NOT CREATED'}"
echo -e "  Product ID: ${PRODUCT_ID:-'NOT CREATED'}"
echo -e "  Plan ID: ${PLAN_ID:-'NOT CREATED'}"
echo -e "  Account Type Cash ID: ${ACCOUNT_TYPE_CASH_ID:-'NOT CREATED'}"
echo -e "  Account Type Cust ID: ${ACCOUNT_TYPE_CUST_ID:-'NOT CREATED'}"
echo -e "  Account Cash ID: ${ACCOUNT_CASH_ID:-'NOT CREATED'}"
echo -e "  Account Cust ID: ${ACCOUNT_CUST_ID:-'NOT CREATED'}"

echo -e "\n${GREEN}✅ Script completed successfully!${NC}"
