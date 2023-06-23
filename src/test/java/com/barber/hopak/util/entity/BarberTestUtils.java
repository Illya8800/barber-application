package com.barber.hopak.util.entity;

import com.barber.hopak.config.InstagramCredentialsConfig;
import com.barber.hopak.model.enumeration.BarberRank;
import com.barber.hopak.web.domain.impl.BarberDto;
import com.barber.hopak.web.domain.impl.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BarberTestUtils {

    private final InstagramCredentialsConfig instagramCredentialsConfig;
    private final ImageTestUtils imageTestUtils;

    public static final Long EXISTING_BARBER_ID = 1L;
    public static final Long UNEXISTING_BARBER_ID = -1L;
    public static final String EXISTING_BARBER_NAME = "Test barber name";

    public BarberDto getBarberDtoWithAvatarId() {
        return BarberDto.builder()
                .id(EXISTING_BARBER_ID)
                .barberName(EXISTING_BARBER_NAME)
                .instagram(instagramCredentialsConfig.getUsername())
                .barberRank(BarberRank.BARBER_SENIOR)
                .avatarId(imageTestUtils.getNoImageId())
                .build();
    }

    public BarberDto getBarberDtoWithAvatar() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        return BarberDto.builder()
                .id(EXISTING_BARBER_ID)
                .barberName(EXISTING_BARBER_NAME)
                .instagram(instagramCredentialsConfig.getUsername())
                .barberRank(BarberRank.BARBER_SENIOR)
                .avatarId(imageDto.getId())
                .avatar(imageDto)
                .build();
    }

    public List<BarberDto> getBarberDtoListWithoutAvatar() {
        BarberDto firstBarber = BarberDto.builder()
                .id(2L)
                .barberName("FirstBarber")
                .instagram("bmw")
                .barberRank(BarberRank.BARBER_SENIOR)
                .avatarId(imageTestUtils.getNoImageId())
                .build();

        BarberDto secondBarber = BarberDto.builder()
                .id(3L)
                .barberName("SecondBarber")
                .instagram("audi")
                .barberRank(BarberRank.TOP_BARBER)
                .avatarId(imageTestUtils.getNoImageId())
                .build();

        BarberDto thirdBarber = BarberDto.builder()
                .id(4L)
                .barberName("ThirdBarber")
                .instagram("opel")
                .barberRank(BarberRank.TOP_BARBER)
                .avatarId(imageTestUtils.getImageDto().getId())
                .build();

        return List.of(firstBarber, secondBarber, thirdBarber);
    }
}
