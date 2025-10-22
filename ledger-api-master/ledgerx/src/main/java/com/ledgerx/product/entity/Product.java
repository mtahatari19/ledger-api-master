package com.ledgerx.product.entity;

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
@Table(name = "PRODUCTS")
public class Product extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "{product.validation.code.required}")
  @Size(min = 2, max = 50, message = "{product.validation.code.size}")
  @Column(name = "product_code", nullable = false, unique = true, length = 50)
  private String productCode;

  @NotBlank(message = "{product.validation.persianName.required}")
  @Size(min = 2, max = 200, message = "{product.validation.persianName.size}")
  @Column(name = "persian_name", nullable = false, length = 200)
  private String persianProductName;

  @NotBlank(message = "{product.validation.englishName.required}")
  @Size(min = 2, max = 200, message = "{product.validation.englishName.size}")
  @Column(name = "english_name", nullable = false, length = 200)
  private String englishProductName;

  @NotBlank(message = "{product.validation.type.required}")
  @Size(min = 2, max = 100, message = "{product.validation.type.size}")
  @Column(name = "product_type", nullable = false, length = 100)
  private String productType;

  @Size(max = 500, message = "{product.validation.description.size}")
  @Column(name = "description", length = 500)
  private String description;

  @NotNull(message = "{product.validation.status.required}")
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private Status status = Status.ACTIVE;

  // Constructors
  public Product() {
  }

  public Product(String productCode, String persianProductName, String englishProductName,
      String productType, String description, Status status) {
    this.productCode = productCode;
    this.persianProductName = persianProductName;
    this.englishProductName = englishProductName;
    this.productType = productType;
    this.description = description;
    this.status = status;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getPersianProductName() {
    return persianProductName;
  }

  public void setPersianProductName(String persianProductName) {
    this.persianProductName = persianProductName;
  }

  public String getEnglishProductName() {
    return englishProductName;
  }

  public void setEnglishProductName(String englishProductName) {
    this.englishProductName = englishProductName;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public enum Status {
    ACTIVE("فعال"),
    INACTIVE("غیر فعال");

    private final String persianLabel;

    Status(String persianLabel) {
      this.persianLabel = persianLabel;
    }

    public String getPersianLabel() {
      return persianLabel;
    }
  }
}