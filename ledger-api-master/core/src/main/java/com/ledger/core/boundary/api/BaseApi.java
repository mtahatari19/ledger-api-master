package com.ledger.core.boundary.api;

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

/**
 * Base ECB API interface for boundary layer.
 *
 * <p>All ECB API interfaces in the boundary.api package should extend this interface
 * to ensure consistent REST API patterns across the application.</p>
 *
 * <p>This interface provides:</p>
 * <ul>
 *   <li>Standard RESTful CRUD operations</li>
 *   <li>Proper HTTP status codes and error handling</li>
 *   <li>Pagination and filtering support</li>
 * </ul>
 *
 * <p>ECB controllers in boundary.http should implement these interfaces
 * and delegate to control layer services.</p>
 *
 * @param <ID> the entity ID type
 * @param <R>  the response DTO type
 * @param <C>  the create DTO type
 * @param <U>  the update DTO type
 */
public interface BaseApi<ID extends Serializable, R, C, U> {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response create(@Valid C request);

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Response findById(@PathParam("id") ID id);

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response update(@PathParam("id") ID id, @Valid U request);

  @DELETE
  @Path("/{id}")
  Response delete(@PathParam("id") ID id);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response findAll(@QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("20") int size,
      @QueryParam("sort") @DefaultValue("") String sort);
}