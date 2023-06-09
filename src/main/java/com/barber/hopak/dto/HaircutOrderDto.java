package com.barber.hopak.dto;

import com.barber.hopak.constrain.FutureDateTime;
import com.barber.hopak.model.Barber;
import com.barber.hopak.model.Client;
import com.barber.hopak.model.Haircut;
import com.barber.hopak.model.HaircutOrder;
import jakarta.validation.Valid;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.HAIRCUT_ORDER_DATE_TIME_SHOULD_BE_IN_THE_FUTURE;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.HAIRCUT_ORDER_DESCRIPTION_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_255_CHARACTER;

@Builder
public class HaircutOrderDto {
    private Long id;
    @Valid
    private Client client;
    @Length(max = 255, message = HAIRCUT_ORDER_DESCRIPTION_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_255_CHARACTER)
    private String description;
    @FutureDateTime(message = HAIRCUT_ORDER_DATE_TIME_SHOULD_BE_IN_THE_FUTURE)
    private LocalDateTime dateTime;
    @Valid
    private Haircut haircut;
    @Valid
    private Barber barber;

    public HaircutOrder toEntity() {
        return new HaircutOrder(
                this.id,
                this.client,
                this.description,
                this.dateTime,
                this.haircut,
                this.barber
        );
    }
}
