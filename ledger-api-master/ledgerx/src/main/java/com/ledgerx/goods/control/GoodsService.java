package com.ledgerx.goods.control;

import com.ledger.core.control.AbstractBaseService;
import com.ledgerx.goods.boundary.GoodsRepository;
import com.ledgerx.goods.boundary.dto.CreateGoodsRequest;
import com.ledgerx.goods.boundary.dto.GoodsDto;
import com.ledgerx.goods.boundary.dto.UpdateGoodsRequest;
import com.ledgerx.goods.boundary.mapper.GoodsMapper;
import com.ledgerx.goods.entity.Goods;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Service class for Goods business logic operations.
 * 
 * <p>This service handles all business logic related to Goods entities,
 * extending the base service functionality for CRUD operations.</p>
 */
@ApplicationScoped
public class GoodsService extends AbstractBaseService<Goods, Long, CreateGoodsRequest, UpdateGoodsRequest, GoodsDto> {

    @Inject
    private GoodsRepository repository;

    @Inject
    private GoodsMapper mapper;

    // No-args constructor for CDI
    public GoodsService() {
        super();
    }

    public GoodsService(GoodsRepository repository) {
        super(repository);
    }

    @PostConstruct
    public void init() {
        // Set the repository after injection
        if (repository != null) {
            setRepository(repository);
        }
    }

    @Override
    protected Goods mapCreateDtoToEntity(CreateGoodsRequest createDto) {
        return mapper.toEntity(createDto);
    }

    @Override
    protected GoodsDto mapEntityToResponseDto(Goods entity) {
        return mapper.toResponseDto(entity);
    }

    @Override
    protected void updateEntityFromDto(Goods entity, UpdateGoodsRequest updateDto) {
        mapper.updateEntity(entity, updateDto);
    }
}
