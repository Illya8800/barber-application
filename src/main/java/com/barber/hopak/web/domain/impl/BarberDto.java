package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.BarberRankName;
import com.barber.hopak.constrain.InstagramNickName;
import com.barber.hopak.model.enumeration.BarberRank;
import com.barber.hopak.model.impl.Barber;
import com.barber.hopak.model.impl.Image;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.*;

@Builder
public class BarberDto implements DTO<Barber> {
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

    @Override
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

