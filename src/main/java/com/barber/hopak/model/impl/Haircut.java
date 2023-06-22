package com.barber.hopak.model.impl;

import com.barber.hopak.web.domain.impl.HaircutDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "haircut")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Haircut implements com.barber.hopak.model.Entity<HaircutDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 30)
    private String name;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Short duration;
    @Column(name = "avatar_id")
    private Long avatarId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", insertable = false, updatable = false)
    private Image avatar;

    @Override
    public HaircutDto toDto() {
        return HaircutDto.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .duration(this.duration)
                .avatarId(this.avatarId)
                .avatar(this.avatar == null ? null : this.avatar.toDto())
                .build();
    }
}
