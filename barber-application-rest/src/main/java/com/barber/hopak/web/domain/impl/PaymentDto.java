package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.payment.DiscountValidCheck;
import com.barber.hopak.constrain.payment.PaymentTypeName;
import com.barber.hopak.model.enumeration.PaymentType;
import com.barber.hopak.model.impl.Payment;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.barber.hopak.constrain.DtoConstraintMessage.FIELD_SHOULD_NOT_BE_NULL;
import static com.barber.hopak.constrain.DtoConstraintMessage.INTEGER_MAX_VALUE_CONSTRAIN_TEXT;
import static com.barber.hopak.constrain.DtoConstraintMessage.MIN_VALUE_SHOULD_BE_MORE_THEN_0;
import static com.barber.hopak.constrain.DtoConstraintMessage.PAYMENT_TYPE_IS_UNKNOWN;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscountValidCheck
public class PaymentDto implements DTO<Payment> {
    private Long id;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Max(value = Integer.MAX_VALUE, message = INTEGER_MAX_VALUE_CONSTRAIN_TEXT)
    @Min(value = 1, message = MIN_VALUE_SHOULD_BE_MORE_THEN_0)
    private Integer price;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Max(value = Integer.MAX_VALUE, message = INTEGER_MAX_VALUE_CONSTRAIN_TEXT)
    @Min(value = 1, message = MIN_VALUE_SHOULD_BE_MORE_THEN_0)
    private Integer discount;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Max(value = Integer.MAX_VALUE, message = INTEGER_MAX_VALUE_CONSTRAIN_TEXT)
    @Min(value = 1, message = MIN_VALUE_SHOULD_BE_MORE_THEN_0)
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
