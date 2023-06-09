package com.barber.hopak.model;

import com.barber.hopak.dto.HaircutOrderDto;
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
public class HaircutOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    private String description;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "haircut_id", insertable = false, updatable = false)
    private Haircut haircut;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barber_id", insertable = false, updatable = false)
    private Barber barber;

    public HaircutOrderDto toDto() {
        return HaircutOrderDto.builder()
                .id(this.id)
                .client(this.client)
                .description(this.description)
                .dateTime(this.dateTime)
                .haircut(this.haircut)
                .barber(this.barber)
                .build();
    }
}