package com.ledgerx.account.boundary.dto;

import com.ledgerx.account.entity.Account;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Account information")
public record AccountDto(
    Long id,
    String accountNumber,
    String accountName,
    Long accountTypeId,
    Long currencyId,
    Long partyId,
    Long organizationalUnitId,
    String status,
    String description,
    Double balance,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String createdBy,
    String updatedBy
) {

  public static AccountDto from(Account entity) {
    return new AccountDto(
        entity.getId(),
        entity.getAccountNumber(),
        entity.getAccountName(),
        entity.getAccountTypeId(),
        entity.getCurrencyId(),
        entity.getPartyId(),
        entity.getOrganizationalUnitId(),
        entity.getStatus().toString(),
        entity.getDescription(),
        entity.getBalance(),
        entity.getCreatedAt(),
        entity.getUpdatedAt(),
        entity.getCreatedBy(),
        entity.getUpdatedBy()
    );
  }
}
