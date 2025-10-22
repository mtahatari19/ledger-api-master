package com.ledgerx.accountingrelationtype.boundary.api;

import com.ledger.core.boundary.api.BaseApi;
import com.ledgerx.accountingrelationtype.boundary.dto.AccountingRelationTypeDto;
import com.ledgerx.accountingrelationtype.boundary.dto.CreateAccountingRelationTypeRequest;
import com.ledgerx.accountingrelationtype.boundary.dto.UpdateAccountingRelationTypeRequest;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Accounting Relation Types", description = "Accounting relation type management operations")
public interface AccountingRelationTypeApi extends
    BaseApi<Long, AccountingRelationTypeDto, CreateAccountingRelationTypeRequest, UpdateAccountingRelationTypeRequest> {
  // CRUD operations are inherited from BaseApi
}
