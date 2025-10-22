package com.ledgerx.plan.boundary.api;

import com.ledger.core.boundary.api.BaseApi;
import com.ledgerx.plan.boundary.dto.CreatePlanRequest;
import com.ledgerx.plan.boundary.dto.PlanDto;
import com.ledgerx.plan.boundary.dto.UpdatePlanRequest;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Plans", description = "Plan management operations")
public interface PlanApi extends BaseApi<Long, PlanDto, CreatePlanRequest, UpdatePlanRequest> {
  // CRUD operations are inherited from BaseApi
}
