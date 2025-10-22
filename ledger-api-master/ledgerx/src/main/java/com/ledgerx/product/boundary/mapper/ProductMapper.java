
package com.ledgerx.product.boundary.mapper;

import com.ledger.core.boundary.mapper.BaseMapper;
import com.ledgerx.product.boundary.dto.CreateProductRequest;
import com.ledgerx.product.boundary.dto.ProductDto;
import com.ledgerx.product.boundary.dto.UpdateProductRequest;
import com.ledgerx.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ProductMapper extends
    BaseMapper<Product, Long, CreateProductRequest, UpdateProductRequest, ProductDto> {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  Product toEntity(CreateProductRequest request);

  ProductDto toResponseDto(Product entity);

  // Manual implementation for updateEntity since MapStruct can't generate void methods
  default void updateEntity(Product entity, UpdateProductRequest request) {
    if (request.productCode() != null) {
      entity.setProductCode(request.productCode());
    }
    if (request.persianProductName() != null) {
      entity.setPersianProductName(request.persianProductName());
    }
    if (request.englishProductName() != null) {
      entity.setEnglishProductName(request.englishProductName());
    }
    if (request.productType() != null) {
      entity.setProductType(request.productType());
    }
    if (request.summary() != null) {
      entity.setDescription(request.summary());
    }
    if (request.status() != null) {
      entity.setStatus(Product.Status.valueOf(request.status()));
    }
  }
}
