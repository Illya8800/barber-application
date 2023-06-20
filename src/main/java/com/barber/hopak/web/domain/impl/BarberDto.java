package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.barber.BarberRankName;
import com.barber.hopak.constrain.instagram.InstagramNickName;
import com.barber.hopak.model.enumeration.BarberRank;
import com.barber.hopak.model.impl.Barber;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.BARBER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.BARBER_INSTAGRAM_SHOULD_BE_CORRECT;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.BARBER_RANK_SHOULD_BE_CORRECT;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.FIELD_SHOULD_NOT_BE_NULL;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.PAYMENT_DISCOUNT_SHOULD_BE_POSITIVE_VALUE_UP_TO_100;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BarberDto implements DTO<Barber> {
    private Long id;
    @NotBlank(message = FIELD_SHOULD_NOT_BE_NULL)
    @Length(min = 1, max = 30, message = BARBER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    private String barberName;
    @NotBlank(message = FIELD_SHOULD_NOT_BE_NULL)
    @InstagramNickName(message = BARBER_INSTAGRAM_SHOULD_BE_CORRECT)
    private String instagram;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @BarberRankName(message = BARBER_RANK_SHOULD_BE_CORRECT)
    private BarberRank barberRank;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Digits(integer = 18, fraction = 1, message = PAYMENT_DISCOUNT_SHOULD_BE_POSITIVE_VALUE_UP_TO_100)
    private Long avatarId;
    private ImageDto avatar;

    @Override
    public Barber toEntity() {
        return new Barber(
                this.id,
                this.barberName,
                this.instagram,
                this.barberRank,
                this.avatarId,
                this.avatar.toEntity()
        );
    }
}

