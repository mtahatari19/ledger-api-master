package com.ledgerx.product.boundary.http;

import com.ledger.core.boundary.http.BaseController;
import com.ledgerx.product.boundary.api.ProductApi;
import com.ledgerx.product.boundary.dto.CreateProductRequest;
import com.ledgerx.product.boundary.dto.ProductDto;
import com.ledgerx.product.boundary.dto.UpdateProductRequest;
import com.ledgerx.product.control.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/products")
@ApplicationScoped
@Tag(name = "Products", description = "Product management operations")
public class ProductController extends
    BaseController<Long, ProductDto, CreateProductRequest, UpdateProductRequest> implements
    ProductApi {

  @Inject
  private ProductService service;

  // No-args constructor for CDI
  public ProductController() {
    super();
  }

  public ProductController(ProductService service) {
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
