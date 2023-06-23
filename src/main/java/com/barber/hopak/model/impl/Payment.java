package com.barber.hopak.model.impl;

import com.barber.hopak.model.enumeration.PaymentType;
import com.barber.hopak.web.domain.impl.PaymentDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements com.barber.hopak.model.Entity<PaymentDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer price;
    private Integer discount;
    @Column(nullable = false)
    private Integer finalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", columnDefinition = "enum('CARD','CASH')", nullable = false)
    private PaymentType paymentType;

    @Override
    public PaymentDto toDto() {
        return PaymentDto.builder()
                .id(this.id)
                .price(this.price)
                .discount(this.discount)
                .finalPrice(this.finalPrice)
                .paymentType(this.paymentType)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (!Objects.equals(id, payment.id)) return false;
        if (!Objects.equals(price, payment.price)) return false;
        if (!Objects.equals(discount, payment.discount)) return false;
        if (!Objects.equals(finalPrice, payment.finalPrice)) return false;
        return paymentType == payment.paymentType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (finalPrice != null ? finalPrice.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        return result;
    }
}