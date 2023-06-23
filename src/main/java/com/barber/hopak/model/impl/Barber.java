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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.util.Objects;

@Entity
@Table(name = "barber")
@Getter
@Setter
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
    @Column(name = "avatar_id")
    private Long avatarId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.PROXY)
    private Image avatar;

    @Override
    public BarberDto toDto() {
        return BarberDto.builder()
                .id(this.id)
                .barberName(this.barberName)
                .instagram(this.instagram)
                .barberRank(this.barberRank)
                .avatarId(this.avatarId)
                .avatar(this.avatar!= null && Hibernate.isInitialized(this.avatar) ? this.avatar.toDto() : null)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Barber barber = (Barber) o;

        if (!Objects.equals(id, barber.id)) return false;
        if (!Objects.equals(barberName, barber.barberName)) return false;
        if (!Objects.equals(instagram, barber.instagram)) return false;
        if (barberRank != barber.barberRank) return false;
        if (!Objects.equals(avatarId, barber.avatarId)) return false;
        return Objects.equals(avatar, barber.avatar);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (barberName != null ? barberName.hashCode() : 0);
        result = 31 * result + (instagram != null ? instagram.hashCode() : 0);
        result = 31 * result + (barberRank != null ? barberRank.hashCode() : 0);
        result = 31 * result + (avatarId != null ? avatarId.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }
}