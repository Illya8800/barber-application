package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.DtoConstraintMessage;
import com.barber.hopak.constrain.RoleEnumName;
import com.barber.hopak.model.impl.Role;
import com.barber.hopak.model.impl.User;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

import static com.barber.hopak.constrain.DtoConstraintMessage.FIELD_SHOULD_NOT_BE_NULL;
import static com.barber.hopak.constrain.DtoConstraintMessage.STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER;
import static com.barber.hopak.constrain.DtoConstraintMessage.STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE;
import static com.barber.hopak.constrain.DtoConstraintMessage.USER_PASSWORD_SHOULD_BE_GREATER_THEN_4_CHARACTER;
import static com.barber.hopak.constrain.DtoConstraintMessage.USER_ROLES_SHOULD_CONSIST_ONLY_PREPARED_ROLES;
import static com.barber.hopak.constrain.DtoConstraintMessage.USER_ROLES_SHOULD_NOT_BE_EMPTY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserDto implements DTO<User> {
    private Long id;
    @NotBlank(message = STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE)
    @Length(min = 1, max = 30, message = STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    private String firstname;
    @NotBlank(message = STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE)
    @Length(min = 1, max = 30, message = DtoConstraintMessage.STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER)
    private String lastname;
    @NotBlank(message = STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE)
    @Length(min = 4, max = 255, message = USER_PASSWORD_SHOULD_BE_GREATER_THEN_4_CHARACTER)
    private String password;
    @NotNull(message = FIELD_SHOULD_NOT_BE_NULL)
    @Size(min = 1, message = USER_ROLES_SHOULD_NOT_BE_EMPTY)
    @RoleEnumName(message = USER_ROLES_SHOULD_CONSIST_ONLY_PREPARED_ROLES)
    private Set<Role> roles;

    @Override
    public User toEntity() {
        return new User(
                this.id,
                this.firstname,
                this.lastname,
                this.password,
                this.roles
        );
    }
}