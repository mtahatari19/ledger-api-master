package com.ledgerx.currency.control;

import com.ledger.core.control.AbstractBaseService;
import com.ledgerx.currency.boundary.CurrencyRepository;
import com.ledgerx.currency.boundary.dto.CreateCurrencyRequest;
import com.ledgerx.currency.boundary.dto.CurrencyDto;
import com.ledgerx.currency.boundary.dto.UpdateCurrencyRequest;
import com.ledgerx.currency.boundary.mapper.CurrencyMapper;
import com.ledgerx.currency.entity.Currency;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CurrencyService extends
    AbstractBaseService<Currency, Long, CreateCurrencyRequest, UpdateCurrencyRequest, CurrencyDto> {

  @Inject
  private CurrencyRepository repository;

  @Inject
  private CurrencyMapper mapper;

  // No-args constructor for CDI
  public CurrencyService() {
    super();
  }

  public CurrencyService(CurrencyRepository repository) {
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
  protected Currency mapCreateDtoToEntity(CreateCurrencyRequest createDto) {
    return mapper.toEntity(createDto);
  }

  @Override
  protected CurrencyDto mapEntityToResponseDto(Currency entity) {
    return mapper.toResponseDto(entity);
  }

  @Override
  protected void updateEntityFromDto(Currency entity, UpdateCurrencyRequest updateDto) {
    mapper.updateEntity(entity, updateDto);
  }
}
