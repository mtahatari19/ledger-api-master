package com.ledgerx.currency.boundary.mapper;

import com.ledger.core.boundary.mapper.BaseMapper;
import com.ledgerx.currency.boundary.dto.CreateCurrencyRequest;
import com.ledgerx.currency.boundary.dto.CurrencyDto;
import com.ledgerx.currency.boundary.dto.UpdateCurrencyRequest;
import com.ledgerx.currency.entity.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface CurrencyMapper extends
    BaseMapper<Currency, Long, CreateCurrencyRequest, UpdateCurrencyRequest, CurrencyDto> {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  Currency toEntity(CreateCurrencyRequest request);

  CurrencyDto toResponseDto(Currency entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  @Mapping(source = "request.currencyCode", target = "currencyCode")
  @Mapping(source = "request.currencyNumCode", target = "currencyNumCode")
  @Mapping(source = "request.swiftCode", target = "swiftCode")
  @Mapping(source = "request.currencyName", target = "currencyName")
  @Mapping(source = "request.symbol", target = "symbol")
  @Mapping(source = "request.decimalPrecision", target = "decimalPrecision")
  @Mapping(source = "request.status", target = "status")
  default void updateEntity(Currency entity, UpdateCurrencyRequest request) {
    if (request.currencyCode() != null) {
      entity.setCurrencyCode(request.currencyCode());
    }
    if (request.currencyNumCode() != null) {
      entity.setCurrencyNumCode(request.currencyNumCode());
    }
    if (request.swiftCode() != null) {
      entity.setSwiftCode(request.swiftCode());
    }
    if (request.currencyName() != null) {
      entity.setCurrencyName(request.currencyName());
    }
    if (request.symbol() != null) {
      entity.setSymbol(request.symbol());
    }
    if (request.decimalPrecision() != null) {
      entity.setDecimalPrecision(request.decimalPrecision());
    }
    if (request.status() != null) {
      entity.setStatus(Currency.Status.valueOf(request.status()));
    }
  }
}
