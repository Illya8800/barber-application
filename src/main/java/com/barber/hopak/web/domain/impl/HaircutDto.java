package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.haircut.UniqueHaircutName;
import com.barber.hopak.model.impl.Haircut;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static com.barber.hopak.constrain.DtoConstraintMessage.FIELD_SHOULD_NOT_BE_NULL;
import static com.barber.hopak.constrain.DtoConstraintMessage.HAIRCUT_DURATION_SHOULD_BE_LESS_THEN_1440;
import static com.barber.hopak.constrain.DtoConstraintMessage.INTEGER_MAX_VALUE_CONSTRAIN_TEXT;
import static com.barber.hopak.constrain.DtoConstraintMessage.MIN_VALUE_SHOULD_BE_MORE_THEN_0;
import static com.barber.hopak.constrain.DtoConstraintMessage.STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER;
import static com.barber.hopak.constrain.DtoConstraintMessage.STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@UniqueHaircutName
public class HaircutDto implements DTO<Haircut> {
    private Long id;
    @NotBlank(message = STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE)
    @Length(min = 1, max = 30, message = STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    private String name;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Max(value = Integer.MAX_VALUE, message = INTEGER_MAX_VALUE_CONSTRAIN_TEXT)
    @Min(value = 1, message = MIN_VALUE_SHOULD_BE_MORE_THEN_0)
    private Integer price;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Max(value = 1440, message = HAIRCUT_DURATION_SHOULD_BE_LESS_THEN_1440)
    @Min(value = 1, message = MIN_VALUE_SHOULD_BE_MORE_THEN_0)
    private Integer duration;
    private Long avatarId;
    private ImageDto avatar;


    @Override
    public Haircut toEntity() {
        return new Haircut(
                this.id,
                this.name,
                this.price,
                this.duration,
                this.avatarId,
                this.avatar == null ? null : this.avatar.toEntity()
        );
    }
}

