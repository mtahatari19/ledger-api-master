package com.ledgerx.accountingrelationtype.entity;

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
@Table(name = "ACCOUNTING_RELATION_TYPES")
public class AccountingRelationType extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "{accountingrelationtype.validation.code.required}")
  @Size(min = 1, max = 10, message = "{accountingrelationtype.validation.code.size}")
  @Column(name = "accounting_relation_code", nullable = false, unique = true, length = 10)
  private String accountingRelationCode;

  @NotBlank(message = "{accountingrelationtype.validation.persianTitle.required}")
  @Size(min = 2, max = 200, message = "{accountingrelationtype.validation.persianTitle.size}")
  @Column(name = "persian_title", nullable = false, length = 200)
  private String persianTitle;

  @NotBlank(message = "{accountingrelationtype.validation.englishTitle.required}")
  @Size(min = 2, max = 200, message = "{accountingrelationtype.validation.englishTitle.size}")
  @Column(name = "english_title", nullable = false, length = 200)
  private String englishTitle;

  @NotBlank(message = "{accountingrelationtype.validation.subsystem.required}")
  @Size(min = 2, max = 10, message = "{accountingrelationtype.validation.subsystem.size}")
  @Column(name = "subsystem", nullable = false, length = 10)
  private String subsystem;

  @Size(max = 50, message = "{accountingrelationtype.validation.productType.size}")
  @Column(name = "product_type", length = 50)
  private String productType;

  @Size(max = 1000, message = "{accountingrelationtype.validation.description.size}")
  @Column(name = "description", length = 1000)
  private String description;

  @NotNull(message = "{accountingrelationtype.validation.status.required}")
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private Status status = Status.ACTIVE;

  // Constructors
  public AccountingRelationType() {
  }

  public AccountingRelationType(String accountingRelationCode, String persianTitle,
      String englishTitle,
      String subsystem, String productType, String description, Status status) {
    this.accountingRelationCode = accountingRelationCode;
    this.persianTitle = persianTitle;
    this.englishTitle = englishTitle;
    this.subsystem = subsystem;
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

  public String getAccountingRelationCode() {
    return accountingRelationCode;
  }

  public void setAccountingRelationCode(String accountingRelationCode) {
    this.accountingRelationCode = accountingRelationCode;
  }

  public String getPersianTitle() {
    return persianTitle;
  }

  public void setPersianTitle(String persianTitle) {
    this.persianTitle = persianTitle;
  }

  public String getEnglishTitle() {
    return englishTitle;
  }

  public void setEnglishTitle(String englishTitle) {
    this.englishTitle = englishTitle;
  }

  public String getSubsystem() {
    return subsystem;
  }

  public void setSubsystem(String subsystem) {
    this.subsystem = subsystem;
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
