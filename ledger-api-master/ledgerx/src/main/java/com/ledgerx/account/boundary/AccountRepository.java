package com.ledgerx.account.boundary;

import com.ledger.core.entity.BaseRepository;
import com.ledgerx.account.entity.Account;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements BaseRepository<Account, Long> {

}
