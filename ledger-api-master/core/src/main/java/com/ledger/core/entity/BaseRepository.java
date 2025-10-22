package com.ledger.core.entity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.io.Serializable;

/**
 * Base repository interface for ECB entity layer.
 *
 * <p>All repositories in the entity layer should extend this interface
 * to ensure consistent Panache repository support across the application.</p>
 *
 * <p>This interface provides:</p>
 * <ul>
 *   <li>Standard Panache repository operations</li>
 *   <li>Dynamic query support using PanacheQuery</li>
 *   <li>Type-safe entity and ID handling</li>
 * </ul>
 *
 * @param <T>  the entity type
 * @param <ID> the entity ID type
 */
public interface BaseRepository<T extends AuditableEntity, ID extends Serializable>
    extends PanacheRepository<T> {

  // Additional common repository methods can be added here
  // All entity-specific repositories should extend this interface
}
