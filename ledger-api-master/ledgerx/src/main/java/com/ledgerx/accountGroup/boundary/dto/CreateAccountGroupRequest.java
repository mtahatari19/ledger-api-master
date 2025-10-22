package com.ledgerx.accountGroup.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAccountGroupRequest(
    @NotBlank(message = "{accountGroup.validation.code.required}")
    String code,

    @NotBlank(message = "{accountGroup.validation.name.required}")
    @Size(max = 200, message = "{accountGroup.validation.name.size}")
    String name,

    @Size(max = 200, message = "{accountGroup.validation.englishName.size}")
    String englishName,

    @NotBlank(message = "{accountGroup.validation.groupType.required}")
    String groupType,

    @NotNull(message = "{accountGroup.validation.status.required}")
    Boolean status
) {

}
