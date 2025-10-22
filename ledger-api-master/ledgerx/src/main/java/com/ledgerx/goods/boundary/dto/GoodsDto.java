package com.ledgerx.goods.boundary.dto;

import com.ledgerx.goods.entity.Goods;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Data Transfer Object for Goods responses.
 * 
 * <p>This DTO represents the response data for Goods operations,
 * including all relevant fields and audit information.</p>
 */
@Schema(description = "Goods response data")
public record GoodsDto(
    Long id,
    String name,
    String description,
    String category,
    String unit,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String createdBy,
    String updatedBy
) {

    public static GoodsDto from(Goods entity) {
        return new GoodsDto(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getCategory(),
            entity.getUnit(),
            entity.getStatus().toString(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getCreatedBy(),
            entity.getUpdatedBy()
        );
    }
}
