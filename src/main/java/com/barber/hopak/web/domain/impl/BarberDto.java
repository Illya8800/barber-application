package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.barber.BarberRankName;
import com.barber.hopak.model.enumeration.BarberRank;
import com.barber.hopak.model.impl.Barber;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static com.barber.hopak.constrain.DtoConstraintMessage.BARBER_RANK_SHOULD_BE_CORRECT;
import static com.barber.hopak.constrain.DtoConstraintMessage.FIELD_SHOULD_NOT_BE_NULL;
import static com.barber.hopak.constrain.DtoConstraintMessage.STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER;
import static com.barber.hopak.constrain.DtoConstraintMessage.STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BarberDto implements DTO<Barber> {
    private Long id;
    @NotBlank(message = STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE)
    @Length(min = 1, max = 30, message = STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    private String barberName;
    @NotBlank(message = STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE)
//    @InstagramNickName(message = BARBER_INSTAGRAM_SHOULD_BE_CORRECT)
    private String instagram;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @BarberRankName(message = BARBER_RANK_SHOULD_BE_CORRECT)
    private BarberRank barberRank;
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