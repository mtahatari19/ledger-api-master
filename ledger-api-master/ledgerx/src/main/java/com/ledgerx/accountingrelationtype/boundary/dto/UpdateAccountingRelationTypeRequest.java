package com.ledgerx.accountingrelationtype.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(description = "Request DTO for updating an existing accounting relation type")
public record UpdateAccountingRelationTypeRequest(

    @Operation(summary = "Accounting relation code", description = "1001")
    @NotBlank(message = "{accountingrelationtype.validation.code.required}")
    @Size(min = 1, max = 10, message = "{accountingrelationtype.validation.code.size}")
    String accountingRelationCode,

    @Operation(summary = "Persian title", description = "خرید اعتباری")
    @NotBlank(message = "{accountingrelationtype.validation.persianTitle.required}")
    @Size(min = 2, max = 200, message = "{accountingrelationtype.validation.persianTitle.size}")
    String persianTitle,

    @Operation(summary = "English title", description = "Credit Purchase")
    @NotBlank(message = "{accountingrelationtype.validation.englishTitle.required}")
    @Size(min = 2, max = 200, message = "{accountingrelationtype.validation.englishTitle.size}")
    String englishTitle,

    @Operation(summary = "Subsystem", description = "GL")
    @NotBlank(message = "{accountingrelationtype.validation.subsystem.required}")
    @Size(min = 2, max = 10, message = "{accountingrelationtype.validation.subsystem.size}")
    String subsystem,

    @Operation(summary = "Product type", description = "Credit")
    @Size(max = 50, message = "{accountingrelationtype.validation.productType.size}")
    String productType,

    @Operation(summary = "summary", description = "نوع رابطه حسابداری خرید اعتباری")
    @Size(max = 1000, message = "{accountingrelationtype.validation.summary.size}")
    String summary,

    @Operation(summary = "Status", description = "ACTIVE")
    String status
) {

}
