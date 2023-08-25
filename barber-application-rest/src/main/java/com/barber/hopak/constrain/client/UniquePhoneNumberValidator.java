package com.barber.hopak.constrain.client;

import com.barber.hopak.exception.entity.client.ClientNotFoundException;
import com.barber.hopak.service.ClientService;
import com.barber.hopak.web.domain.impl.ClientDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, ClientDto> {
    private final ClientService<ClientDto, Long> clientService;
    @Override
    public boolean isValid(ClientDto value, ConstraintValidatorContext context) {
        try {
            ClientDto haircutDtoByDb = clientService.findByPhoneNumber(value.getPhoneNumber());
            return haircutDtoByDb.getId().equals(value.getId());
        } catch (ClientNotFoundException e) {
            return true;
        }
    }
}
