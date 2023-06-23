package com.barber.hopak.util.entity;

import com.barber.hopak.web.domain.impl.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientTestUtils {
    private final CheckTestUtils checkTestUtils;
    public static final Long EXISTING_CLIENT_ID = 1L;
    public static final Long UNEXISTING_CLIENT_ID = -1L;
    public static final String EXISTING_PHONE_NUMBER = "+(38)095-000-51-58";
    public static final String UNEXISTING_PHONE_NUMBER = "+(38)000-000-00-00";

    public ClientDto getClientDtoWithLazyCheck() {

        return ClientDto.builder()
                .id(EXISTING_CLIENT_ID)
                .firstname("Test barber name")
                .lastname("anyName")
                .phoneNumber(EXISTING_PHONE_NUMBER)
                .checks(List.of(checkTestUtils.getLazyCheckDto()))
                .build();
    }

    public List<ClientDto> getClientDtoListWithLazyChecks(){
        ClientDto firstClientWithTwoChecks = ClientDto.builder()
                .id(2L)
                .firstname("First barber name")
                .lastname("anyName")
                .phoneNumber("+(38)095-000-51-51")
                .checks(checkTestUtils.getTwoLazyCheckDtoAsList())
                .build();
        ClientDto secondClient = ClientDto.builder()
                .id(3L)
                .firstname("Second barber name")
                .lastname("anyName")
                .phoneNumber("+(38)095-000-51-52")
                .checks(checkTestUtils.getTwoLazyCheckDtoAsList())
                .build();

        ClientDto thirdClient = ClientDto.builder()
                .id(4L)
                .firstname("Third barber name")
                .lastname("anyName")
                .phoneNumber("+(38)095-000-51-53")
                .checks(checkTestUtils.getTwoLazyCheckDtoAsList())
                .build();
        return List.of(firstClientWithTwoChecks, secondClient, thirdClient);
    }
}
