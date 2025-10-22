package com.ledgerx.producttype.control;

import com.ledger.core.control.AbstractBaseService;
import com.ledgerx.producttype.boundary.ProductTypeRepository;
import com.ledgerx.producttype.boundary.dto.CreateProductTypeRequest;
import com.ledgerx.producttype.boundary.dto.ProductTypeDto;
import com.ledgerx.producttype.boundary.dto.UpdateProductTypeRequest;
import com.ledgerx.producttype.boundary.mapper.ProductTypeMapper;
import com.ledgerx.producttype.entity.ProductType;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductTypeService extends
    AbstractBaseService<ProductType, Long, CreateProductTypeRequest, UpdateProductTypeRequest, ProductTypeDto> {

  @Inject
  private ProductTypeMapper mapper;

  @Inject
  private ProductTypeRepository repository;

  // No-args constructor for CDI
  public ProductTypeService() {
    super();
  }

  public ProductTypeService(ProductTypeRepository repository) {
    super(repository);
  }

  @PostConstruct
  public void init() {
    // Set the repository after injection
    setRepository(repository);
  }

  @Override
  protected ProductType mapCreateDtoToEntity(CreateProductTypeRequest createDto) {
    return mapper.toEntity(createDto);
  }

  @Override
  protected ProductTypeDto mapEntityToResponseDto(ProductType entity) {
    return mapper.toResponseDto(entity);
  }

  @Override
  protected void updateEntityFromDto(ProductType entity, UpdateProductTypeRequest updateDto) {
    mapper.updateEntity(entity, updateDto);
  }

}
