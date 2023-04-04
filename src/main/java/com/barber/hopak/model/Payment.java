package com.barber.hopak.model;

import com.barber.hopak.dto.PaymentDto;
import com.barber.hopak.model.enumeration.PaymentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
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

    public PaymentDto toDto() {
        return PaymentDto.builder()
                .id(this.id)
                .price(this.price)
                .discount(this.discount)
                .finalPrice(this.finalPrice)
                .paymentType(this.paymentType)
                .build();
    }
}