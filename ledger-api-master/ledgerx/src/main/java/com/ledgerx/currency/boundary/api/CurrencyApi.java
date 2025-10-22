package com.ledgerx.currency.boundary.api;

import com.ledger.core.boundary.api.BaseApi;
import com.ledgerx.currency.boundary.dto.CreateCurrencyRequest;
import com.ledgerx.currency.boundary.dto.CurrencyDto;
import com.ledgerx.currency.boundary.dto.UpdateCurrencyRequest;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Currencies", description = "Currency management operations")
public interface CurrencyApi extends
    BaseApi<Long, CurrencyDto, CreateCurrencyRequest, UpdateCurrencyRequest> {

}
