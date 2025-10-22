package com.ledgerx.account.boundary.api;

import com.ledger.core.boundary.api.BaseApi;
import com.ledgerx.account.boundary.dto.AccountDto;
import com.ledgerx.account.boundary.dto.CreateAccountRequest;
import com.ledgerx.account.boundary.dto.UpdateAccountRequest;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Accounts", description = "Account management operations")
public interface AccountApi extends
    BaseApi<Long, AccountDto, CreateAccountRequest, UpdateAccountRequest> {

}
