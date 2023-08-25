package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.DtoConstraintMessage;
import com.barber.hopak.constrain.client.PhoneNumber;
import com.barber.hopak.constrain.client.UniquePhoneNumber;
import com.barber.hopak.model.impl.Client;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

import static com.barber.hopak.constrain.DtoConstraintMessage.CLIENT_PHONE_NUMBER_SHOULD_BE_MATCH_WITH_REGEX;
import static com.barber.hopak.constrain.DtoConstraintMessage.STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER;
import static com.barber.hopak.constrain.DtoConstraintMessage.STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@UniquePhoneNumber
public class ClientDto implements DTO<Client> {
    private Long id;
    @NotBlank(message = STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE)
    @Length(min = 1, max = 30, message = STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    private String firstname;
    @NotBlank(message = STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE)
    @Length(min = 1, max = 30, message = DtoConstraintMessage.STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    private String lastname;
    @NotBlank(message = STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE)
    @PhoneNumber(message = CLIENT_PHONE_NUMBER_SHOULD_BE_MATCH_WITH_REGEX)
    private String phoneNumber;
    private List<CheckDto> checks;

    @Override
    public Client toEntity() {
        return new Client(
                this.id,
                this.firstname,
                this.lastname,
                this.phoneNumber,
                this.checks == null ? null : this.checks.stream().map(CheckDto::toEntity).toList()
        );
    }
}
