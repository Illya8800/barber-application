package com.barber.hopak.model.impl;

import com.barber.hopak.web.domain.impl.HaircutDto;
import jakarta.persistence.*;
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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private Image avatar;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Short duration;

    @Override
    public HaircutDto toDto() {
        return HaircutDto.builder()
                .id(this.id)
                .name(this.name)
                .avatar(this.avatar)
                .price(this.price)
                .duration(this.duration)
                .build();
    }
}
