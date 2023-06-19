package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.BarberRankName;
import com.barber.hopak.constrain.instagram.InstagramNickName;
import com.barber.hopak.model.enumeration.BarberRank;
import com.barber.hopak.model.impl.Barber;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.BARBER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.BARBER_INSTAGRAM_SHOULD_BE_CORRECT;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BarberDto implements DTO<Barber> {
    private Long id;
    @NotBlank(message = FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE)
    @Length(min = 1, max = 30, message = BARBER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    private String barberName;
    @NotBlank(message = FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE)
    @InstagramNickName(message = BARBER_INSTAGRAM_SHOULD_BE_CORRECT)
    private String instagram;
    @BarberRankName(message = "Цього звання барбера не існує")
    private BarberRank barberRank;
    private ImageDto avatar;

    @Override
    public Barber toEntity() {
        return new Barber(
                this.id,
                this.barberName,
                this.instagram,
                this.barberRank,
                this.avatar.toEntity()
        );
    }
}

