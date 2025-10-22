package com.ledgerx.plan.entity;

import com.ledger.core.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PLANS")
public class Plan extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PRODUCT_ID", nullable = false)
  @NotNull(message = "{plan.validation.product.required}")
  private Long productId;

  @Column(name = "PLAN_CODE", nullable = false, length = 50, unique = true)
  @NotBlank(message = "{plan.validation.code.required}")
  @Size(min = 2, max = 50, message = "{plan.validation.code.size}")
  private String planCode;

  @Column(name = "PLAN_NAME", nullable = false, length = 200)
  @NotBlank(message = "{plan.validation.name.required}")
  @Size(min = 2, max = 200, message = "{plan.validation.name.size}")
  private String planName;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false, length = 20)
  private Status status = Status.ACTIVE;

  @Column(name = "DESCRIPTION", length = 500)
  private String description;

  @Column(name = "IS_DEFAULT", nullable = false)
  private Boolean isDefault = false;

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getPlanCode() {
    return planCode;
  }

  public void setPlanCode(String planCode) {
    this.planCode = planCode;
  }

  public String getPlanName() {
    return planName;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Boolean isDefault) {
    this.isDefault = isDefault;
  }

  public enum Status {ACTIVE, INACTIVE}

}
