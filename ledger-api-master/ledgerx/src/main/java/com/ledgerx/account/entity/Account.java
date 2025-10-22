package com.ledgerx.account.entity;

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
@Table(name = "ACCOUNTS")
public class Account extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 50, unique = true)
  @NotBlank(message = "{account.validation.number.required}")
  @Size(min = 2, max = 50, message = "{account.validation.number.size}")
  private String accountNumber;

  @Column(name = "ACCOUNT_NAME", nullable = false, length = 200)
  @NotBlank(message = "{account.validation.name.required}")
  @Size(min = 2, max = 200, message = "{account.validation.name.size}")
  private String accountName;

  @Column(name = "ACCOUNT_TYPE_ID", nullable = false)
  @NotNull(message = "{account.validation.type.required}")
  private Long accountTypeId;

  @Column(name = "CURRENCY_ID", nullable = false)
  @NotNull(message = "{account.validation.currency.required}")
  private Long currencyId;

  @Column(name = "PARTY_ID", nullable = false)
  @NotNull(message = "{account.validation.party.required}")
  private Long partyId;

  @Column(name = "ORGANIZATIONAL_UNIT_ID", nullable = false)
  @NotNull(message = "{account.validation.organizationalUnit.required}")
  private Long organizationalUnitId;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false, length = 20)
  private Status status = Status.ACTIVE;

  @Column(name = "DESCRIPTION", length = 500)
  private String description;

  @Column(name = "BALANCE", nullable = false)
  private Double balance = 0.0;

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public Long getAccountTypeId() {
    return accountTypeId;
  }

  public void setAccountTypeId(Long accountTypeId) {
    this.accountTypeId = accountTypeId;
  }

  public Long getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(Long currencyId) {
    this.currencyId = currencyId;
  }

  public Long getPartyId() {
    return partyId;
  }

  public void setPartyId(Long partyId) {
    this.partyId = partyId;
  }

  public Long getOrganizationalUnitId() {
    return organizationalUnitId;
  }

  public void setOrganizationalUnitId(Long organizationalUnitId) {
    this.organizationalUnitId = organizationalUnitId;
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

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public enum Status {ACTIVE, INACTIVE, SUSPENDED, CLOSED}
}