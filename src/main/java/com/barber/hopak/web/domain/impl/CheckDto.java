package com.barber.hopak.web.domain.impl;

import com.barber.hopak.model.impl.Barber;
import com.barber.hopak.model.impl.Check;
import com.barber.hopak.model.impl.Client;
import com.barber.hopak.model.impl.Haircut;
import com.barber.hopak.model.impl.Payment;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
