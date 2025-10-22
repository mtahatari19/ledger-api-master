package com.ledgerx.producttype.boundary;

import com.ledger.core.entity.BaseRepository;
import com.ledgerx.producttype.entity.ProductType;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductTypeRepository implements BaseRepository<ProductType, Long> {
  // Repository methods will be implemented here
}