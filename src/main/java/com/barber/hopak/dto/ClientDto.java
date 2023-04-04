package com.barber.hopak.dto;

import com.barber.hopak.constrain.UniquePhoneNumber;
import com.barber.hopak.model.Check;
import com.barber.hopak.model.Client;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
public class ClientDto {
    private Long id;
    private List<Check> checks;
    @Length(min = 1, max = 30, message = "dw")
    @UniquePhoneNumber
    private String firstname;
    @Length(min = 1, max = 30, message = "dw")
    private String lastname;
    @Length(min = 19, max = 19, message = "dwdwd")
    private String phoneNumber;

    public Client toEntity() {
        return new Client(
                this.id,
                this.checks,
                this.firstname,
                this.lastname,
                this.phoneNumber
        );
    }
}
