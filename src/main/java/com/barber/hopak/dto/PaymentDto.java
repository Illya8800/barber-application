package com.barber.hopak.dto;

import com.barber.hopak.constrain.PaymentTypeName;
import com.barber.hopak.model.Payment;
import com.barber.hopak.model.enumeration.PaymentType;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.PAYMENT_DISCOUNT_SHOULD_BE_POSITIVE_VALUE_UP_TO_100;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.PAYMENT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.PAYMENT_TYPE_IS_UNKNOWN;

@Builder
public class PaymentDto {
    private Long id;
    @Size(message = PAYMENT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647)
    private Integer price;
    @Size(max = 100, message = PAYMENT_DISCOUNT_SHOULD_BE_POSITIVE_VALUE_UP_TO_100)
    private Integer discount;
    @Size(message = PAYMENT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647)
    private Integer finalPrice;
    @PaymentTypeName(message = PAYMENT_TYPE_IS_UNKNOWN)
    private PaymentType paymentType;

    public Payment toEntity() {
        return new Payment(
                this.id,
                this.price,
                this.discount,
                this.finalPrice,
                this.paymentType
        );
    }
}
