package com.ledgerx.account.boundary.http;

import com.ledger.core.boundary.http.BaseController;
import com.ledgerx.account.boundary.api.AccountApi;
import com.ledgerx.account.boundary.dto.AccountDto;
import com.ledgerx.account.boundary.dto.CreateAccountRequest;
import com.ledgerx.account.boundary.dto.UpdateAccountRequest;
import com.ledgerx.account.control.AccountService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Tag(name = "Accounts", description = "Account management operations")
public class AccountController extends
    BaseController<Long, AccountDto, CreateAccountRequest, UpdateAccountRequest> implements
    AccountApi {

  @Inject
  private AccountService service;

  // No-args constructor for CDI
  public AccountController() {
    super();
  }

  public AccountController(AccountService service) {
    super(service);
  }

  @Override
  protected void setService(com.ledger.core.control.BaseService<?, Long, CreateAccountRequest, UpdateAccountRequest, AccountDto> service) {
    super.setService(service);
  }

  @PostConstruct
  public void init() {
    // Set the service after injection
    setService(service);
  }
}
