package com.ledgerx.producttype.boundary.mapper;

import com.ledger.core.boundary.mapper.BaseMapper;
import com.ledgerx.producttype.boundary.dto.CreateProductTypeRequest;
import com.ledgerx.producttype.boundary.dto.ProductTypeDto;
import com.ledgerx.producttype.boundary.dto.UpdateProductTypeRequest;
import com.ledgerx.producttype.entity.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ProductTypeMapper extends
    BaseMapper<ProductType, Long, CreateProductTypeRequest, UpdateProductTypeRequest, ProductTypeDto> {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  ProductType toEntity(CreateProductTypeRequest request);

  ProductTypeDto toResponseDto(ProductType entity);

  // Manual implementation for updateEntity since MapStruct can't generate void methods
  default void updateEntity(ProductType entity, UpdateProductTypeRequest request) {
    if (request.productTypeCode() != null) {
      entity.setProductTypeCode(request.productTypeCode());
    }
    if (request.persianProductTypeName() != null) {
      entity.setPersianProductTypeName(request.persianProductTypeName());
    }
    if (request.englishProductTypeName() != null) {
      entity.setEnglishProductTypeName(request.englishProductTypeName());
    }
    if (request.summary() != null) {
      entity.setDescription(request.summary());
    }
    if (request.features() != null) {
      entity.setFeatures(request.features());
    }
    if (request.status() != null) {
      entity.setStatus(ProductType.Status.valueOf(request.status()));
    }
  }
}
