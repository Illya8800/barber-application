package com.barber.hopak.dto;

import com.barber.hopak.model.Barber;
import com.barber.hopak.model.Check;
import com.barber.hopak.model.Client;
import com.barber.hopak.model.Haircut;
import com.barber.hopak.model.Payment;
import jakarta.validation.Valid;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class CheckDto {
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
