package com.barber.hopak.dto;

import com.barber.hopak.constrain.UniqueHaircutName;
import com.barber.hopak.model.Haircut;
import com.barber.hopak.model.Image;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.HAIRCUT_DURATION_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_32767;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.HAIRCUT_NAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.HAIRCUT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647;

@Builder
public class HaircutDto {
    private Long id;
    @Length(min = 1, max = 30, message = HAIRCUT_NAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    @UniqueHaircutName
    private String name;
    @Valid
    private Image avatar;
    @Size(min = 1, message = HAIRCUT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647)
    private Integer price;
    @Size(min = 1, max = Short.MAX_VALUE, message = HAIRCUT_DURATION_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_32767)
    private Short duration;

    public Haircut toEntity() {
        return new Haircut(
                this.id,
                this.name,
                this.avatar,
                this.price,
                this.duration
        );
    }
}

