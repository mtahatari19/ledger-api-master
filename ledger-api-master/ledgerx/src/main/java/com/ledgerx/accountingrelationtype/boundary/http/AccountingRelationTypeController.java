package com.ledgerx.accountingrelationtype.boundary.http;

import com.ledger.core.boundary.http.BaseController;
import com.ledgerx.accountingrelationtype.boundary.api.AccountingRelationTypeApi;
import com.ledgerx.accountingrelationtype.boundary.dto.AccountingRelationTypeDto;
import com.ledgerx.accountingrelationtype.boundary.dto.CreateAccountingRelationTypeRequest;
import com.ledgerx.accountingrelationtype.boundary.dto.UpdateAccountingRelationTypeRequest;
import com.ledgerx.accountingrelationtype.control.AccountingRelationTypeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/accounting-relation-types")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Tag(name = "Accounting Relation Types", description = "Accounting relation type management operations")
public class AccountingRelationTypeController extends
    BaseController<Long, AccountingRelationTypeDto, CreateAccountingRelationTypeRequest, UpdateAccountingRelationTypeRequest>
    implements AccountingRelationTypeApi {

  @Inject
  private AccountingRelationTypeService service;

  // No-args constructor for CDI
  public AccountingRelationTypeController() {
    super();
  }

  public AccountingRelationTypeController(AccountingRelationTypeService service) {
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