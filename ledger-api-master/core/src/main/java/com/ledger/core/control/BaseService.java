package com.ledger.core.control;

import com.ledger.core.entity.AuditableEntity;
import com.ledger.core.entity.BaseRepository;
import com.ledger.core.filtering.FilterCriteria;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Base ECB service interface for control layer.
 *
 * <p>All ECB services in the control layer should extend this interface
 * to ensure consistent business logic patterns across the application.</p>
 *
 * <p>This interface provides:</p>
 * <ul>
 *   <li>Standard CRUD operations with @Transactional boundaries</li>
 *   <li>Dynamic filtering and search capabilities</li>
 *   <li>Pagination support</li>
 *   <li>Panache-based querying</li>
 * </ul>
 *
 * <p>ECB services should contain business logic only and delegate to repositories
 * for data access. No HTTP concerns should be present in this layer.</p>
 *
 * @param <T>  the entity type
 * @param <ID> the entity ID type
 * @param <C>  the create DTO type
 * @param <U>  the update DTO type
 * @param <R>  the response DTO type
 */
public interface BaseService<T extends AuditableEntity, ID extends Serializable, C, U, R> {

  /**
   * Creates a new entity from the create DTO.
   *
   * @param createDto the create DTO
   * @return the response DTO
   */
  @Transactional
  R create(C createDto);

  /**
   * Finds an entity by ID and returns the response DTO.
   *
   * @param id the entity ID
   * @return the response DTO
   */
  @Transactional
  R findById(ID id);

  /**
   * Updates an entity with the update DTO.
   *
   * @param id        the entity ID
   * @param updateDto the update DTO
   * @return the response DTO
   */
  @Transactional
  R update(ID id, U updateDto);

  /**
   * Deletes an entity by ID.
   *
   * @param id the entity ID
   */
  @Transactional
  void delete(ID id);

  /**
   * Finds all entities with pagination and filtering.
   *
   * @param page    page number
   * @param size    page size
   * @param sort    sort criteria
   * @param filters filter criteria
   * @return paginated response DTOs
   */
  @Transactional
  List<R> findAll(int page, int size, String sort, List<FilterCriteria> filters);

  /**
   * Checks if an entity exists by ID.
   *
   * @param id the entity ID
   * @return true if exists, false otherwise
   */
  @Transactional
  boolean existsById(ID id);

  /**
   * Counts all entities.
   *
   * @return total count
   */
  @Transactional
  long count();

  /**
   * Gets the repository for this service.
   *
   * @return the base repository
   */
  BaseRepository<T, ID> getRepository();
}