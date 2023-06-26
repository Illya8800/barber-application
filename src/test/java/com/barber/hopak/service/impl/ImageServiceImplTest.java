package com.barber.hopak.service.impl;

import com.barber.hopak.buffer.BufferService;
import com.barber.hopak.buffer.BufferState;
import com.barber.hopak.exception.entity.image.ImageNotFoundException;
import com.barber.hopak.model.enumeration.ImageExtensions;
import com.barber.hopak.model.impl.Image;
import com.barber.hopak.repository.ImageRepository;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.entity.ImageTestUtils;
import com.barber.hopak.web.domain.impl.ImageDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.barber.hopak.util.entity.ImageTestUtils.EXISTING_IMAGE_DTO_ID;
import static com.barber.hopak.util.entity.ImageTestUtils.EXISTING_IMAGE_DTO_NAME;
import static com.barber.hopak.util.entity.ImageTestUtils.UNEXISTING_IMAGE_DTO_ID;
import static com.barber.hopak.util.entity.ImageTestUtils.UNEXISTING_IMAGE_DTO_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class ImageServiceImplTest {
    private final ImageTestUtils imageTestUtils;
    private final BufferState bufferState;
    @Mock
    private BufferService<ImageDto> bufferService;
    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Autowired
    ImageServiceImplTest(ImageTestUtils imageTestUtils, BufferState bufferState) {
        this.imageTestUtils = imageTestUtils;
        this.bufferState = bufferState;
    }

    @AfterEach
    void destroyBuffer() {
        bufferState.destroy();
    }

    @Test
    void findById_thenFindInBuffer() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        when(bufferService.findImageById(EXISTING_IMAGE_DTO_ID)).thenReturn(Optional.of(imageDto));

        imageService.findById(EXISTING_IMAGE_DTO_ID);

        then(bufferService)
                .should(times(1))
                .findImageById(EXISTING_IMAGE_DTO_ID);
        then(imageRepository)
                .should(never())
                .findById(EXISTING_IMAGE_DTO_ID);
    }

    @Test
    void findById_thenNotFindInBufferButFindInDateBase() {
        Image image = mock(Image.class);
        ImageDto imageDto = mock(ImageDto.class);
        when(bufferService.findImageById(EXISTING_IMAGE_DTO_ID)).thenReturn(Optional.empty());
        when(imageRepository.findById(EXISTING_IMAGE_DTO_ID)).thenReturn(Optional.of(image));
        when(image.toDto()).thenReturn(imageDto);

        imageService.findById(EXISTING_IMAGE_DTO_ID);

        then(bufferService)
                .should(times(1))
                .findImageById(EXISTING_IMAGE_DTO_ID);
        then(imageRepository)
                .should(times(1))
                .findById(EXISTING_IMAGE_DTO_ID);
        then(image)
                .should(times(2))
                .toDto();
        then(bufferService)
                .should(times(1))
                .save(imageDto);
    }

    @Test
    void findById_thenNotFindInBufferAndNotFindInDateBase() {
        when(bufferService.findImageById(UNEXISTING_IMAGE_DTO_ID)).thenReturn(Optional.empty());
        when(imageRepository.findById(UNEXISTING_IMAGE_DTO_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> imageService.findById(UNEXISTING_IMAGE_DTO_ID))
                .isInstanceOf(ImageNotFoundException.class)
                .hasMessage(StringUtils3C.join("Image with id ", UNEXISTING_IMAGE_DTO_ID, " not found"));

        then(bufferService)
                .should(times(1))
                .findImageById(UNEXISTING_IMAGE_DTO_ID);
        then(imageRepository)
                .should(times(1))
                .findById(UNEXISTING_IMAGE_DTO_ID);

        then(bufferService)
                .should(never())
                .save(any());
    }

    @Test
    void findByName_thenFindInBuffer() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        when(bufferService.findImageByName(EXISTING_IMAGE_DTO_NAME)).thenReturn(Optional.of(imageDto));

        imageService.findByName(EXISTING_IMAGE_DTO_NAME);

        then(bufferService)
                .should(times(1))
                .findImageByName(EXISTING_IMAGE_DTO_NAME);
        then(imageRepository)
                .should(never())
                .findByName(EXISTING_IMAGE_DTO_NAME);
    }

    @Test
    void findByName_thenNotFindInBufferButFindInDateBase() {
        Image image = mock(Image.class);
        ImageDto imageDto = mock(ImageDto.class);
        when(bufferService.findImageByName(EXISTING_IMAGE_DTO_NAME)).thenReturn(Optional.empty());
        when(imageRepository.findByName(EXISTING_IMAGE_DTO_NAME)).thenReturn(Optional.of(image));
        when(image.toDto()).thenReturn(imageDto);

        imageService.findByName(EXISTING_IMAGE_DTO_NAME);

        then(bufferService)
                .should(times(1))
                .findImageByName(EXISTING_IMAGE_DTO_NAME);
        then(imageRepository)
                .should(times(1))
                .findByName(EXISTING_IMAGE_DTO_NAME);
        then(image)
                .should(times(2))
                .toDto();
        then(bufferService)
                .should(times(1))
                .save(imageDto);
    }

    @Test
    void findByName_thenNotFindInBufferAndNotFindInDateBase() {
        when(bufferService.findImageByName(UNEXISTING_IMAGE_DTO_NAME)).thenReturn(Optional.empty());
        when(imageRepository.findByName(UNEXISTING_IMAGE_DTO_NAME)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> imageService.findByName(UNEXISTING_IMAGE_DTO_NAME))
                .isInstanceOf(ImageNotFoundException.class)
                .hasMessage("Image with name " + UNEXISTING_IMAGE_DTO_NAME + " not found");

        then(bufferService)
                .should(times(1))
                .findImageByName(UNEXISTING_IMAGE_DTO_NAME);
        then(imageRepository)
                .should(times(1))
                .findByName(UNEXISTING_IMAGE_DTO_NAME);

        then(bufferService)
                .should(never())
                .save(any());
    }

    @Test
    void findAllImages_thenFindNotEmptyList() {
        List<ImageDto> imageListMock = imageTestUtils.getImageList().stream().map(Image::toDto).toList();

        when(imageRepository.findAll()).thenReturn(imageTestUtils.getImageList());
        doNothing().when(bufferService).save(any());

        List<ImageDto> allImages = imageService.findAll();

        then(imageRepository)
                .should(times(1))
                .findAll();
        then(bufferService)
                .should(times(imageListMock.size()))
                .save(any());

        assertThat(allImages.stream().map(ImageDto::toEntity).toList()).isEqualTo(imageListMock.stream().map(ImageDto::toEntity).toList());
    }

    @Test
    void findAllImages_thenFindEmptyList() {
        when(imageRepository.findAll()).thenReturn(Collections.emptyList());
        doNothing().when(bufferService).save(any());

        List<ImageDto> allImages = imageService.findAll();

        then(imageRepository)
                .should(times(1))
                .findAll();
        then(bufferService)
                .should(never())
                .save(any());

        assertThat(allImages).isEmpty();
    }

    @Test
    void create_thenSuccessfulCreate() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        when(imageRepository.save(any())).thenReturn(imageTestUtils.getImageDto().toEntity());
        doNothing().when(bufferService).save(imageDto);

        ImageDto savedImageDto = imageService.create(imageDto);

        then(imageRepository)
                .should(times(1))
                .save(any());
        then(bufferService)
                .should(times(1))
                .save(any());

        assertThat(savedImageDto.toEntity()).isEqualTo(imageDto.toEntity());
    }

    @Test
    void update_thenSuccessfulUpdate() {
        ImageDto imageDto = imageTestUtils.getImageDto();

        when(imageRepository.save(any())).thenReturn(imageTestUtils.getImageDto().toEntity());
        doNothing().when(bufferService).save(imageDto);

        ImageDto savedImageDto = imageService.update(imageDto);

        then(imageRepository)
                .should(times(1))
                .save(any());
        then(bufferService)
                .should(times(1))
                .save(any());

        assertThat(savedImageDto.toEntity()).isEqualTo(imageDto.toEntity());
    }

    @Test
    void deleteById_thenSuccessfulDelete() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        doNothing().when(imageRepository).deleteById(imageDto.getId());
        doNothing().when(bufferService).deleteImageById(imageDto.getId());

        imageService.deleteById(imageDto.getId());

        then(imageRepository)
                .should(times(1))
                .deleteById(imageDto.getId());
        then(bufferService)
                .should(times(1))
                .deleteImageById(imageDto.getId());
    }

    @Test
    void isUnique_thenUniqueByNotExistInDateBase() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        when(imageRepository.findByName(imageDto.getName())).thenReturn(Optional.empty());

        boolean unique = imageService.isUnique(imageDto.getId(), imageDto.getName());

        then(imageRepository)
                .should(times(1))
                .findByName(imageDto.getName());

        assertThat(unique).isTrue();
    }

    @Test
    void isUnique_thenUniqueByEqualsId() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        when(imageRepository.findByName(imageDto.getName())).thenReturn(Optional.of(imageDto.toEntity()));

        boolean unique = imageService.isUnique(imageDto.getId(), imageDto.getName());

        then(imageRepository)
                .should(times(1))
                .findByName(imageDto.getName());

        assertThat(unique).isTrue();
    }

    @Test
    void isUnique_thenNotUniqueById() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        when(imageRepository.findByName(imageDto.getName())).thenReturn(Optional.of(ImageDto.builder().id(3L).name("anotherName").image(imageDto.getImage()).build().toEntity()));

        boolean unique = imageService.isUnique(imageDto.getId(), imageDto.getName());

        then(imageRepository)
                .should(times(1))
                .findByName(imageDto.getName());

        assertThat(unique).isFalse();
    }

    @Test
    void setImageNameByOriginalFileName_theSetName() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        imageService.setImageNameByOriginalFileName(imageDto);
        assertThat(imageDto.getName()).isNotBlank();
        assertThat(imageDto.getName()).isNotEmpty();
        assertThat(imageDto.getName()).contains(imageDto.getName());

    }

    @Test
    void isExtensionValid_thenCorrectExtensions() {
        String basicName = "basicImageName";
        Arrays.stream(ImageExtensions.values()).forEach(extension -> {
            String fileNameWithExtension = basicName.concat(ImageExtensions.getDotExtension(extension));
            boolean extensionValid = imageService.isExtensionValid(fileNameWithExtension);
            assertThat(extensionValid).isTrue();
        });
    }

    @Test
    void isExtensionValid_thenNotCorrectExtensions() {
        String basicName = "basicImageName";
        List.of(".pn", ".jppg", ".jpgpng", ".pngjpg", "pn", "jppg", "jpgpng", "pngjpg").forEach(extension -> {
            String fileNameWithExtension = basicName.concat(extension);
            boolean extensionValid = imageService.isExtensionValid(fileNameWithExtension);
            assertThat(extensionValid).isFalse();
        });
    }
}