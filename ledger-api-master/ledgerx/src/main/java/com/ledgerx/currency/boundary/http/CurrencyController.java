package com.ledgerx.currency.boundary.http;

import com.ledger.core.boundary.http.BaseController;
import com.ledgerx.currency.boundary.api.CurrencyApi;
import com.ledgerx.currency.boundary.dto.CreateCurrencyRequest;
import com.ledgerx.currency.boundary.dto.CurrencyDto;
import com.ledgerx.currency.boundary.dto.UpdateCurrencyRequest;
import com.ledgerx.currency.control.CurrencyService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/currencies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Tag(name = "Currencies", description = "Currency management operations")
public class CurrencyController extends
    BaseController<Long, CurrencyDto, CreateCurrencyRequest, UpdateCurrencyRequest> implements
    CurrencyApi {

  @Inject
  private CurrencyService service;

  // No-args constructor for CDI
  public CurrencyController() {
    super();
  }

  public CurrencyController(CurrencyService service) {
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
