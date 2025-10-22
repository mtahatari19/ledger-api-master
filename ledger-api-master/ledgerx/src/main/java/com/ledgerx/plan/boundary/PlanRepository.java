package com.ledgerx.plan.boundary;

import com.ledger.core.entity.BaseRepository;
import com.ledgerx.plan.entity.Plan;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlanRepository implements BaseRepository<Plan, Long> {
  // Repository methods will be implemented here
}
