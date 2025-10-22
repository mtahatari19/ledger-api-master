package com.ledgerx.accountingrelationtype.boundary;

import com.ledger.core.entity.BaseRepository;
import com.ledgerx.accountingrelationtype.entity.AccountingRelationType;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountingRelationTypeRepository implements
    BaseRepository<AccountingRelationType, Long> {
  // Repository methods will be implemented here
}