package com.ledgerx.accountGroup.boundary.api;

import com.ledger.core.boundary.api.BaseApi;
import com.ledgerx.accountGroup.boundary.dto.AccountGroupDto;
import com.ledgerx.accountGroup.boundary.dto.CreateAccountGroupRequest;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Account Groups", description = "Account group management operations")
public interface AccountGroupApi extends
    BaseApi<Long, AccountGroupDto, CreateAccountGroupRequest, CreateAccountGroupRequest> {

}
