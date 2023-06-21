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
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "haircut_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
                .client(this.client)
                .haircut(this.haircut)
                .barber(this.barber)
                .build();
    }
}