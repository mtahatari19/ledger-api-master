package com.ledgerx.currency.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Request to update an existing currency")
public record UpdateCurrencyRequest(
    @NotBlank(message = "{currency.validation.code.required}")
    @Size(min = 3, max = 3, message = "{currency.validation.code.size}")
    String currencyCode,

    @NotNull(message = "{currency.validation.numCode.required}")
    Integer currencyNumCode,

    @NotBlank(message = "{currency.validation.swiftCode.required}")
    @Size(min = 3, max = 3, message = "{currency.validation.swiftCode.size}")
    String swiftCode,

    @NotBlank(message = "{currency.validation.name.required}")
    @Size(min = 2, max = 100, message = "{currency.validation.name.size}")
    String currencyName,

    @NotBlank(message = "{currency.validation.symbol.required}")
    @Size(min = 1, max = 10, message = "{currency.validation.symbol.size}")
    String symbol,

    @NotNull(message = "{currency.validation.precision.required}")
    Integer decimalPrecision,

    String status
) {

}
