package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.payment.DiscountInPercent;
import com.barber.hopak.constrain.payment.PaymentTypeName;
import com.barber.hopak.model.enumeration.PaymentType;
import com.barber.hopak.model.impl.Payment;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.FIELD_SHOULD_NOT_BE_NULL;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.PAYMENT_DISCOUNT_SHOULD_BE_POSITIVE_VALUE_UP_TO_100;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.PAYMENT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.PAYMENT_TYPE_IS_UNKNOWN;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PaymentDto implements DTO<Payment> {
    private Long id;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Digits(integer = 6, fraction = 1, message = PAYMENT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647)
    private Integer price;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @DiscountInPercent(message = PAYMENT_DISCOUNT_SHOULD_BE_POSITIVE_VALUE_UP_TO_100)
    private Integer discount;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Digits(integer = 6, fraction = 1, message = PAYMENT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647)
    private Integer finalPrice;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @PaymentTypeName(message = PAYMENT_TYPE_IS_UNKNOWN)
    private PaymentType paymentType;

    @Override
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
