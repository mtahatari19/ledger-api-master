package com.ledgerx.goods.boundary;

import com.ledger.core.entity.BaseRepository;
import com.ledgerx.goods.entity.Goods;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

/**
 * Repository interface for Goods entity operations.
 * 
 * <p>This repository provides data access methods for Goods entities,
 * extending the base repository functionality.</p>
 */
@jakarta.enterprise.context.ApplicationScoped
public class GoodsRepository implements PanacheRepository<Goods>, BaseRepository<Goods, Long> {
    
    // No custom methods needed - using base repository functionality
    // All CRUD operations are inherited from BaseRepository and PanacheRepository
}
