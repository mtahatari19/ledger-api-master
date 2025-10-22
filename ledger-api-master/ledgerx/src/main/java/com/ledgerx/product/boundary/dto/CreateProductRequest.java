package com.ledgerx.product.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Request to create a new product")
public record CreateProductRequest(
    
    @Schema(description = "Product code", example = "1001")
    @NotBlank(message = "{product.validation.code.required}")
    @Size(min = 2, max = 50, message = "{product.validation.code.size}")
    String productCode,

    @Schema(description = "Persian product name", example = "یارانه کالایی")
    @NotBlank(message = "{product.validation.persianName.required}")
    @Size(min = 2, max = 200, message = "{product.validation.persianName.size}")
    String persianProductName,

    @Schema(description = "English product name", example = "Commodity Subsidy")
    @NotBlank(message = "{product.validation.englishName.required}")
    @Size(min = 2, max = 200, message = "{product.validation.englishName.size}")
    String englishProductName,

    @Schema(description = "Product type", example = "خرید اعتباری")
    @NotBlank(message = "{product.validation.type.required}")
    @Size(min = 2, max = 100, message = "{product.validation.type.size}")
    String productType,

    @Schema(description = "Product summary", example = "محصول یارانه کالایی")
    @Size(max = 500, message = "{product.validation.summary.size}")
    String summary
) {

}
