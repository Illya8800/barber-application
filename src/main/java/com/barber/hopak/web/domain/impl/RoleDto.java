package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.RoleEnumName;
import com.barber.hopak.model.enumeration.RoleEnum;
import com.barber.hopak.model.impl.Role;
import com.barber.hopak.web.domain.DTO;
import lombok.Builder;

@Builder
public class RoleDto implements DTO<Role> {
    private Long id;
    @RoleEnumName
    private RoleEnum name;

    @Override
    public Role toEntity() {
        return new Role(
                this.id,
                this.name
        );
    }
}
