package com.barber.hopak.model.impl;

import com.barber.hopak.model.enumeration.RoleEnum;
import com.barber.hopak.web.domain.impl.RoleDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements com.barber.hopak.model.Entity<RoleDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RoleEnum name;

    @Override
    public RoleDto toDto() {
        return RoleDto.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}