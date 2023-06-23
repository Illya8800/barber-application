package com.barber.hopak.util.entity;

import com.barber.hopak.web.domain.impl.CheckDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.barber.hopak.util.entity.BarberTestUtils.EXISTING_BARBER_ID;
import static com.barber.hopak.util.entity.ClientTestUtils.EXISTING_CLIENT_ID;
import static com.barber.hopak.util.entity.HaircutTestUtils.EXISTING_HAIRCUT_ID;
import static com.barber.hopak.util.entity.PaymentTestUtils.EXISTING_PAYMENT_ID;

@Component
public class CheckTestUtils {

    public static final Long EXISTING_CHECK_ID = 1L;
    public static final Long UNEXISTING_CHECK_ID = -1L;

    public CheckDto getLazyCheckDto() {
        return CheckDto.builder()
                .id(EXISTING_CHECK_ID)
                .dateTime(LocalDateTime.now().plusDays(1))
                .paymentId(EXISTING_PAYMENT_ID)
                .haircutId(EXISTING_HAIRCUT_ID)
                .barberId(EXISTING_BARBER_ID)
                .clientId(EXISTING_CLIENT_ID)
                .build();
    }

    public List<CheckDto> getTwoLazyCheckDtoAsList() {
        CheckDto first = CheckDto.builder()
                .id(EXISTING_CHECK_ID + 1)
                .dateTime(LocalDateTime.now().plusDays(2))
                .paymentId(EXISTING_PAYMENT_ID + 1)
                .haircutId(EXISTING_HAIRCUT_ID + 1)
                .barberId(EXISTING_BARBER_ID + 1)
                .clientId(EXISTING_CLIENT_ID + 1)
                .build();
        CheckDto second = CheckDto.builder()
                .id(EXISTING_CHECK_ID + 2)
                .dateTime(LocalDateTime.now().plusDays(3))
                .paymentId(EXISTING_PAYMENT_ID + 2)
                .haircutId(EXISTING_HAIRCUT_ID + 2)
                .barberId(EXISTING_BARBER_ID + 2)
                .clientId(EXISTING_CLIENT_ID + 2)
                .build();
        return List.of(first, second);
    }

    public List<CheckDto> getLazyChechDtoList() {
        CheckDto first = CheckDto.builder()
                .id(EXISTING_CHECK_ID + 2)
                .dateTime(LocalDateTime.now().plusDays(2))
                .paymentId(EXISTING_PAYMENT_ID + 2)
                .haircutId(EXISTING_HAIRCUT_ID + 2)
                .barberId(EXISTING_BARBER_ID + 2)
                .clientId(EXISTING_CLIENT_ID + 2)
                .build();
        CheckDto second = CheckDto.builder()
                .id(EXISTING_CHECK_ID + 3)
                .dateTime(LocalDateTime.now().plusDays(3))
                .paymentId(EXISTING_PAYMENT_ID + 3)
                .haircutId(EXISTING_HAIRCUT_ID + 3)
                .barberId(EXISTING_BARBER_ID + 3)
                .clientId(EXISTING_CLIENT_ID + 3)
                .build();
        CheckDto third = CheckDto.builder()
                .id(EXISTING_CHECK_ID + 4)
                .dateTime(LocalDateTime.now().plusDays(4))
                .paymentId(EXISTING_PAYMENT_ID + 4)
                .haircutId(EXISTING_HAIRCUT_ID + 4)
                .barberId(EXISTING_BARBER_ID + 4)
                .clientId(EXISTING_CLIENT_ID + 4)
                .build();
        return List.of(first, second, third);
    }
}
