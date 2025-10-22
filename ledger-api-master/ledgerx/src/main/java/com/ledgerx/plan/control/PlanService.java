package com.ledgerx.plan.control;

import com.ledger.core.control.AbstractBaseService;
import com.ledgerx.plan.boundary.PlanRepository;
import com.ledgerx.plan.boundary.dto.CreatePlanRequest;
import com.ledgerx.plan.boundary.dto.PlanDto;
import com.ledgerx.plan.boundary.dto.UpdatePlanRequest;
import com.ledgerx.plan.boundary.mapper.PlanMapper;
import com.ledgerx.plan.entity.Plan;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlanService extends
    AbstractBaseService<Plan, Long, CreatePlanRequest, UpdatePlanRequest, PlanDto> {

  @Inject
  private PlanMapper mapper;

  @Inject
  private PlanRepository repository;

  // No-args constructor for CDI
  public PlanService() {
    super();
  }

  public PlanService(PlanRepository repository) {
    super(repository);
  }

  @PostConstruct
  public void init() {
    // Set the repository after injection
    setRepository(repository);
  }

  @Override
  protected Plan mapCreateDtoToEntity(CreatePlanRequest createDto) {
    return mapper.toEntity(createDto);
  }

  @Override
  protected PlanDto mapEntityToResponseDto(Plan entity) {
    return mapper.toResponseDto(entity);
  }

  @Override
  protected void updateEntityFromDto(Plan entity, UpdatePlanRequest updateDto) {
    mapper.updateEntity(entity, updateDto);
  }
}
