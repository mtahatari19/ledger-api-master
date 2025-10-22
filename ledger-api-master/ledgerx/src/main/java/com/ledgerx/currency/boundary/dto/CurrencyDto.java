package com.ledgerx.currency.boundary.dto;

import com.ledgerx.currency.entity.Currency;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Currency information")
public record CurrencyDto(
    Long id,
    String currencyCode,
    Integer currencyNumCode,
    String swiftCode,
    String currencyName,
    String symbol,
    Integer decimalPrecision,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String createdBy,
    String updatedBy
) {

  public static CurrencyDto from(Currency entity) {
    return new CurrencyDto(
        entity.getId(),
        entity.getCurrencyCode(),
        entity.getCurrencyNumCode(),
        entity.getSwiftCode(),
        entity.getCurrencyName(),
        entity.getSymbol(),
        entity.getDecimalPrecision(),
        entity.getStatus().toString(),
        entity.getCreatedAt(),
        entity.getUpdatedAt(),
        entity.getCreatedBy(),
        entity.getUpdatedBy()
    );
  }
}
