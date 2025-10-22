package com.ledgerx.account.boundary.mapper;

import com.ledger.core.boundary.mapper.BaseMapper;
import com.ledgerx.account.boundary.dto.AccountDto;
import com.ledgerx.account.boundary.dto.CreateAccountRequest;
import com.ledgerx.account.boundary.dto.UpdateAccountRequest;
import com.ledgerx.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface AccountMapper extends
    BaseMapper<Account, Long, CreateAccountRequest, UpdateAccountRequest, AccountDto> {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "balance", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  Account toEntity(CreateAccountRequest request);

  AccountDto toResponseDto(Account entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "balance", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  @Mapping(source = "request.accountNumber", target = "accountNumber")
  @Mapping(source = "request.accountName", target = "accountName")
  @Mapping(source = "request.accountTypeId", target = "accountTypeId")
  @Mapping(source = "request.currencyId", target = "currencyId")
  @Mapping(source = "request.partyId", target = "partyId")
  @Mapping(source = "request.organizationalUnitId", target = "organizationalUnitId")
  @Mapping(source = "request.status", target = "status")
  @Mapping(source = "request.description", target = "description")
  default void updateEntity(Account entity, UpdateAccountRequest request) {
    if (request.accountNumber() != null) {
      entity.setAccountNumber(request.accountNumber());
    }
    if (request.accountName() != null) {
      entity.setAccountName(request.accountName());
    }
    if (request.accountTypeId() != null) {
      entity.setAccountTypeId(request.accountTypeId());
    }
    if (request.currencyId() != null) {
      entity.setCurrencyId(request.currencyId());
    }
    if (request.partyId() != null) {
      entity.setPartyId(request.partyId());
    }
    if (request.organizationalUnitId() != null) {
      entity.setOrganizationalUnitId(request.organizationalUnitId());
    }
    if (request.status() != null) {
      entity.setStatus(Account.Status.valueOf(request.status()));
    }
    if (request.description() != null) {
      entity.setDescription(request.description());
    }
  }
}
