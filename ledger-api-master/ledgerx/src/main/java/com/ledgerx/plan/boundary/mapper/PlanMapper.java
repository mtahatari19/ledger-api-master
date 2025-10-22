package com.ledgerx.plan.boundary.mapper;

import com.ledger.core.boundary.mapper.BaseMapper;
import com.ledgerx.plan.boundary.dto.CreatePlanRequest;
import com.ledgerx.plan.boundary.dto.PlanDto;
import com.ledgerx.plan.boundary.dto.UpdatePlanRequest;
import com.ledgerx.plan.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface PlanMapper extends
    BaseMapper<Plan, Long, CreatePlanRequest, UpdatePlanRequest, PlanDto> {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "isDefault", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  Plan toEntity(CreatePlanRequest request);

  PlanDto toResponseDto(Plan entity);

  // Manual implementation for updateEntity since MapStruct can't generate void methods
  default void updateEntity(Plan entity, UpdatePlanRequest request) {
    if (request.planCode() != null) {
      entity.setPlanCode(request.planCode());
    }
    if (request.planName() != null) {
      entity.setPlanName(request.planName());
    }
    if (request.status() != null) {
      entity.setStatus(Plan.Status.valueOf(request.status()));
    }
    if (request.description() != null) {
      entity.setDescription(request.description());
    }
  }
}
