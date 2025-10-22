package com.ledgerx.accountingrelationtype.boundary.dto;

import com.ledgerx.accountingrelationtype.entity.AccountingRelationType;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(description = "Accounting relation type response DTO")
public record AccountingRelationTypeDto(

    @Operation(summary = "Accounting relation type ID", description = "1")
    Long id,

    @Operation(summary = "Accounting relation code", description = "1001")
    String accountingRelationCode,

    @Operation(summary = "Persian title", description = "خرید اعتباری")
    String persianTitle,

    @Operation(summary = "English title", description = "Credit Purchase")
    String englishTitle,

    @Operation(summary = "Subsystem", description = "GL")
    String subsystem,

    @Operation(summary = "Product type", description = "Credit")
    String productType,

    @Operation(summary = "summary", description = "نوع رابطه حسابداری خرید اعتباری")
    String summary,

    @Operation(summary = "Status", description = "ACTIVE")
    String status,

    @Operation(summary = "Persian status label", description = "فعال")
    String statusLabel,

    @Operation(summary = "Creation timestamp", description = "2024-01-01T10:00:00")
    LocalDateTime createdAt,

    @Operation(summary = "Last update timestamp", description = "2024-01-01T14:33:00")
    LocalDateTime updatedAt,

    @Operation(summary = "Created by user", description = "admin")
    String createdBy,

    @Operation(summary = "Updated by user", description = "admin")
    String updatedBy
) {

  public static AccountingRelationTypeDto from(AccountingRelationType entity) {
    return new AccountingRelationTypeDto(
        entity.getId(),
        entity.getAccountingRelationCode(),
        entity.getPersianTitle(),
        entity.getEnglishTitle(),
        entity.getSubsystem(),
        entity.getProductType(),
        entity.getDescription(),
        entity.getStatus().name(),
        entity.getStatus().getPersianLabel(),
        entity.getCreatedAt(),
        entity.getUpdatedAt(),
        entity.getCreatedBy(),
        entity.getUpdatedBy()
    );
  }
}
