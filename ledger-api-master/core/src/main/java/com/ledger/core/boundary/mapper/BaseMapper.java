package com.ledger.core.boundary.mapper;

import com.ledger.core.entity.AuditableEntity;
import java.io.Serializable;
import java.util.List;

/**
 * Base ECB mapper interface for boundary layer.
 *
 * <p>All ECB mappers in the boundary.mapper package should extend this interface
 * to ensure consistent entity-to-DTO mapping patterns across the application.</p>
 *
 * <p>This interface provides:</p>
 * <ul>
 *   <li>Entity to DTO mapping</li>
 *   <li>DTO to entity mapping</li>
 *   <li>Entity update from DTO</li>
 *   <li>Collection mapping support</li>
 * </ul>
 *
 * <p>ECB implementations should use MapStruct or manual mapping as needed.
 * Avoid manual mapping outside of mappers.</p>
 *
 * @param <T>  the entity type
 * @param <ID> the entity ID type
 * @param <C>  the create DTO type
 * @param <U>  the update DTO type
 * @param <R>  the response DTO type
 */
public interface BaseMapper<T extends AuditableEntity, ID extends Serializable, C, U, R> {

  /**
   * Maps create DTO to entity.
   *
   * @param createDto the create DTO
   * @return the entity
   */
  T toEntity(C createDto);

  /**
   * Maps entity to response DTO.
   *
   * @param entity the entity
   * @return the response DTO
   */
  R toResponseDto(T entity);

  /**
   * Updates entity from update DTO.
   *
   * @param updateDto the update DTO
   * @param entity    the entity to update
   */
  void updateEntity(T entity, U updateDto);

  /**
   * Maps entity collection to response DTO collection.
   *
   * @param entities the entity collection
   * @return the response DTO collection
   */
  default List<R> toResponseDtos(List<T> entities) {
    return entities.stream()
        .map(this::toResponseDto)
        .toList();
  }
}
