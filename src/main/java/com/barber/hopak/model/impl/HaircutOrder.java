package com.barber.hopak.model.impl;

import com.barber.hopak.web.domain.impl.HaircutOrderDto;
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

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "haircut_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HaircutOrder implements com.barber.hopak.model.Entity<HaircutOrderDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    @Column(name = "haircut_id", nullable = false)
    private Long haircutId;
    @Column(name = "barber_id", nullable = false)
    private Long barberId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "haircut_id", insertable = false, updatable = false)
    private Haircut haircut;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barber_id", insertable = false, updatable = false)
    private Barber barber;

    @Override
    public HaircutOrderDto toDto() {
        return HaircutOrderDto.builder()
                .id(this.id)
                .description(this.description)
                .dateTime(this.dateTime)
                .clientId(this.clientId)
                .haircutId(this.haircutId)
                .barberId(this.barberId)
                .client(this.client == null ? null : this.client.toDto())
                .haircut(this.haircut == null ? null : this.haircut.toDto())
                .barber(this.barber == null ? null : this.barber.toDto())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HaircutOrder that = (HaircutOrder) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(dateTime, that.dateTime)) return false;
        if (!Objects.equals(clientId, that.clientId)) return false;
        if (!Objects.equals(haircutId, that.haircutId)) return false;
        if (!Objects.equals(barberId, that.barberId)) return false;
        if (!Objects.equals(client, that.client)) return false;
        if (!Objects.equals(haircut, that.haircut)) return false;
        return Objects.equals(barber, that.barber);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (haircutId != null ? haircutId.hashCode() : 0);
        result = 31 * result + (barberId != null ? barberId.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (haircut != null ? haircut.hashCode() : 0);
        result = 31 * result + (barber != null ? barber.hashCode() : 0);
        return result;
    }
}