package com.barber.hopak.model.impl;

import com.barber.hopak.model.enumeration.BarberRank;
import com.barber.hopak.web.domain.impl.BarberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "barber")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Barber implements com.barber.hopak.model.Entity<BarberDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String firstname;
    @Column(nullable = false, length = 30)
    private String instagram;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('BARBER','TRAINEE_BARBER')", nullable = false)
    private BarberRank rank;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private Image avatar;

    @Override
    public BarberDto toDto() {
        return BarberDto.builder()
                .id(this.id)
                .firstname(this.firstname)
                .instagram(this.instagram)
                .rank(this.rank)
                .avatar(this.avatar)
                .build();
    }
}
