package com.ledger.core.control;

import com.ledger.core.entity.AuditableEntity;
import com.ledger.core.entity.BaseRepository;
import com.ledger.core.filtering.FilterCriteria;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Abstract base ECB service implementation for control layer.
 *
 * <p>Provides common implementation for EcbBaseService interface.
 * Concrete ECB services should extend this class and implement entity-specific logic.</p>
 *
 * <p>This class handles:</p>
 * <ul>
 *   <li>Standard CRUD operations with proper transaction boundaries</li>
 *   <li>Dynamic filtering using PanacheQuery</li>
 *   <li>Pagination support</li>
 *   <li>Entity-to-DTO mapping delegation</li>
 * </ul>
 *
 * @param <T>  the entity type
 * @param <ID> the entity ID type
 * @param <C>  the create DTO type
 * @param <U>  the update DTO type
 * @param <R>  the response DTO type
 */
public abstract class AbstractBaseService<T extends AuditableEntity, ID extends Serializable, C, U, R>
    implements BaseService<T, ID, C, U, R> {

  protected BaseRepository<T, ID> repository;

  protected AbstractBaseService(BaseRepository<T, ID> repository) {
    this.repository = repository;
  }

  public AbstractBaseService() {
    this.repository = null;
  }

  @Override
  @Transactional
  public R create(C createDto) {
    T entity = mapCreateDtoToEntity(createDto);
    // Set auditing fields
    LocalDateTime now = LocalDateTime.now();
    entity.setCreatedAt(now);
    entity.setUpdatedAt(now);
    entity.setCreatedBy("system"); // TODO: Get from security context
    entity.setUpdatedBy("system"); // TODO: Get from security context
    repository.persist(entity);
    return mapEntityToResponseDto(entity);
  }

  @Override
  @Transactional
  public R findById(ID id) {
    T entity = repository.findById((Long) id);
    if (entity == null) {
      throw new WebApplicationException("Resource with id " + id + " not found",
          Response.Status.NOT_FOUND);
    }
    return mapEntityToResponseDto(entity);
  }

  @Override
  @Transactional
  public R update(ID id, U updateDto) {
    T entity = repository.findById((Long) id);
    if (entity == null) {
      throw new WebApplicationException("Resource with id " + id + " not found",
          Response.Status.NOT_FOUND);
    }

    updateEntityFromDto(entity, updateDto);
    // Set auditing fields for update
    entity.setUpdatedAt(LocalDateTime.now());
    entity.setUpdatedBy("system"); // TODO: Get from security context
    repository.persist(entity);
    return mapEntityToResponseDto(entity);
  }

  @Override
  @Transactional
  public void delete(ID id) {
    T entity = repository.findById((Long) id);
    if (entity == null) {
      throw new WebApplicationException("Resource with id " + id + " not found",
          Response.Status.NOT_FOUND);
    }
    repository.delete(entity);
  }

  @Override
  @Transactional
  public List<R> findAll(int page, int size, String sort, List<FilterCriteria> filters) {
    // For now, return all entities with pagination
    // TODO: Implement filtering with PanacheQuery
    PanacheQuery<T> query = repository.findAll();
    List<T> entities = query.page(Page.of(page, size)).list();
    return entities.stream().map(this::mapEntityToResponseDto).toList();
  }

  @Override
  @Transactional
  public boolean existsById(ID id) {
    return repository.findById((Long) id) != null;
  }

  @Override
  @Transactional
  public long count() {
    return repository.count();
  }

  @Override
  public BaseRepository<T, ID> getRepository() {
    return repository;
  }

  public void setRepository(BaseRepository<T, ID> repository) {
    this.repository = repository;
  }

  /**
   * Maps create DTO to entity. Should be implemented by concrete services.
   *
   * @param createDto the create DTO
   * @return the entity
   */
  protected abstract T mapCreateDtoToEntity(C createDto);

  /**
   * Maps entity to response DTO. Should be implemented by concrete services.
   *
   * @param entity the entity
   * @return the response DTO
   */
  protected abstract R mapEntityToResponseDto(T entity);

  /**
   * Updates entity from update DTO. Should be implemented by concrete services.
   *
   * @param entity    the entity to update
   * @param updateDto the update DTO
   */
  protected abstract void updateEntityFromDto(T entity, U updateDto);
}