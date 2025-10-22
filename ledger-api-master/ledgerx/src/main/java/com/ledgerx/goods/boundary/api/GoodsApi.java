package com.ledgerx.goods.boundary.api;

import com.ledger.core.boundary.api.BaseApi;
import com.ledgerx.goods.boundary.dto.CreateGoodsRequest;
import com.ledgerx.goods.boundary.dto.GoodsDto;
import com.ledgerx.goods.boundary.dto.UpdateGoodsRequest;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * API interface for Goods operations.
 * 
 * <p>This interface defines the REST endpoints for Goods management,
 * extending the base API functionality for CRUD operations.</p>
 */
@Tag(name = "Goods", description = "Operations related to goods management")
public interface GoodsApi extends BaseApi<Long, GoodsDto, CreateGoodsRequest, UpdateGoodsRequest> {

}
