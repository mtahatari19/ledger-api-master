package com.ledgerx.currency.entity;

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
@Table(name = "CURRENCIES")
public class Currency extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CURRENCY_CODE", nullable = false, length = 3, unique = true)
  @NotBlank(message = "{currency.validation.code.required}")
  @Size(min = 3, max = 3, message = "{currency.validation.code.size}")
  private String currencyCode;

  @Column(name = "CURRENCY_NUM_CODE", nullable = false, unique = true)
  @NotNull(message = "{currency.validation.numCode.required}")
  private Integer currencyNumCode;

  @Column(name = "SWIFT_CODE", nullable = false, length = 3)
  @NotBlank(message = "{currency.validation.swiftCode.required}")
  @Size(min = 3, max = 3, message = "{currency.validation.swiftCode.size}")
  private String swiftCode;

  @Column(name = "CURRENCY_NAME", nullable = false, length = 100)
  @NotBlank(message = "{currency.validation.name.required}")
  @Size(min = 2, max = 100, message = "{currency.validation.name.size}")
  private String currencyName;

  @Column(name = "SYMBOL", nullable = false, length = 10)
  @NotBlank(message = "{currency.validation.symbol.required}")
  @Size(min = 1, max = 10, message = "{currency.validation.symbol.size}")
  private String symbol;

  @Column(name = "DECIMAL_PRECISION", nullable = false)
  @NotNull(message = "{currency.validation.precision.required}")
  private Integer decimalPrecision;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false, length = 20)
  private Status status = Status.ACTIVE;

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public Integer getCurrencyNumCode() {
    return currencyNumCode;
  }

  public void setCurrencyNumCode(Integer currencyNumCode) {
    this.currencyNumCode = currencyNumCode;
  }

  public String getSwiftCode() {
    return swiftCode;
  }

  public void setSwiftCode(String swiftCode) {
    this.swiftCode = swiftCode;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Integer getDecimalPrecision() {
    return decimalPrecision;
  }

  public void setDecimalPrecision(Integer decimalPrecision) {
    this.decimalPrecision = decimalPrecision;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public enum Status {ACTIVE, INACTIVE}
}