package com.ledgerx.accountingrelationtype.boundary.mapper;

import com.ledger.core.boundary.mapper.BaseMapper;
import com.ledgerx.accountingrelationtype.boundary.dto.AccountingRelationTypeDto;
import com.ledgerx.accountingrelationtype.boundary.dto.CreateAccountingRelationTypeRequest;
import com.ledgerx.accountingrelationtype.boundary.dto.UpdateAccountingRelationTypeRequest;
import com.ledgerx.accountingrelationtype.entity.AccountingRelationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface AccountingRelationTypeMapper extends
    BaseMapper<AccountingRelationType, Long, CreateAccountingRelationTypeRequest, UpdateAccountingRelationTypeRequest, AccountingRelationTypeDto> {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  AccountingRelationType toEntity(CreateAccountingRelationTypeRequest request);

  AccountingRelationTypeDto toResponseDto(AccountingRelationType entity);

  // Manual implementation for updateEntity since MapStruct can't generate void methods
  default void updateEntity(AccountingRelationType entity,
      UpdateAccountingRelationTypeRequest request) {
    if (request.accountingRelationCode() != null) {
      entity.setAccountingRelationCode(request.accountingRelationCode());
    }
    if (request.persianTitle() != null) {
      entity.setPersianTitle(request.persianTitle());
    }
    if (request.englishTitle() != null) {
      entity.setEnglishTitle(request.englishTitle());
    }
    if (request.subsystem() != null) {
      entity.setSubsystem(request.subsystem());
    }
    if (request.productType() != null) {
      entity.setProductType(request.productType());
    }
    if (request.summary() != null) {
      entity.setDescription(request.summary());
    }
    if (request.status() != null) {
      entity.setStatus(AccountingRelationType.Status.valueOf(request.status()));
    }
  }
}
