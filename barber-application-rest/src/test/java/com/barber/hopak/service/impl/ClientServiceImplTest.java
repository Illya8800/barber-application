package com.barber.hopak.service.impl;

import com.barber.hopak.exception.entity.client.ClientNotFoundException;
import com.barber.hopak.model.impl.Client;
import com.barber.hopak.repository.ClientRepository;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.entity.CheckTestUtils;
import com.barber.hopak.util.entity.ClientTestUtils;
import com.barber.hopak.web.domain.impl.ClientDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.barber.hopak.util.entity.ClientTestUtils.EXISTING_CLIENT_ID;
import static com.barber.hopak.util.entity.ClientTestUtils.EXISTING_PHONE_NUMBER;
import static com.barber.hopak.util.entity.ClientTestUtils.UNEXISTING_CLIENT_ID;
import static com.barber.hopak.util.entity.ClientTestUtils.UNEXISTING_PHONE_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceImplTest {
    private final ClientTestUtils clientTestUtils;
    private final CheckTestUtils checkTestUtils;
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientServiceImpl clientService;

    @Autowired
    ClientServiceImplTest(ClientTestUtils clientTestUtils, CheckTestUtils checkTestUtils) {
        this.clientTestUtils = clientTestUtils;
        this.checkTestUtils = checkTestUtils;
    }

    @Test
    void findById_thenReturnClient() {
        ClientDto paymentDto = clientTestUtils.getClientDtoWithLazyCheck();
        when(clientRepository.findById(EXISTING_CLIENT_ID))
                .thenReturn(Optional.of(paymentDto.toEntity()));

        clientService.findById(EXISTING_CLIENT_ID);

        then(clientRepository)
                .should(times(1))
                .findById(EXISTING_CLIENT_ID);
    }

    @Test
    void findById_thenThrowClientNotFoundException() {
        when(clientRepository.findById(UNEXISTING_CLIENT_ID))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> clientService.findById(UNEXISTING_CLIENT_ID))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage(StringUtils3C.join("Client with id ", UNEXISTING_CLIENT_ID, " not found"));

        then(clientRepository)
                .should(times(1))
                .findById(UNEXISTING_CLIENT_ID);
    }

    @Test
    void findByPhoneNumber_thenReturnClient() {
        ClientDto paymentDto = clientTestUtils.getClientDtoWithLazyCheck();
        when(clientRepository.findByPhoneNumber(EXISTING_PHONE_NUMBER))
                .thenReturn(Optional.of(paymentDto.toEntity()));

        clientService.findByPhoneNumber(EXISTING_PHONE_NUMBER);

        then(clientRepository)
                .should(times(1))
                .findByPhoneNumber(EXISTING_PHONE_NUMBER);
    }

    @Test
    void findByPhoneNumber_thenThrowClientNotFoundException() {
        when(clientRepository.findByPhoneNumber(UNEXISTING_PHONE_NUMBER))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> clientService.findByPhoneNumber(UNEXISTING_PHONE_NUMBER))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage(StringUtils3C.join("Client with phone number ", UNEXISTING_PHONE_NUMBER, " not found"));

        then(clientRepository)
                .should(times(1))
                .findByPhoneNumber(UNEXISTING_PHONE_NUMBER);
    }

    @Test
    void findAll_thenReturnClients() {
        List<ClientDto> clientDtoList = clientTestUtils.getClientDtoListWithLazyChecks();
        List<Client> clientList = List.copyOf(clientDtoList).stream().map(ClientDto::toEntity).toList();

        when(clientRepository.findAll())
                .thenReturn(clientList);

        List<ClientDto> all = clientService.findAll();

        then(clientRepository)
                .should(times(1))
                .findAll();

        assertThat(all.stream().map(ClientDto::toEntity).toList()).isEqualTo(clientDtoList.stream().map(ClientDto::toEntity).toList());
    }

    @Test
    void findAll_thenReturnEmptyList() {
        when(clientRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<ClientDto> all = clientService.findAll();

        then(clientRepository)
                .should(times(1))
                .findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void create_thenSuccessfulCreate() {
        ClientDto clientDto = clientTestUtils.getClientDtoWithLazyCheck();
        Client clientMock = mock(Client.class);

        when(clientRepository.save(any()))
                .thenReturn(clientMock);
        when(clientMock.toDto())
                .thenReturn(clientDto);

        clientService.create(clientDto);

        then(clientRepository)
                .should(times(1))
                .save(any());
        then(clientMock)
                .should(times(1))
                .toDto();
    }

    @Test
    void update_thenSuccessfulUpdate() {
        ClientDto clientDto = clientTestUtils.getClientDtoWithLazyCheck();
        Client clientMock = mock(Client.class);

        when(clientRepository.save(any()))
                .thenReturn(clientMock);
        when(clientMock.toDto())
                .thenReturn(clientDto);

        clientService.update(clientDto);

        then(clientRepository)
                .should(times(1))
                .save(any());
        then(clientMock)
                .should(times(1))
                .toDto();
    }

    @Test
    void deleteById_thenSuccessfulDelete() {
        doNothing().when(clientRepository).deleteById(EXISTING_CLIENT_ID);

        clientService.deleteById(EXISTING_CLIENT_ID);

        then(clientRepository)
                .should(times(1))
                .deleteById(EXISTING_CLIENT_ID);
    }

    @Test
    void isUnique_thenUniqueByNotExistInDateBase() {
        ClientDto clientDto = clientTestUtils.getClientDtoWithLazyCheck();
        when(clientRepository.findByPhoneNumber(clientDto.getPhoneNumber())).thenReturn(Optional.of(clientDto.toEntity()));

        boolean unique = clientService.isUnique(clientDto.getId(), clientDto.getPhoneNumber());

        then(clientRepository)
                .should(times(1))
                .findByPhoneNumber(clientDto.getPhoneNumber());

        Assertions.assertThat(unique).isTrue();

    }

    @Test
    void isUnique_thenUniqueByEqualsId() {
        ClientDto clientDto = clientTestUtils.getClientDtoWithLazyCheck();
        when(clientRepository.findByPhoneNumber(clientDto.getPhoneNumber())).thenReturn(Optional.of(clientDto.toEntity()));

        boolean unique = clientService.isUnique(clientDto.getId(), clientDto.getPhoneNumber());

        then(clientRepository)
                .should(times(1))
                .findByPhoneNumber(clientDto.getPhoneNumber());

        Assertions.assertThat(unique).isTrue();
    }

    @Test
    void isUnique_thenNotUniqueById() {
        ClientDto clientDto = clientTestUtils.getClientDtoWithLazyCheck();
        when(clientRepository.findByPhoneNumber(clientDto.getPhoneNumber())).thenReturn(Optional.of(ClientDto.builder()
                .id(5L)
                .firstname("Any name")
                .lastname("anyName")
                .phoneNumber(EXISTING_PHONE_NUMBER)
                .checks(List.of(checkTestUtils.getLazyCheckDto()))
                .build().toEntity()));

        boolean unique = clientService.isUnique(clientDto.getId(), clientDto.getPhoneNumber());

        then(clientRepository)
                .should(times(1))
                .findByPhoneNumber(clientDto.getPhoneNumber());

        Assertions.assertThat(unique).isFalse();
    }
}