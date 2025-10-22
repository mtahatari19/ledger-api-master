package com.ledgerx.producttype.boundary.api;

import com.ledger.core.boundary.api.BaseApi;
import com.ledgerx.producttype.boundary.dto.CreateProductTypeRequest;
import com.ledgerx.producttype.boundary.dto.ProductTypeDto;
import com.ledgerx.producttype.boundary.dto.UpdateProductTypeRequest;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Product Types", description = "Product type management operations")
public interface ProductTypeApi extends
    BaseApi<Long, ProductTypeDto, CreateProductTypeRequest, UpdateProductTypeRequest> {
  // CRUD operations are inherited from BaseApi
}
