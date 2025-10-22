package com.ledgerx.account.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Request to update an existing account")
public record UpdateAccountRequest(
    @NotBlank(message = "{account.validation.number.required}")
    @Size(min = 2, max = 50, message = "{account.validation.number.size}")
    String accountNumber,

    @NotBlank(message = "{account.validation.name.required}")
    @Size(min = 2, max = 200, message = "{account.validation.name.size}")
    String accountName,

    @NotNull(message = "{account.validation.type.required}")
    Long accountTypeId,

    @NotNull(message = "{account.validation.currency.required}")
    Long currencyId,

    @NotNull(message = "{account.validation.party.required}")
    Long partyId,

    @NotNull(message = "{account.validation.organizationalUnit.required}")
    Long organizationalUnitId,

    String status,
    String description
) {

}
