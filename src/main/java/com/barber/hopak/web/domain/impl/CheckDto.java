package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.check.FutureDateTime;
import com.barber.hopak.model.impl.Check;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.barber.hopak.constrain.DtoConstraintMessage.CHECK_LOCAL_DATE_TIME_SHOULD_BE_IN_THE_FUTURE;
import static com.barber.hopak.constrain.DtoConstraintMessage.FIELD_SHOULD_NOT_BE_NULL;
import static com.barber.hopak.constrain.DtoConstraintMessage.LONG_MAX_VALUE_CONSTRAIN_TEXT;
import static com.barber.hopak.constrain.DtoConstraintMessage.MIN_VALUE_SHOULD_BE_MORE_THEN_0;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckDto implements DTO<Check> {
    private Long id;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @FutureDateTime(message = CHECK_LOCAL_DATE_TIME_SHOULD_BE_IN_THE_FUTURE)
    private LocalDateTime dateTime;
    private Long paymentId;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Max(value = Long.MAX_VALUE, message = LONG_MAX_VALUE_CONSTRAIN_TEXT)
    @Min(value = 1, message = MIN_VALUE_SHOULD_BE_MORE_THEN_0)
    private Long haircutId;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Max(value = Long.MAX_VALUE, message = LONG_MAX_VALUE_CONSTRAIN_TEXT)
    @Min(value = 1, message = MIN_VALUE_SHOULD_BE_MORE_THEN_0)
    private Long barberId;
    private Long clientId;

    private PaymentDto payment;
    private HaircutDto haircut;
    private BarberDto barber;
    private ClientDto client;

    @Override
    public Check toEntity() {
        return new Check(
                this.id,
                this.dateTime,
                this.paymentId,
                this.haircutId,
                this.barberId,
                this.clientId,
                this.payment == null ? null : this.payment.toEntity(),
                this.haircut == null ? null : this.haircut.toEntity(),
                this.barber == null ? null : this.barber.toEntity(),
                this.client == null ? null : this.client.toEntity()
        );
    }
}
