package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.check.FutureDateTime;
import com.barber.hopak.model.impl.Barber;
import com.barber.hopak.model.impl.Client;
import com.barber.hopak.model.impl.Haircut;
import com.barber.hopak.model.impl.HaircutOrder;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import static com.barber.hopak.constrain.DtoConstraintMessage.FIELD_SHOULD_NOT_BE_NULL;
import static com.barber.hopak.constrain.DtoConstraintMessage.HAIRCUT_ORDER_DATE_TIME_SHOULD_BE_IN_THE_FUTURE;
import static com.barber.hopak.constrain.DtoConstraintMessage.LONG_MAX_VALUE_CONSTRAIN_TEXT;
import static com.barber.hopak.constrain.DtoConstraintMessage.MIN_VALUE_SHOULD_BE_MORE_THEN_0;
import static com.barber.hopak.constrain.DtoConstraintMessage.STRING_SHOULD_BE_LESS_THEN_255_CHARACTER;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class HaircutOrderDto implements DTO<HaircutOrder> {
    private Long id;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Length(max = 255, message = STRING_SHOULD_BE_LESS_THEN_255_CHARACTER)
    private String description;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @FutureDateTime(message = HAIRCUT_ORDER_DATE_TIME_SHOULD_BE_IN_THE_FUTURE)
    private LocalDateTime dateTime;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Max(value = Long.MAX_VALUE, message = LONG_MAX_VALUE_CONSTRAIN_TEXT)
    @Min(value = 1, message = MIN_VALUE_SHOULD_BE_MORE_THEN_0)
    private Long clientId;
    private Long haircutId;
    private Long barberId;

    private Client client;
    private Haircut haircut;
    private Barber barber;

    @Override
    public HaircutOrder toEntity() {
        return new HaircutOrder(
                this.id,
                this.description,
                this.dateTime,
                this.clientId,
                this.haircutId,
                this.barberId,
                this.client,
                this.haircut,
                this.barber
        );
    }
}
