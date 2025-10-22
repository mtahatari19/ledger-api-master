package com.ledgerx.account.control;

import com.ledger.core.control.AbstractBaseService;
import com.ledgerx.account.boundary.AccountRepository;
import com.ledgerx.account.boundary.dto.AccountDto;
import com.ledgerx.account.boundary.dto.CreateAccountRequest;
import com.ledgerx.account.boundary.dto.UpdateAccountRequest;
import com.ledgerx.account.boundary.mapper.AccountMapper;
import com.ledgerx.account.entity.Account;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountService extends
    AbstractBaseService<Account, Long, CreateAccountRequest, UpdateAccountRequest, AccountDto> {

  @Inject
  private AccountMapper mapper;

  @Inject
  private AccountRepository repository;

  // No-args constructor for CDI
  public AccountService() {
    super();
  }

  public AccountService(AccountRepository repository) {
    super(repository);
  }

  @PostConstruct
  public void init() {
    // Set the repository after injection
    setRepository(repository);
  }

  @Override
  protected Account mapCreateDtoToEntity(CreateAccountRequest createDto) {
    return mapper.toEntity(createDto);
  }

  @Override
  protected AccountDto mapEntityToResponseDto(Account entity) {
    return mapper.toResponseDto(entity);
  }

  @Override
  protected void updateEntityFromDto(Account entity, UpdateAccountRequest updateDto) {
    mapper.updateEntity(entity, updateDto);
  }
}