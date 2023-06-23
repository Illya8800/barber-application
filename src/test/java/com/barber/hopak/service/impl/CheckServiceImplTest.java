package com.barber.hopak.service.impl;

import com.barber.hopak.exception.entity.check.CheckNotFoundException;
import com.barber.hopak.model.impl.Check;
import com.barber.hopak.repository.CheckRepository;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.entity.CheckTestUtils;
import com.barber.hopak.web.domain.impl.CheckDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.barber.hopak.util.entity.CheckTestUtils.EXISTING_CHECK_ID;
import static com.barber.hopak.util.entity.CheckTestUtils.UNEXISTING_CHECK_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class CheckServiceImplTest {
    private final CheckTestUtils checkTestUtils;
    @Mock
    private CheckRepository checkRepository;
    @InjectMocks
    private CheckServiceImpl checkService;

    @Autowired
    CheckServiceImplTest(CheckTestUtils checkTestUtils) {
        this.checkTestUtils = checkTestUtils;
    }

    @Test
    void findById_thenReturnCheck() {
        CheckDto checkDto = checkTestUtils.getLazyCheckDto();
        when(checkRepository.findById(EXISTING_CHECK_ID))
                .thenReturn(Optional.of(checkDto.toEntity()));

        checkService.findById(EXISTING_CHECK_ID);

        then(checkRepository)
                .should(times(1))
                .findById(EXISTING_CHECK_ID);
    }

    @Test
    void findById_thenThrowCheckNotFoundException() {
        when(checkRepository.findById(UNEXISTING_CHECK_ID))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> checkService.findById(UNEXISTING_CHECK_ID))
                .isInstanceOf(CheckNotFoundException.class)
                .hasMessage(StringUtils3C.join("Check with id ", UNEXISTING_CHECK_ID, " not found"));

        then(checkRepository)
                .should(times(1))
                .findById(UNEXISTING_CHECK_ID);
    }

    @Test
    void findAll_thenReturnChecks() {
        List<CheckDto> checkDtoList = checkTestUtils.getLazyChechDtoList();
        List<Check> checkList = checkDtoList.stream().map(CheckDto::toEntity).toList();
        when(checkRepository.findAll())
                .thenReturn(checkList);

        List<CheckDto> all = checkService.findAll();

        then(checkRepository)
                .should(times(1))
                .findAll();

        assertThat(all.stream().map(CheckDto::toEntity).toList()).isEqualTo(checkDtoList.stream().map(CheckDto::toEntity).toList());
    }

    @Test
    void findAll_thenReturnEmptyList() {
        when(checkRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<CheckDto> all = checkService.findAll();

        then(checkRepository)
                .should(times(1))
                .findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void create_thenSuccessfulCreate() {
        CheckDto checkDto = checkTestUtils.getLazyCheckDto();
        Check checkMock = mock(Check.class);

        when(checkRepository.save(any()))
                .thenReturn(checkMock);
        when(checkMock.toDto())
                .thenReturn(checkDto);

        checkService.create(checkDto);

        then(checkRepository)
                .should(times(1))
                .save(any());
        then(checkMock)
                .should(times(1))
                .toDto();
    }

    @Test
    void update_thenSuccessfulUpdate() {
        CheckDto checkDto = checkTestUtils.getLazyCheckDto();
        Check checkMock = mock(Check.class);

        when(checkRepository.save(any()))
                .thenReturn(checkMock);
        when(checkMock.toDto())
                .thenReturn(checkDto);

        checkService.update(checkDto);

        then(checkRepository)
                .should(times(1))
                .save(any());
        then(checkMock)
                .should(times(1))
                .toDto();
    }

    @Test
    void deleteById() {
        doNothing().when(checkRepository).deleteById(EXISTING_CHECK_ID);

        checkService.deleteById(EXISTING_CHECK_ID);

        then(checkRepository)
                .should(times(1))
                .deleteById(EXISTING_CHECK_ID);
    }
}