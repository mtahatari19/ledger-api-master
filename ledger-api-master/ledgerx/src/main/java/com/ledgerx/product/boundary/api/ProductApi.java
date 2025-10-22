package com.ledgerx.product.boundary.api;

import com.ledger.core.boundary.api.BaseApi;
import com.ledgerx.product.boundary.dto.CreateProductRequest;
import com.ledgerx.product.boundary.dto.ProductDto;
import com.ledgerx.product.boundary.dto.UpdateProductRequest;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Products", description = "Product management operations")
public interface ProductApi extends
    BaseApi<Long, ProductDto, CreateProductRequest, UpdateProductRequest> {
  // CRUD operations are inherited from BaseApi
}
