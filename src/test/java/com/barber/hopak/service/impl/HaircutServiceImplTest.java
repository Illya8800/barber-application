package com.barber.hopak.service.impl;

import com.barber.hopak.exception.entity.haircut.HaircutNotFoundException;
import com.barber.hopak.model.impl.Haircut;
import com.barber.hopak.repository.HaircutRepository;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.entity.HaircutTestUtils;
import com.barber.hopak.util.entity.ImageTestUtils;
import com.barber.hopak.web.domain.impl.HaircutDto;
import com.barber.hopak.web.domain.impl.ImageDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.barber.hopak.util.entity.HaircutTestUtils.EXISTING_HAIRCUT_ID;
import static com.barber.hopak.util.entity.HaircutTestUtils.HAIRCUT_NAME;
import static com.barber.hopak.util.entity.HaircutTestUtils.UNEXISTING_HAIRCUT_ID;
import static com.barber.hopak.util.entity.HaircutTestUtils.UNEXISTING_HAIRCUT_NAME;
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
class HaircutServiceImplTest {
    private final ImageTestUtils imageTestUtils;
    private final HaircutTestUtils haircutTestUtils;
    @Mock
    private HaircutRepository haircutRepository;
    @Mock
    private ImageService<ImageDto, Long> imageService;
    @InjectMocks
    HaircutServiceImpl haircutService;

    @Autowired
    HaircutServiceImplTest(ImageTestUtils imageTestUtils, HaircutTestUtils haircutTestUtils) {
        this.imageTestUtils = imageTestUtils;
        this.haircutTestUtils = haircutTestUtils;
    }

    @Test
    void findById_thenReturnHaircutWithAvatar() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        HaircutDto haircutDto = haircutTestUtils.getHaircutDto();
        haircutDto.setAvatarId(imageDto.getId());
        Haircut haircutMock = mock(Haircut.class);

        when(haircutRepository.findById(EXISTING_HAIRCUT_ID))
                .thenReturn(Optional.of(haircutMock));
        when(haircutMock.toDto())
                .thenReturn(haircutDto);
        when(imageService.findById(imageDto.getId()))
                .thenReturn(imageDto);

        HaircutDto testedHaircut = haircutService.findById(EXISTING_HAIRCUT_ID);

        then(haircutRepository)
                .should(times(1))
                .findById(EXISTING_HAIRCUT_ID);
        then(haircutMock)
                .should(times(1))
                .toDto();
        then(imageService)
                .should(times(1))
                .findById(imageDto.getId());

        testedHaircut.setAvatar(imageDto);
        assertThat(testedHaircut).isEqualTo(haircutDto);
    }

    @Test
    void findById_thenThrowHaircutNotFoundException() {
        Haircut haircutMock = mock(Haircut.class);

        when(haircutRepository.findById(UNEXISTING_HAIRCUT_ID))
                .thenReturn(Optional.empty());


        assertThatThrownBy(() -> haircutService.findById(UNEXISTING_HAIRCUT_ID))
                .isInstanceOf(HaircutNotFoundException.class)
                .hasMessage(StringUtils3C.join("Haircut with id ", UNEXISTING_HAIRCUT_ID, " not found"));


        then(haircutRepository)
                .should(times(1))
                .findById(UNEXISTING_HAIRCUT_ID);
        then(haircutMock)
                .should(never())
                .toDto();
        then(imageService)
                .should(never())
                .findById(any());

    }

    @Test
    void findByName_thenReturnHaircutWithAvatar() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        HaircutDto haircutDto = haircutTestUtils.getHaircutDto();
        haircutDto.setAvatarId(imageDto.getId());
        Haircut haircutMock = mock(Haircut.class);

        when(haircutRepository.findByName(HAIRCUT_NAME))
                .thenReturn(Optional.of(haircutMock));
        when(haircutMock.toDto())
                .thenReturn(haircutDto);
        when(imageService.findById(imageDto.getId()))
                .thenReturn(imageDto);

        HaircutDto testedHaircut = haircutService.findByName(HAIRCUT_NAME);

        then(haircutRepository)
                .should(times(1))
                .findByName(HAIRCUT_NAME);
        then(haircutMock)
                .should(times(1))
                .toDto();
        then(imageService)
                .should(times(1))
                .findById(imageDto.getId());

        testedHaircut.setAvatar(imageDto);
        assertThat(testedHaircut).isEqualTo(haircutDto);
    }

    @Test
    void findByName_thenThrowHaircutNotFoundException() {
        Haircut haircutMock = mock(Haircut.class);

        when(haircutRepository.findByName(UNEXISTING_HAIRCUT_NAME))
                .thenReturn(Optional.empty());


        assertThatThrownBy(() -> haircutService.findByName(UNEXISTING_HAIRCUT_NAME))
                .isInstanceOf(HaircutNotFoundException.class)
                .hasMessage(StringUtils3C.join("Haircut with name ", UNEXISTING_HAIRCUT_NAME, " not found"));


        then(haircutRepository)
                .should(times(1))
                .findByName(UNEXISTING_HAIRCUT_NAME);
        then(haircutMock)
                .should(never())
                .toDto();
        then(imageService)
                .should(never())
                .findById(any());
    }

    @Test
    void findAll_thenReturnBarbers() {
        List<Haircut> haircutList = haircutTestUtils.getHaircutDtoList().stream().map(HaircutDto::toEntity).toList();
        when(haircutRepository.findAll())
                .thenReturn(haircutList);

        List<Haircut> all = haircutRepository.findAll();

        then(haircutRepository)
                .should(times(1))
                .findAll();

        assertThat(all).isEqualTo(haircutList);
    }

    @Test
    void findAll_thenReturnEmptyList() {
        when(haircutRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<Haircut> all = haircutRepository.findAll();

        then(haircutRepository)
                .should(times(1))
                .findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void create_thenSuccessfulCreate() {
        HaircutDto barberhaircutDto = haircutTestUtils.getHaircutDto();
        Haircut haircutMock = mock(Haircut.class);
        HaircutDto createdHaircutDtoMock = mock(HaircutDto.class);
        ImageDto imageDtoMock = mock(ImageDto.class);

        when(haircutRepository.save(any()))
                .thenReturn(haircutMock);
        when(haircutMock.toDto()).thenReturn(createdHaircutDtoMock);

        when(imageService.update(createdHaircutDtoMock.getAvatar()))
                .thenReturn(imageDtoMock);

        haircutService.create(barberhaircutDto);

        then(haircutRepository)
                .should(times(1))
                .save(any());
        then(imageService)
                .should(times(1))
                .create(createdHaircutDtoMock.getAvatar());
    }

    @Test
    void update_thenSuccessfulUpdate() {
        HaircutDto barberDto = haircutTestUtils.getHaircutDto();
        Haircut haircutMock = mock(Haircut.class);
        HaircutDto updatedHaircutDtoMock = mock(HaircutDto.class);
        ImageDto imageDtoMock = mock(ImageDto.class);

        when(haircutRepository.save(any()))
                .thenReturn(haircutMock);
        when(haircutMock.toDto()).thenReturn(updatedHaircutDtoMock);

        when(imageService.update(updatedHaircutDtoMock.getAvatar()))
                .thenReturn(imageDtoMock);

        haircutService.update(barberDto);

        then(haircutRepository)
                .should(times(1))
                .save(any());
        then(imageService)
                .should(times(1))
                .update(updatedHaircutDtoMock.getAvatar());
    }

    @Test
    void deleteById_thenSuccessfulDelete() {
        HaircutDto haircutDto = haircutTestUtils.getHaircutDto();
        doNothing().when(haircutRepository).deleteById(haircutDto.getId());
        doNothing().when(imageService).deleteById(haircutDto.getAvatarId());

        haircutService.delete(haircutDto);

        then(haircutRepository)
                .should(times(1))
                .deleteById(haircutDto.getId());
        then(imageService)
                .should(times(1))
                .deleteById(haircutDto.getAvatarId());
    }

    @Test
    void isUnique_thenUniqueByNotExistInDateBase() {
        HaircutDto haircutDto = haircutTestUtils.getHaircutDto();
        when(haircutRepository.findByName(haircutDto.getName())).thenReturn(Optional.of(haircutDto.toEntity()));

        boolean unique = haircutService.isUnique(haircutDto.getId(), haircutDto.getName());

        then(haircutRepository)
                .should(times(1))
                .findByName(haircutDto.getName());

        Assertions.assertThat(unique).isTrue();

    }

    @Test
    void isUnique_thenUniqueByEqualsId() {
        HaircutDto haircutDto = haircutTestUtils.getHaircutDto();
        when(haircutRepository.findByName(haircutDto.getName())).thenReturn(Optional.of(haircutDto.toEntity()));

        boolean unique = haircutService.isUnique(haircutDto.getId(), haircutDto.getName());

        then(haircutRepository)
                .should(times(1))
                .findByName(haircutDto.getName());

        Assertions.assertThat(unique).isTrue();
    }

    @Test
    void isUnique_thenNotUniqueById() {
        HaircutDto haircutDto = haircutTestUtils.getHaircutDto();
        when(haircutRepository.findByName(haircutDto.getName())).thenReturn(Optional.of(HaircutDto.builder().id(3L).name("ANY NAME").price(150).duration(90).avatarId(imageTestUtils.getNoImageId()).build().toEntity()));

        boolean unique = haircutService.isUnique(haircutDto.getId(), haircutDto.getName());

        then(haircutRepository)
                .should(times(1))
                .findByName(haircutDto.getName());

        Assertions.assertThat(unique).isFalse();
    }
}