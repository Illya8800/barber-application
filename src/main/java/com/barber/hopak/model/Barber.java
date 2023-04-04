package com.barber.hopak.model;

import com.barber.hopak.dto.BarberDto;
import com.barber.hopak.model.enumeration.BarberRank;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "barber")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Barber {
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
