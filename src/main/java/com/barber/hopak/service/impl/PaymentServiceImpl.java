package com.barber.hopak.service.impl;

import com.barber.hopak.exception.BarberNotFoundException;
import com.barber.hopak.exception.payment.PaymentNotFoundException;
import com.barber.hopak.model.impl.Payment;
import com.barber.hopak.repository.PaymentRepository;
import com.barber.hopak.service.PaymentService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.web.domain.impl.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService<PaymentDto, Long> {
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional(readOnly = true)
    public PaymentDto findById(Long id) {
        log.info("Finding an barber with id = {} in DB", id);
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(StringUtils3C.join("Payment with id ", id, " not found"))).toDto();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDto> findAll() {
        log.info("Finding all payments in DB");
        return paymentRepository.findAll().stream()
                .map(Payment::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto create(PaymentDto paymentDto) {
        log.info("Inserting new payment with name = {} in DB ", paymentDto.getId());
        return paymentRepository.save(paymentDto.toEntity()).toDto();
    }

    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        log.info("Updating payment with name = {} in DB ", paymentDto.getId());
        return paymentRepository.save(paymentDto.toEntity()).toDto();
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting a payment with id = {} from DB", id);
        paymentRepository.deleteById(id);
    }
}