package com.barber.hopak.dto;

import com.barber.hopak.constrain.RoleEnumName;
import com.barber.hopak.model.Role;
import com.barber.hopak.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.USER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.USER_PASSWORD_SHOULD_BE_GREATER_THEN_4_CHARACTER;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.USER_ROLES_SHOULD_CONSIST_ONLY_PREPARED_ROLES;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.USER_ROLES_SHOULD_NOT_BE_EMPTY;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.USER_SECOND_NAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30;

@Builder
public class UserDto {
    private Long id;
    @NotBlank(message = FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE)
    @Length(min = 1, max = 30, message = USER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30)
    private String firstname;
    @NotBlank(message = FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE)
    @Length(min = 1, max = 30, message = USER_SECOND_NAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30)
    private String lastname;
    @NotBlank(message = FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE)
    @Length(min = 4, max = 255, message = USER_PASSWORD_SHOULD_BE_GREATER_THEN_4_CHARACTER)
    private String password;
    @Size(min = 1, message = USER_ROLES_SHOULD_NOT_BE_EMPTY)
    @RoleEnumName(message = USER_ROLES_SHOULD_CONSIST_ONLY_PREPARED_ROLES)
    private Set<Role> roles;

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
