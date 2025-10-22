package com.ledgerx.goods.boundary.http;

import com.ledger.core.boundary.http.BaseController;
import com.ledgerx.goods.boundary.api.GoodsApi;
import com.ledgerx.goods.boundary.dto.CreateGoodsRequest;
import com.ledgerx.goods.boundary.dto.GoodsDto;
import com.ledgerx.goods.boundary.dto.UpdateGoodsRequest;
import com.ledgerx.goods.control.GoodsService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * REST controller for Goods operations.
 * 
 * <p>This controller handles HTTP requests for Goods management,
 * extending the base controller functionality for CRUD operations.</p>
 */
@Path("/goods")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Goods", description = "Operations related to goods management")
public class GoodsController extends BaseController<Long, GoodsDto, CreateGoodsRequest, UpdateGoodsRequest> implements GoodsApi {

    @Inject
    GoodsService goodsService;

    @PostConstruct
    public void init() {
        setService(goodsService);
    }
}
