package com.ledgerx.goods.boundary.dto;

import com.ledgerx.goods.entity.Goods;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Data Transfer Object for updating existing Goods.
 * 
 * <p>This DTO represents the request data for updating existing Goods items,
 * where all fields are optional for partial updates.</p>
 */
@Schema(description = "Request data for updating existing goods")
public record UpdateGoodsRequest(
    @Size(max = 255, message = "Goods name must not exceed 255 characters")
    @Schema(description = "Name of the goods", example = "Olive Oil")
    String name,

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Description of the goods", example = "Extra virgin olive oil from Italy")
    String description,

    @Size(max = 50, message = "Category must not exceed 50 characters")
    @Schema(description = "Category of the goods", example = "Food & Beverages")
    String category,

    @Size(max = 20, message = "Unit must not exceed 20 characters")
    @Schema(description = "Unit of measurement", example = "liter")
    String unit,

    @Schema(description = "Status of the goods", example = "ACTIVE")
    Goods.Status status
) {
}
