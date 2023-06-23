package com.barber.hopak.util.entity;

import com.barber.hopak.web.domain.impl.HaircutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HaircutTestUtils {

    private final ImageTestUtils imageTestUtils;
    public static final Long EXISTING_HAIRCUT_ID = 1L;
    public static final Long UNEXISTING_HAIRCUT_ID = 1L;
    public static final String HAIRCUT_NAME = "Test haircut name";
    public static final String UNEXISTING_HAIRCUT_NAME = "Test haircut name";

    public HaircutDto getHaircutDto() {
        return HaircutDto.builder()
                .id(EXISTING_HAIRCUT_ID)
                .name(HAIRCUT_NAME)
                .price(150)
                .duration(90)
                .avatarId(imageTestUtils.getNoImageId())
                .build();
    }

    public List<HaircutDto> getHaircutDtoList() {
        HaircutDto first = HaircutDto.builder()
                .id(2L)
                .name(HAIRCUT_NAME)
                .price(150)
                .duration(90)
                .avatarId(imageTestUtils.getNoImageId())
                .build();

        HaircutDto second = HaircutDto.builder()
                .id(3L)
                .name(HAIRCUT_NAME)
                .price(150)
                .duration(90)
                .avatarId(imageTestUtils.getNoImageId())
                .build();

        HaircutDto third = HaircutDto.builder()
                .id(4L)
                .name(HAIRCUT_NAME)
                .price(150)
                .duration(90)
                .avatarId(imageTestUtils.getNoImageId())
                .build();

        return List.of(first, second, third);
    }
}
