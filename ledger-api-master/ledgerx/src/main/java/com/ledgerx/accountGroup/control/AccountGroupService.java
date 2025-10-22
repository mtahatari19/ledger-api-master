package com.ledgerx.accountGroup.control;

import com.ledger.core.control.AbstractBaseService;
import com.ledgerx.accountGroup.boundary.AccountGroupRepository;
import com.ledgerx.accountGroup.boundary.dto.AccountGroupDto;
import com.ledgerx.accountGroup.boundary.dto.CreateAccountGroupRequest;
import com.ledgerx.accountGroup.boundary.mapper.AccountGroupMapper;
import com.ledgerx.accountGroup.entity.AccountGroup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountGroupService extends
    AbstractBaseService<AccountGroup, Long, CreateAccountGroupRequest, CreateAccountGroupRequest, AccountGroupDto> {

  @Inject
  AccountGroupRepository repository;

  @Inject
  AccountGroupMapper mapper;

  // No-args constructor for CDI
  public AccountGroupService() {
    super();
  }

  public AccountGroupService(AccountGroupRepository repository) {
    super(repository);
  }

  @PostConstruct
  public void init() {
    // Set the repository after injection
    if (repository != null) {
      setRepository(repository);
    }
  }

  @Override
  protected AccountGroup mapCreateDtoToEntity(CreateAccountGroupRequest createDto) {
    return mapper.toEntity(createDto);
  }

  @Override
  protected AccountGroupDto mapEntityToResponseDto(AccountGroup entity) {
    return mapper.toResponseDto(entity);
  }

  @Override
  protected void updateEntityFromDto(AccountGroup entity, CreateAccountGroupRequest updateDto) {
    mapper.updateEntity(entity, updateDto);
  }
}
