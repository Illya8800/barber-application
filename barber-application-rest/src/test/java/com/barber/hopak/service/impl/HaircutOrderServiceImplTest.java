package com.barber.hopak.service.impl;

import com.barber.hopak.exception.entity.haircut.order.HaircutOrderNotFoundException;
import com.barber.hopak.model.impl.HaircutOrder;
import com.barber.hopak.repository.HaircutOrderRepository;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.entity.HaircutOrderTestUtils;
import com.barber.hopak.web.domain.impl.HaircutOrderDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.barber.hopak.util.entity.HaircutOrderTestUtils.EXISTING_HAIRCUT_ORDER_ID;
import static com.barber.hopak.util.entity.HaircutOrderTestUtils.UNEXISTING_HAIRCUT_ORDER_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class HaircutOrderServiceImplTest {
    private final HaircutOrderTestUtils haircutOrderTestUtils;
    @Mock
    private HaircutOrderRepository haircutOrderRepository;
    @InjectMocks
    private HaircutOrderServiceImpl haircutOrderService;

    @Autowired
    public HaircutOrderServiceImplTest(HaircutOrderTestUtils haircutOrderTestUtils) {
        this.haircutOrderTestUtils = haircutOrderTestUtils;
    }

    @Test
    void findById_thenReturnHaircutOrder() {
        HaircutOrderDto haircutOrderDto = haircutOrderTestUtils.getLazyHaircutOrderDto();
        when(haircutOrderRepository.findById(EXISTING_HAIRCUT_ORDER_ID))
                .thenReturn(Optional.of(haircutOrderDto.toEntity()));

        haircutOrderService.findById(EXISTING_HAIRCUT_ORDER_ID);

        then(haircutOrderRepository)
                .should(times(1))
                .findById(EXISTING_HAIRCUT_ORDER_ID);
    }
    @Test
    void findById_thenThrowHaircutOrderNotFoundException() {
        when(haircutOrderRepository.findById(UNEXISTING_HAIRCUT_ORDER_ID))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> haircutOrderService.findById(UNEXISTING_HAIRCUT_ORDER_ID))
                .isInstanceOf(HaircutOrderNotFoundException.class)
                .hasMessage(StringUtils3C.join("Haircut order with id ", UNEXISTING_HAIRCUT_ORDER_ID, " not found"));

        then(haircutOrderRepository)
                .should(times(1))
                .findById(UNEXISTING_HAIRCUT_ORDER_ID);
    }
    @Test
    void findAll_thenReturnLazyHaircutOrderList() {
        List<HaircutOrderDto> paymentDtoList = haircutOrderTestUtils.getLazyHaircutOrderDtoList();
        List<HaircutOrder> paymentList = paymentDtoList.stream().map(HaircutOrderDto::toEntity).toList();
        when(haircutOrderRepository.findAll())
                .thenReturn(paymentList);

        List<HaircutOrderDto> all = haircutOrderService.findAll();

        then(haircutOrderRepository)
                .should(times(1))
                .findAll();

        assertThat(all.stream().map(HaircutOrderDto::toEntity).toList()).isEqualTo(paymentDtoList.stream().map(HaircutOrderDto::toEntity).toList());
    }

    @Test
    void findAll_thenReturnEmptyList() {
        when(haircutOrderRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<HaircutOrderDto> all = haircutOrderService.findAll();

        then(haircutOrderRepository)
                .should(times(1))
                .findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void create_thenSuccessfulCreate() {
        HaircutOrderDto paymentDto = haircutOrderTestUtils.getLazyHaircutOrderDto();
        HaircutOrder payment = mock(HaircutOrder.class);

        when(haircutOrderRepository.save(any()))
                .thenReturn(payment);
        when(payment.toDto())
                .thenReturn(paymentDto);

        haircutOrderService.create(paymentDto);

        then(haircutOrderRepository)
                .should(times(1))
                .save(any());
        then(payment)
                .should(times(1))
                .toDto();
    }

    @Test
    void update_thenSuccessfulUpdate() {
        HaircutOrderDto paymentDto = haircutOrderTestUtils.getLazyHaircutOrderDto();
        HaircutOrder payment = mock(HaircutOrder.class);

        when(haircutOrderRepository.save(any()))
                .thenReturn(payment);
        when(payment.toDto())
                .thenReturn(paymentDto);

        haircutOrderService.update(paymentDto);

        then(haircutOrderRepository)
                .should(times(1))
                .save(any());
        then(payment)
                .should(times(1))
                .toDto();
    }

    @Test
    void deleteById() {
        doNothing().when(haircutOrderRepository).deleteById(EXISTING_HAIRCUT_ORDER_ID);

        haircutOrderService.deleteById(EXISTING_HAIRCUT_ORDER_ID);

        then(haircutOrderRepository)
                .should(times(1))
                .deleteById(EXISTING_HAIRCUT_ORDER_ID);
    }
}