package com.ledgerx.plan.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePlanRequest(
    @NotBlank(message = "{plan.validation.code.required}")
    @Size(min = 2, max = 50, message = "{plan.validation.code.size}")
    String planCode,

    @NotBlank(message = "{plan.validation.name.required}")
    @Size(min = 2, max = 200, message = "{plan.validation.name.size}")
    String planName,

    String status,
    String description
) {

}
