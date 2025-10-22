package com.ledgerx.producttype.boundary.http;

import com.ledger.core.boundary.http.BaseController;
import com.ledgerx.producttype.boundary.api.ProductTypeApi;
import com.ledgerx.producttype.boundary.dto.CreateProductTypeRequest;
import com.ledgerx.producttype.boundary.dto.ProductTypeDto;
import com.ledgerx.producttype.boundary.dto.UpdateProductTypeRequest;
import com.ledgerx.producttype.control.ProductTypeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/product-types")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Tag(name = "Product Types", description = "Product type management operations")
public class ProductTypeController extends
    BaseController<Long, ProductTypeDto, CreateProductTypeRequest, UpdateProductTypeRequest> implements
    ProductTypeApi {

  @Inject
  private ProductTypeService service;

  // No-args constructor for CDI
  public ProductTypeController() {
    super();
  }

  public ProductTypeController(ProductTypeService service) {
    super(service);
  }

  @PostConstruct
  public void init() {
    // Set the service after injection
    if (service != null) {
      setService(service);
    }
  }
}