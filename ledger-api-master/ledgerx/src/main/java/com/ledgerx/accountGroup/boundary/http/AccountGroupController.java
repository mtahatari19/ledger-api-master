package com.ledgerx.accountGroup.boundary.http;

import com.ledger.core.boundary.http.BaseController;
import com.ledgerx.accountGroup.boundary.api.AccountGroupApi;
import com.ledgerx.accountGroup.boundary.dto.AccountGroupDto;
import com.ledgerx.accountGroup.boundary.dto.CreateAccountGroupRequest;
import com.ledgerx.accountGroup.control.AccountGroupService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/account-groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Tag(name = "Account Groups", description = "Account group management operations")
public class AccountGroupController extends
    BaseController<Long, AccountGroupDto, CreateAccountGroupRequest, CreateAccountGroupRequest>
    implements AccountGroupApi {

  @Inject
  AccountGroupService service;

  // No-args constructor for CDI
  public AccountGroupController() {
    super();
  }

  public AccountGroupController(AccountGroupService service) {
    super(service);
  }

  @PostConstruct
  public void init() {
    // Set the service after injection
    if (service != null) {
      setService(service);
    }
  }

}
