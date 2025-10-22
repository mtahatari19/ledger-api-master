package com.ledgerx.plan.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePlanRequest(
    @NotBlank(message = "{plan.validation.code.required}")
    @Size(min = 2, max = 50, message = "{plan.validation.code.size}")
    String planCode,

    @NotBlank(message = "{plan.validation.name.required}")
    @Size(min = 2, max = 200, message = "{plan.validation.name.size}")
    String planName,

    @NotNull(message = "{plan.validation.product.required}")
    Long productId,

    String description
) {

}
