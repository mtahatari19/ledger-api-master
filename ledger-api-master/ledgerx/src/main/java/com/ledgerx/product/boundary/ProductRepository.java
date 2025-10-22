package com.ledgerx.product.boundary;

import com.ledger.core.entity.BaseRepository;
import com.ledgerx.product.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements BaseRepository<Product, Long> {
  // Repository methods will be implemented here
}