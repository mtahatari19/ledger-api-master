package com.ledgerx.product.control;

import com.ledger.core.control.AbstractBaseService;
import com.ledgerx.product.boundary.ProductRepository;
import com.ledgerx.product.boundary.dto.CreateProductRequest;
import com.ledgerx.product.boundary.dto.ProductDto;
import com.ledgerx.product.boundary.dto.UpdateProductRequest;
import com.ledgerx.product.boundary.mapper.ProductMapper;
import com.ledgerx.product.entity.Product;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductService extends
    AbstractBaseService<Product, Long, CreateProductRequest, UpdateProductRequest, ProductDto> {

  @Inject
  private ProductMapper mapper;

  @Inject
  private ProductRepository repository;

  // No-args constructor for CDI
  public ProductService() {
    super();
  }

  public ProductService(ProductRepository repository) {
    super(repository);
  }

  @PostConstruct
  public void init() {
    // Set the repository after injection
    setRepository(repository);
  }

  @Override
  protected Product mapCreateDtoToEntity(CreateProductRequest createDto) {
    return mapper.toEntity(createDto);
  }

  @Override
  protected ProductDto mapEntityToResponseDto(Product entity) {
    return mapper.toResponseDto(entity);
  }

  @Override
  protected void updateEntityFromDto(Product entity, UpdateProductRequest updateDto) {
    mapper.updateEntity(entity, updateDto);
  }

}
