package com.ledgerx.producttype.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(description = "Request DTO for creating a new product type")
public record CreateProductTypeRequest(
    
    @Operation(summary = "Product type code", description = "1001")
    @NotBlank(message = "{producttype.validation.code.required}")
    @Size(min = 2, max = 50, message = "{producttype.validation.code.size}")
    String productTypeCode,

    @Operation(summary = "Persian product type name", description = "خرید اعتباری")
    @NotBlank(message = "{producttype.validation.persianName.required}")
    @Size(min = 2, max = 200, message = "{producttype.validation.persianName.size}")
    String persianProductTypeName,

    @Operation(summary = "English product type name", description = "Credit Purchase")
    @Size(max = 200, message = "{producttype.validation.englishName.size}")
    String englishProductTypeName,

    @Operation(summary = "Product type summary", description = "نوع محصول خرید اعتباری")
    @Size(max = 1000, message = "{producttype.validation.summary.size}")
    String summary,

    @Operation(summary = "Product type features", description = "امکان خرید اعتباری، پرداخت اقساطی")
    @Size(max = 2000, message = "{producttype.validation.features.size}")
    String features
) {

}
