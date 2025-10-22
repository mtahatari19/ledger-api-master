package com.ledgerx.producttype.boundary.dto;

import com.ledgerx.producttype.entity.ProductType;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(description = "Product type response DTO")
public record ProductTypeDto(
    @Operation(summary = "Product type ID", description = "1")
    Long id,

    @Operation(summary = "Product type code", description = "1001")
    String productTypeCode,

    @Operation(summary = "Persian product type name", description = "خرید اعتباری")
    String persianProductTypeName,

    @Operation(summary = "English product type name", description = "Credit Purchase")
    String englishProductTypeName,

    @Operation(summary = "Product type summary", description = "نوع محصول خرید اعتباری")
    String summary,

    @Operation(summary = "Product type features", description = "امکان خرید اعتباری، پرداخت اقساطی")
    String features,

    @Operation(summary = "Product type status", description = "ACTIVE")
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

  public static ProductTypeDto from(ProductType entity) {
    return new ProductTypeDto(
        entity.getId(),
        entity.getProductTypeCode(),
        entity.getPersianProductTypeName(),
        entity.getEnglishProductTypeName(),
        entity.getDescription(),
        entity.getFeatures(),
        entity.getStatus().name(),
        entity.getStatus().getPersianLabel(),
        entity.getCreatedAt(),
        entity.getUpdatedAt(),
        entity.getCreatedBy(),
        entity.getUpdatedBy()
    );
  }
}
