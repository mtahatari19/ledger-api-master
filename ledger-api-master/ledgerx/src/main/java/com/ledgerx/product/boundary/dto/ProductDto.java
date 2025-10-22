package com.ledgerx.product.boundary.dto;

import com.ledgerx.product.entity.Product;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Product information")
public record ProductDto(
    @Schema(description = "Product ID", example = "1")
    Long id,

    @Schema(description = "Product code", example = "1001")
    String productCode,

    @Schema(description = "Persian product name", example = "یارانه کالایی")
    String persianProductName,

    @Schema(description = "English product name", example = "Commodity Subsidy")
    String englishProductName,

    @Schema(description = "Product type", example = "خرید اعتباری")
    String productType,

    @Schema(description = "Product summary", example = "محصول یارانه کالایی")
    String summary,

    @Schema(description = "Product status", example = "ACTIVE")
    String status,

    @Schema(description = "Persian status label", example = "فعال")
    String statusLabel,

    @Schema(description = "Creation timestamp", example = "2024-01-01T10:00:00")
    LocalDateTime createdAt,

    @Schema(description = "Last update timestamp", example = "2024-01-01T14:33:00")
    LocalDateTime updatedAt,

    @Schema(description = "Created by user", example = "admin")
    String createdBy,

    @Schema(description = "Updated by user", example = "admin")
    String updatedBy
) {

  public static ProductDto from(Product entity) {
    return new ProductDto(
        entity.getId(),
        entity.getProductCode(),
        entity.getPersianProductName(),
        entity.getEnglishProductName(),
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
