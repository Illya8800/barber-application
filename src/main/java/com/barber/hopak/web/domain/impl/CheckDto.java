package com.barber.hopak.web.domain.impl;

import com.barber.hopak.model.impl.*;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.Valid;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class CheckDto implements DTO<Check> {
    private Long id;
    private LocalDateTime dateTime;
    @Valid
    private Payment payment;
    @Valid
    private Haircut haircut;
    @Valid
    private Barber barber;
    @Valid
    private Client client;

    @Override
    public Check toEntity() {
        return new Check(
                this.id,
                this.dateTime,
                this.payment,
                this.haircut,
                this.barber,
                this.client
        );
    }
}
