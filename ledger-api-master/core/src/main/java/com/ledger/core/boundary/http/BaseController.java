package com.ledger.core.boundary.http;

import com.ledger.core.boundary.api.BaseApi;
import com.ledger.core.control.BaseService;
import com.ledger.core.filtering.FilterCriteria;
import com.ledger.core.filtering.QueryParameterParser;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

/**
 * Base ECB controller implementation for boundary layer.
 *
 * <p>All ECB controllers in the boundary.http package should extend this class
 * to ensure consistent REST API implementation patterns across the application.</p>
 *
 * <p>This class provides:</p>
 * <ul>
 *   <li>Standard RESTful CRUD operation implementations</li>
 *   <li>Query parameter parsing and filtering</li>
 *   <li>Proper HTTP status code handling</li>
 *   <li>Delegation to control layer services</li>
 * </ul>
 *
 * <p>ECB controllers should only contain HTTP concerns and delegate all business logic
 * to the control layer services.</p>
 *
 * @param <ID> the entity ID type
 * @param <R>  the response DTO type
 * @param <C>  the create DTO type
 * @param <U>  the update DTO type
 */
public abstract class BaseController<ID extends Serializable, R, C, U>
    implements BaseApi<ID, R, C, U> {

  protected BaseService<?, ID, C, U, R> service;

  protected BaseController(BaseService<?, ID, C, U, R> service) {
    this.service = service;
  }

  protected BaseController() {
    this.service = null;
  }

  protected void setService(BaseService<?, ID, C, U, R> service) {
    this.service = service;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Override
  public Response create(@Valid C request) {
    if (service == null) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Service not initialized").build();
    }
    try {
      R resource = service.create(request);
      return Response.status(Response.Status.CREATED).entity(resource).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error creating resource: " + e.getMessage()).build();
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Override
  public Response findById(@PathParam("id") ID id) {
    if (service == null) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Service not initialized").build();
    }
    try {
      R resource = service.findById(id);
      return Response.ok(resource).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error finding resource: " + e.getMessage()).build();
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Override
  public Response update(@PathParam("id") ID id, @Valid U request) {
    if (service == null) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Service not initialized").build();
    }
    try {
      R resource = service.update(id, request);
      return Response.ok(resource).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error updating resource: " + e.getMessage()).build();
    }
  }

  @DELETE
  @Path("/{id}")
  @Override
  public Response delete(@PathParam("id") ID id) {
    if (service == null) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Service not initialized").build();
    }
    try {
      service.delete(id);
      return Response.noContent().build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error deleting resource: " + e.getMessage()).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Override
  public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("20") int size,
      @QueryParam("sort") @DefaultValue("") String sort) {
    if (service == null) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Service not initialized").build();
    }
    try {
      List<FilterCriteria> filters = java.util.List.of(); // Empty filters for now
      List<R> results = service.findAll(page, size, sort, filters);
      return Response.ok(results).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error finding resources: " + e.getMessage()).build();
    }
  }
}