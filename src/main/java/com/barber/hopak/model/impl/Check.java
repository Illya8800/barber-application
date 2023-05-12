package com.barber.hopak.model.impl;

import com.barber.hopak.web.domain.impl.CheckDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "check")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Check implements com.barber.hopak.model.Entity<CheckDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime dateTime;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "haircut_id", insertable = false, updatable = false)
    private Haircut haircut;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barber_id", insertable = false, updatable = false)
    private Barber barber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    @Override
    public CheckDto toDto() {
        return CheckDto.builder()
                .id(this.id)
                .dateTime(this.dateTime)
                .payment(this.payment)
                .haircut(this.haircut)
                .barber(this.barber)
                .client(this.client)
                .build();
    }

}