package com.ledgerx.plan.boundary.http;

import com.ledger.core.boundary.http.BaseController;
import com.ledgerx.plan.boundary.api.PlanApi;
import com.ledgerx.plan.boundary.dto.CreatePlanRequest;
import com.ledgerx.plan.boundary.dto.PlanDto;
import com.ledgerx.plan.boundary.dto.UpdatePlanRequest;
import com.ledgerx.plan.control.PlanService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/plans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Tag(name = "Plans", description = "Plan management operations")
public class PlanController extends
    BaseController<Long, PlanDto, CreatePlanRequest, UpdatePlanRequest> implements PlanApi {

  @Inject
  private PlanService service;

  // No-args constructor for CDI
  public PlanController() {
    super();
  }

  public PlanController(PlanService service) {
    super(service);
  }

  @PostConstruct
  public void init() {
    // Set the service after injection
    setService(service);
  }
}
