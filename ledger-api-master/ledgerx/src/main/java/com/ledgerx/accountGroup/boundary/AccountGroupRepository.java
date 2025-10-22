package com.ledgerx.accountGroup.boundary;

import com.ledger.core.entity.BaseRepository;
import com.ledgerx.accountGroup.entity.AccountGroup;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountGroupRepository implements BaseRepository<AccountGroup, Long> {
  // Repository methods will be implemented here
}