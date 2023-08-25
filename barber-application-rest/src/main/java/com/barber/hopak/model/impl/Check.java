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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "check")
@Getter
@Setter
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
                .payment(this.payment == null ? null : this.payment.toDto())
                .haircut(this.haircut == null ? null : this.haircut.toDto())
                .barber(this.barber == null ? null : this.barber.toDto())
                .client(this.client == null ? null : this.client.toDto())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (!Objects.equals(id, check.id)) return false;
        if (!Objects.equals(dateTime, check.dateTime)) return false;
        if (!Objects.equals(paymentId, check.paymentId)) return false;
        if (!Objects.equals(haircutId, check.haircutId)) return false;
        if (!Objects.equals(barberId, check.barberId)) return false;
        if (!Objects.equals(clientId, check.clientId)) return false;
        if (!Objects.equals(payment, check.payment)) return false;
        if (!Objects.equals(haircut, check.haircut)) return false;
        if (!Objects.equals(barber, check.barber)) return false;
        return Objects.equals(client, check.client);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (paymentId != null ? paymentId.hashCode() : 0);
        result = 31 * result + (haircutId != null ? haircutId.hashCode() : 0);
        result = 31 * result + (barberId != null ? barberId.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        result = 31 * result + (haircut != null ? haircut.hashCode() : 0);
        result = 31 * result + (barber != null ? barber.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }
}