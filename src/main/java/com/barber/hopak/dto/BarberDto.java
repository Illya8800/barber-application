package com.barber.hopak.dto;

import com.barber.hopak.constrain.BarberRankName;
import com.barber.hopak.constrain.InstagramNickName;
import com.barber.hopak.model.Barber;
import com.barber.hopak.model.Image;
import com.barber.hopak.model.enumeration.BarberRank;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.BARBER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.BARBER_INSTAGRAM_SHOULD_BE_CORRECT;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE;

@Builder
public class BarberDto {
    private Long id;
    @NotBlank(message = FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE)
    @Length(min = 1, max = 30, message = BARBER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    private String firstname;
    @NotBlank(message = FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE)
    @InstagramNickName(message = BARBER_INSTAGRAM_SHOULD_BE_CORRECT)
    private String instagram;
    @BarberRankName(message = "Цього звання барбера не існує")
    private BarberRank rank;
    @Valid
    private Image avatar;

    public Barber toEntity() {
        return new Barber(
                this.id,
                this.firstname,
                this.instagram,
                this.rank,
                this.avatar
        );
    }

}

