package com.barber.hopak.service.impl;

import com.barber.hopak.buffer.impl.BufferServiceImpl;
import com.barber.hopak.exception.entity.barber.BarberNotFoundException;
import com.barber.hopak.model.impl.Barber;
import com.barber.hopak.model.impl.Image;
import com.barber.hopak.repository.BarberRepository;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.entity.BarberTestUtils;
import com.barber.hopak.util.entity.ImageTestUtils;
import com.barber.hopak.web.domain.impl.BarberDto;
import com.barber.hopak.web.domain.impl.ImageDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.barber.hopak.util.entity.BarberTestUtils.EXISTING_BARBER_ID;
import static com.barber.hopak.util.entity.BarberTestUtils.UNEXISTING_BARBER_ID;
import static com.barber.hopak.util.entity.ImageTestUtils.EXISTING_IMAGE_DTO_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class BarberServiceImplTest {
    private final ImageTestUtils imageTestUtils;
    private final BarberTestUtils barberTestUtils;

    @Mock
    private BufferServiceImpl bufferService;
    @Mock
    private BarberRepository barberRepository;
    @Mock
    private ImageService<ImageDto, Long> imageService;
    @InjectMocks
    private BarberServiceImpl barberService;

    @Autowired
    BarberServiceImplTest(ImageTestUtils imageTestUtils, BarberTestUtils barberTestUtils) {
        this.imageTestUtils = imageTestUtils;
        this.barberTestUtils = barberTestUtils;
    }

    @Test
    void findById_thenReturnBarberWithAvatar() {
        BarberDto barberDto = barberTestUtils.getBarberDtoWithAvatar();
        barberDto.setAvatar(null);
        Barber barberMock = mock(Barber.class);
        ImageDto imageDto = imageTestUtils.getImageDto();


        when(barberRepository.findById(EXISTING_BARBER_ID))
                .thenReturn(Optional.of(barberMock));

        when(barberMock.getAvatarId()).thenReturn(EXISTING_IMAGE_DTO_ID);
        when(imageService.findById(imageDto.getId()))
                .thenReturn(imageDto);
        when(barberMock.toDto())
                .thenReturn(barberDto);


        BarberDto testedBarber = barberService.findById(EXISTING_BARBER_ID);


        then(barberRepository)
                .should(times(1))
                .findById(EXISTING_BARBER_ID);
        then(barberMock)
                .should(times(1))
                .toDto();
        then(imageService)
                .should(times(1))
                .findById(imageDto.getId());

        barberDto.setAvatar(imageDto);
        assertThat(testedBarber).isEqualTo(barberDto);
    }

    @Test
    void findById_thenThrowBarberNotFoundException() {
        Barber barberMock = mock(Barber.class);


        when(barberRepository.findById(UNEXISTING_BARBER_ID))
                .thenReturn(Optional.empty());


        assertThatThrownBy(() -> barberService.findById(UNEXISTING_BARBER_ID))
                .isInstanceOf(BarberNotFoundException.class)
                .hasMessage(StringUtils3C.join("Barber with id ", UNEXISTING_BARBER_ID, " not found"));


        then(barberRepository)
                .should(times(1))
                .findById(UNEXISTING_BARBER_ID);
        then(barberMock)
                .should(never())
                .toDto();
        then(imageService)
                .should(never())
                .findById(any());
    }

    @Test
    void findAll_thenReturnBarbers() {
        List<Barber> barberList = barberTestUtils.getBarberDtoListWithoutAvatar().stream().map(BarberDto::toEntity).toList();
        when(barberRepository.findAll())
                .thenReturn(barberList);

        List<BarberDto> all = barberService.findAll();

        then(barberRepository)
                .should(times(1))
                .findAll();

        assertThat(all.stream().map(BarberDto::toEntity).toList()).isEqualTo(barberList);
    }

    @Test
    void findAll_thenReturnEmptyList() {
        when(barberRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<BarberDto> all = barberService.findAll();

        then(barberRepository)
                .should(times(1))
                .findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void create_thenSuccessfulCreate() {
        BarberDto barberDto = barberTestUtils.getBarberDtoWithAvatar();
        Image imageMock = mock(Image.class);
        ImageDto imageDtoMock = mock(ImageDto.class);
        Barber createdBarberMock = mock(Barber.class);
        BarberDto createdBarberDtoMock = mock(BarberDto.class);

        when(barberRepository.save(any()))
                .thenReturn(createdBarberMock);

        when(createdBarberMock.getAvatar())
                .thenReturn(imageMock);

        when(imageMock.getId())
                .thenReturn(barberDto.getAvatar().getId());

        when(createdBarberMock.toDto())
                .thenReturn(createdBarberDtoMock);

        when(createdBarberDtoMock.getAvatar()).thenReturn(imageDtoMock);
        doNothing().when(bufferService).save(imageDtoMock);

        barberService.create(barberDto);

        then(barberRepository)
                .should(times(1))
                .save(any());
        then(bufferService)
                .should(times(1))
                .save(createdBarberDtoMock.getAvatar());
    }

    @Test
    void update_thenSuccessfulUpdate() {
        BarberDto barberDto = barberTestUtils.getBarberDtoWithAvatar();
        Barber barberMock = mock(Barber.class);
        BarberDto updatedBarberDtoMock = mock(BarberDto.class);

        when(barberRepository.save(any()))
                .thenReturn(barberMock);


        when(barberMock.toDto())
                .thenReturn(updatedBarberDtoMock);

        doNothing().when(bufferService).save(any());

        barberService.update(barberDto);

        then(barberRepository)
                .should(times(1))
                .save(any());
        then(bufferService)
                .should(times(1))
                .save(any());
    }

    @Test
    void deleteById_thenSuccessfulDelete() {
        BarberDto barberDto = barberTestUtils.getBarberDtoWithAvatar();
        doNothing().when(barberRepository).deleteById(barberDto.getId());
        doNothing().when(imageService).deleteById(barberDto.getAvatarId());

        barberService.delete(barberDto);

        then(barberRepository)
                .should(times(1))
                .deleteById(barberDto.getId());
        then(imageService)
                .should(times(1))
                .deleteById(barberDto.getAvatarId());
    }
}