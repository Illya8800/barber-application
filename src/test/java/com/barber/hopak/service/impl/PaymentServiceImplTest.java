package com.barber.hopak.service.impl;

import com.barber.hopak.exception.entity.payment.PaymentNotFoundException;
import com.barber.hopak.model.impl.Payment;
import com.barber.hopak.repository.PaymentRepository;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.entity.PaymentTestUtils;
import com.barber.hopak.web.domain.impl.PaymentDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.barber.hopak.util.entity.PaymentTestUtils.PAYMENT_ID;
import static com.barber.hopak.util.entity.PaymentTestUtils.UNEXISTING_PAYMENT_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentServiceImplTest {
    private final PaymentTestUtils paymentTestUtils;
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Autowired
    PaymentServiceImplTest(PaymentTestUtils paymentTestUtils) {
        this.paymentTestUtils = paymentTestUtils;
    }

    @Test
    void findById_thenReturnPayment() {
        PaymentDto paymentDto = paymentTestUtils.getPaymentDto();
        when(paymentRepository.findById(PAYMENT_ID))
                .thenReturn(Optional.of(paymentDto.toEntity()));

        paymentService.findById(PAYMENT_ID);

        then(paymentRepository)
                .should(times(1))
                .findById(PAYMENT_ID);
    }


    @Test
    void findById_thenThrowPaymentNotFoundException() {
        when(paymentRepository.findById(UNEXISTING_PAYMENT_ID))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> paymentService.findById(UNEXISTING_PAYMENT_ID))
                .isInstanceOf(PaymentNotFoundException.class)
                .hasMessage(StringUtils3C.join("Payment with id ", UNEXISTING_PAYMENT_ID, " not found"));

        then(paymentRepository)
                .should(times(1))
                .findById(UNEXISTING_PAYMENT_ID);
    }

    @Test
    void findAll_thenReturnPayments() {
        List<PaymentDto> paymentDtoList = paymentTestUtils.getPaymentDtoList();
        List<Payment> paymentList = paymentDtoList.stream().map(PaymentDto::toEntity).toList();
        when(paymentRepository.findAll())
                .thenReturn(paymentList);

        List<PaymentDto> all = paymentService.findAll();

        then(paymentRepository)
                .should(times(1))
                .findAll();

        assertThat(all).isEqualTo(paymentDtoList);
    }

    @Test
    void findAll_thenReturnEmptyList() {
        when(paymentRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<PaymentDto> all = paymentService.findAll();

        then(paymentRepository)
                .should(times(1))
                .findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void create_thenSuccessfulCreate() {
        PaymentDto paymentDto = paymentTestUtils.getPaymentDto();
        Payment payment = mock(Payment.class);

        when(paymentRepository.save(any()))
                .thenReturn(payment);
        when(payment.toDto())
                .thenReturn(paymentDto);

        paymentService.create(paymentDto);

        then(paymentRepository)
                .should(times(1))
                .save(any());
        then(payment)
                .should(times(1))
                .toDto();
    }

    @Test
    void update_thenSuccessfulUpdate() {
        PaymentDto paymentDto = paymentTestUtils.getPaymentDto();
        Payment payment = mock(Payment.class);

        when(paymentRepository.save(any()))
                .thenReturn(payment);
        when(payment.toDto())
                .thenReturn(paymentDto);

        paymentService.update(paymentDto);

        then(paymentRepository)
                .should(times(1))
                .save(any());
        then(payment)
                .should(times(1))
                .toDto();
    }

    @Test
    void deleteById() {
        doNothing().when(paymentRepository).deleteById(PAYMENT_ID);

        paymentService.deleteById(PAYMENT_ID);

        then(paymentRepository)
                .should(times(1))
                .deleteById(PAYMENT_ID);
    }
}