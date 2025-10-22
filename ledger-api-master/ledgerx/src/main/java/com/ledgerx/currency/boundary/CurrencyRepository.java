package com.ledgerx.currency.boundary;

import com.ledger.core.entity.BaseRepository;
import com.ledgerx.currency.entity.Currency;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CurrencyRepository implements BaseRepository<Currency, Long> {
  // Repository methods will be implemented here
}
