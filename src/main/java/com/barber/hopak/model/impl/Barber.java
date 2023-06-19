package com.barber.hopak.model.impl;

import com.barber.hopak.model.enumeration.BarberRank;
import com.barber.hopak.web.domain.impl.BarberDto;
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
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

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
    private String barberName;
    @Column(nullable = false, length = 30)
    private String instagram;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('BARBER_APPRENTICE','BARBER_TRAINEE','JUNIOR_BARBER','BARBER','TOP_BARBER','BARBER_SENIOR','CHEF_BARBER')", nullable = false)
    private BarberRank barberRank;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    @LazyToOne(LazyToOneOption.PROXY)
    private Image avatar;

    @Override
    public BarberDto toDto() {
        return BarberDto.builder()
                .id(this.id)
                .barberName(this.barberName)
                .instagram(this.instagram)
                .barberRank(this.barberRank)
                .avatar(Hibernate.isInitialized(this.avatar) ? this.avatar.toDto() : null)
                .build();
    }
}