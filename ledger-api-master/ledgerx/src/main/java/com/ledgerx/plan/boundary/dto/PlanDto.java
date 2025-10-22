package com.ledgerx.plan.boundary.dto;

import com.ledgerx.plan.entity.Plan;
import java.time.LocalDateTime;

public record PlanDto(
    Long id,
    String planCode,
    String planName,
    String status,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String createdBy,
    String updatedBy
) {

  public static PlanDto from(Plan entity) {
    return new PlanDto(
        entity.getId(),
        entity.getPlanCode(),
        entity.getPlanName(),
        entity.getStatus().toString(),
        entity.getDescription(),
        entity.getCreatedAt(),
        entity.getUpdatedAt(),
        entity.getCreatedBy(),
        entity.getUpdatedBy()
    );
  }
}
