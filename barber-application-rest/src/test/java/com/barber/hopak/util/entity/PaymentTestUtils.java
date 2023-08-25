package com.barber.hopak.util.entity;

import com.barber.hopak.model.enumeration.PaymentType;
import com.barber.hopak.web.domain.impl.PaymentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentTestUtils {

    public static final Long EXISTING_PAYMENT_ID = 1L;
    public static final Long UNEXISTING_PAYMENT_ID = -1L;

    public PaymentDto getPaymentDto() {
        return PaymentDto.builder()
                .id(EXISTING_PAYMENT_ID)
                .price(100)
                .discount(5)
                .finalPrice(95)
                .paymentType(PaymentType.CASH)
                .build();
    }

    public List<PaymentDto> getPaymentDtoList() {
        PaymentDto firstPayment = PaymentDto.builder()
                .id(EXISTING_PAYMENT_ID)
                .price(100)
                .discount(5)
                .finalPrice(95)
                .paymentType(PaymentType.CASH)
                .build();

        PaymentDto secondPayment = PaymentDto.builder()
                .id(EXISTING_PAYMENT_ID)
                .price(333)
                .discount(10)
                .finalPrice(323)
                .paymentType(PaymentType.CASH)
                .build();

        PaymentDto thirdPayment = PaymentDto.builder()
                .id(EXISTING_PAYMENT_ID)
                .price(50)
                .discount(0)
                .finalPrice(50)
                .paymentType(PaymentType.CASH)
                .build();

        return List.of(firstPayment, secondPayment, thirdPayment);
    }
}
