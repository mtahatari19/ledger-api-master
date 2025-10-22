package com.ledgerx.accountingrelationtype.control;

import com.ledger.core.control.AbstractBaseService;
import com.ledgerx.accountingrelationtype.boundary.AccountingRelationTypeRepository;
import com.ledgerx.accountingrelationtype.boundary.dto.AccountingRelationTypeDto;
import com.ledgerx.accountingrelationtype.boundary.dto.CreateAccountingRelationTypeRequest;
import com.ledgerx.accountingrelationtype.boundary.dto.UpdateAccountingRelationTypeRequest;
import com.ledgerx.accountingrelationtype.boundary.mapper.AccountingRelationTypeMapper;
import com.ledgerx.accountingrelationtype.entity.AccountingRelationType;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccountingRelationTypeService extends
    AbstractBaseService<AccountingRelationType, Long, CreateAccountingRelationTypeRequest, UpdateAccountingRelationTypeRequest, AccountingRelationTypeDto> {

  @Inject
  private AccountingRelationTypeMapper mapper;

  @Inject
  private AccountingRelationTypeRepository repository;

  // No-args constructor for CDI
  public AccountingRelationTypeService() {
    super();
  }

  public AccountingRelationTypeService(AccountingRelationTypeRepository repository) {
    super(repository);
  }

  @PostConstruct
  public void init() {
    // Set the repository after injection
    setRepository(repository);
  }

  @Override
  protected AccountingRelationType mapCreateDtoToEntity(
      CreateAccountingRelationTypeRequest createDto) {
    return mapper.toEntity(createDto);
  }

  @Override
  protected AccountingRelationTypeDto mapEntityToResponseDto(AccountingRelationType entity) {
    return mapper.toResponseDto(entity);
  }

  @Override
  protected void updateEntityFromDto(AccountingRelationType entity,
      UpdateAccountingRelationTypeRequest updateDto) {
    mapper.updateEntity(entity, updateDto);
  }

}
