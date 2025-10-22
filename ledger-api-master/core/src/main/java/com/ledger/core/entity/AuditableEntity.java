package com.ledger.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Base entity class for JPA auditing in ECB architecture.
 *
 * <p>All entities in the entity layer must extend this class to ensure
 * consistent auditing across the application. This class provides:</p>
 * <ul>
 *   <li>Created/modified timestamps</li>
 *   <li>Created/modified by user tracking</li>
 *   <li>JPA auditing integration</li>
 * </ul>
 *
 * <p>This is centrally configured in the core library auto-configuration.
 * Do not redefine auditing configuration in application services.</p>
 */
@MappedSuperclass
public abstract class AuditableEntity {

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @Column(name = "updated_by")
  private String updatedBy;

  // Getters and Setters
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }
}
