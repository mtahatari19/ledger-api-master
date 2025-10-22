package com.ledgerx.accountGroup.boundary.dto;

public record AccountGroupDto(
    Long id,
    String code,
    String name,
    String englishName,
    String groupType,
    Boolean status
) {

}
