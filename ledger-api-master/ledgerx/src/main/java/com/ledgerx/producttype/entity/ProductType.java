package com.ledgerx.producttype.entity;

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
@Table(name = "PRODUCT_TYPES")
public class ProductType extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "{producttype.validation.code.required}")
  @Size(min = 2, max = 50, message = "{producttype.validation.code.size}")
  @Column(name = "product_type_code", nullable = false, unique = true, length = 50)
  private String productTypeCode;

  @NotBlank(message = "{producttype.validation.persianName.required}")
  @Size(min = 2, max = 200, message = "{producttype.validation.persianName.size}")
  @Column(name = "persian_name", nullable = false, length = 200)
  private String persianProductTypeName;

  @Size(max = 200, message = "{producttype.validation.englishName.size}")
  @Column(name = "english_name", length = 200)
  private String englishProductTypeName;

  @Size(max = 1000, message = "{producttype.validation.description.size}")
  @Column(name = "description", length = 1000)
  private String description;

  @Size(max = 2000, message = "{producttype.validation.features.size}")
  @Column(name = "features", length = 2000)
  private String features;

  @NotNull(message = "{producttype.validation.status.required}")
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private Status status = Status.ACTIVE;

  // Constructors
  public ProductType() {
  }

  public ProductType(String productTypeCode, String persianProductTypeName,
      String englishProductTypeName,
      String description, String features, Status status) {
    this.productTypeCode = productTypeCode;
    this.persianProductTypeName = persianProductTypeName;
    this.englishProductTypeName = englishProductTypeName;
    this.description = description;
    this.features = features;
    this.status = status;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProductTypeCode() {
    return productTypeCode;
  }

  public void setProductTypeCode(String productTypeCode) {
    this.productTypeCode = productTypeCode;
  }

  public String getPersianProductTypeName() {
    return persianProductTypeName;
  }

  public void setPersianProductTypeName(String persianProductTypeName) {
    this.persianProductTypeName = persianProductTypeName;
  }

  public String getEnglishProductTypeName() {
    return englishProductTypeName;
  }

  public void setEnglishProductTypeName(String englishProductTypeName) {
    this.englishProductTypeName = englishProductTypeName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFeatures() {
    return features;
  }

  public void setFeatures(String features) {
    this.features = features;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public enum Status {
    ACTIVE("فعال"),
    INACTIVE("غیرفعال");

    private final String persianLabel;

    Status(String persianLabel) {
      this.persianLabel = persianLabel;
    }

    public String getPersianLabel() {
      return persianLabel;
    }
  }
}
