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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "haircut")
@Getter
@Setter
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
    private Integer duration;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Haircut haircut = (Haircut) o;

        if (!Objects.equals(id, haircut.id)) return false;
        if (!Objects.equals(name, haircut.name)) return false;
        if (!Objects.equals(price, haircut.price)) return false;
        if (!Objects.equals(duration, haircut.duration)) return false;
        if (!Objects.equals(avatarId, haircut.avatarId)) return false;
        return Objects.equals(avatar, haircut.avatar);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (avatarId != null ? avatarId.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }
}
