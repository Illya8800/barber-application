package com.barber.hopak.model;

import com.barber.hopak.dto.HaircutDto;
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
public class Haircut {
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
