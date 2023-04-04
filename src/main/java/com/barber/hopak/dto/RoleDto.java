package com.barber.hopak.dto;

import com.barber.hopak.constrain.RoleEnumName;
import com.barber.hopak.model.Role;
import com.barber.hopak.model.enumeration.RoleEnum;
import lombok.Builder;

@Builder
public class RoleDto {
    private Long id;
    @RoleEnumName
    private RoleEnum name;

    public Role toEntity() {
        return new Role(
                this.id,
                this.name
        );
    }
}
