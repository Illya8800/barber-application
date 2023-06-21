package com.barber.hopak.model.impl;

import com.barber.hopak.web.domain.impl.CheckDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;
    @Column(name = "haircut_id", nullable = false)
    private Long haircutId;
    @Column(name = "barber_id", nullable = false)
    private Long barberId;
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", insertable = false, updatable = false)
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
                .paymentId(this.paymentId)
                .haircutId(this.haircutId)
                .barberId(this.barberId)
                .clientId(this.clientId)
                .payment(this.payment)
                .haircut(this.haircut)
                .barber(this.barber)
                .client(this.client)
                .build();
    }

}