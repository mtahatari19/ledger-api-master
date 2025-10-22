package com.ledgerx.accountGroup.boundary.mapper;

import com.ledgerx.accountGroup.boundary.dto.AccountGroupDto;
import com.ledgerx.accountGroup.boundary.dto.CreateAccountGroupRequest;
import com.ledgerx.accountGroup.entity.AccountGroup;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi")
public interface AccountGroupMapper {

  AccountGroup toEntity(CreateAccountGroupRequest request);

  AccountGroupDto toResponseDto(AccountGroup entity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntity(@MappingTarget AccountGroup entity, CreateAccountGroupRequest request);
}

