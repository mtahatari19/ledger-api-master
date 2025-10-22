package com.ledgerx.goods.boundary.mapper;

import com.ledger.core.boundary.mapper.BaseMapper;
import com.ledgerx.goods.boundary.dto.CreateGoodsRequest;
import com.ledgerx.goods.boundary.dto.GoodsDto;
import com.ledgerx.goods.boundary.dto.UpdateGoodsRequest;
import com.ledgerx.goods.entity.Goods;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct mapper interface for Goods entity and DTOs.
 * 
 * <p>This mapper provides type-safe mapping between Goods entities and their DTOs,
 * extending the base mapper functionality.</p>
 */
@Mapper(componentModel = "cdi")
public interface GoodsMapper extends BaseMapper<Goods, Long, CreateGoodsRequest, UpdateGoodsRequest, GoodsDto> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Goods toEntity(CreateGoodsRequest request);

    default GoodsDto toResponseDto(Goods entity) {
        return GoodsDto.from(entity);
    }

    default void updateEntity(Goods entity, UpdateGoodsRequest request) {
        if (request.name() != null) {
            entity.setName(request.name());
        }
        if (request.description() != null) {
            entity.setDescription(request.description());
        }
        if (request.category() != null) {
            entity.setCategory(request.category());
        }
        if (request.unit() != null) {
            entity.setUnit(request.unit());
        }
        if (request.status() != null) {
            entity.setStatus(request.status());
        }
    }
}
