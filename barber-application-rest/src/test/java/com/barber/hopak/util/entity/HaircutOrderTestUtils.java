package com.barber.hopak.util.entity;

import com.barber.hopak.web.domain.impl.HaircutOrderDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.barber.hopak.util.entity.BarberTestUtils.EXISTING_BARBER_ID;
import static com.barber.hopak.util.entity.ClientTestUtils.EXISTING_CLIENT_ID;
import static com.barber.hopak.util.entity.HaircutTestUtils.EXISTING_HAIRCUT_ID;

@Component
public class HaircutOrderTestUtils {
public static final Long EXISTING_HAIRCUT_ORDER_ID = 1L;
public static final Long UNEXISTING_HAIRCUT_ORDER_ID = -1L;

    public HaircutOrderDto getLazyHaircutOrderDto() {
        return HaircutOrderDto.builder()
                .id(EXISTING_HAIRCUT_ORDER_ID)
                .description("Any text")
                .dateTime(LocalDateTime.now())
                .clientId(EXISTING_CLIENT_ID)
                .haircutId(EXISTING_HAIRCUT_ID)
                .barberId(EXISTING_BARBER_ID)
                .build();
    }

    public List<HaircutOrderDto> getLazyHaircutOrderDtoList() {
        HaircutOrderDto firstHaircutOrderDto = HaircutOrderDto.builder()
                .id(EXISTING_HAIRCUT_ORDER_ID + 1)
                .description("Any text")
                .dateTime(LocalDateTime.now())
                .clientId(EXISTING_CLIENT_ID + 1)
                .haircutId(EXISTING_HAIRCUT_ID + 1)
                .barberId(EXISTING_BARBER_ID + 1)
                .build();

        HaircutOrderDto secondHaircutOrderDto = HaircutOrderDto.builder()
                .id(EXISTING_HAIRCUT_ORDER_ID + 2)
                .description("Any text")
                .dateTime(LocalDateTime.now())
                .clientId(EXISTING_CLIENT_ID + 2)
                .haircutId(EXISTING_HAIRCUT_ID + 2)
                .barberId(EXISTING_BARBER_ID + 2)
                .build();

        HaircutOrderDto thirdHaircutOrderDto = HaircutOrderDto.builder()
                .id(EXISTING_HAIRCUT_ORDER_ID + 3)
                .description("Any text")
                .dateTime(LocalDateTime.now())
                .clientId(EXISTING_CLIENT_ID + 3)
                .haircutId(EXISTING_HAIRCUT_ID + 3)
                .barberId(EXISTING_BARBER_ID + 3)
                .build();

        return List.of(firstHaircutOrderDto, secondHaircutOrderDto, thirdHaircutOrderDto);
    }
}
