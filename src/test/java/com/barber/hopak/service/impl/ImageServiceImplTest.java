package com.barber.hopak.service.impl;

import com.barber.hopak.buffer.BufferService;
import com.barber.hopak.exception.image.ImageNotFoundException;
import com.barber.hopak.model.enumeration.ImageExtensions;
import com.barber.hopak.model.impl.Image;
import com.barber.hopak.repository.ImageRepository;
import com.barber.hopak.util.ImageUtils;
import com.barber.hopak.util.buffer.BufferUtils;
import com.barber.hopak.web.domain.impl.ImageDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.barber.hopak.util.ImageUtils.EXISTING_IMAGE_DTO_ID;
import static com.barber.hopak.util.ImageUtils.EXISTING_IMAGE_DTO_NAME;
import static com.barber.hopak.util.ImageUtils.UNEXISTING_IMAGE_DTO_ID;
import static com.barber.hopak.util.ImageUtils.UNEXISTING_IMAGE_DTO_NAME;
import static com.barber.hopak.util.ImageUtils.getImageDto;
import static com.barber.hopak.util.ImageUtils.getImageList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class ImageServiceImplTest {
    @Mock
    private BufferService<ImageDto> bufferService;
    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @AfterAll
    static void destroyBuffer() {
        BufferUtils.destroyBuffer();
    }

    @Test
    void findById_thenFindInBuffer() {
        ImageDto imageDto = ImageUtils.getImageDto();
        when(bufferService.findImageById(EXISTING_IMAGE_DTO_ID)).thenReturn(Optional.of(imageDto));

        imageService.findById(EXISTING_IMAGE_DTO_ID);

        then(bufferService)
                .should()
                .findImageById(EXISTING_IMAGE_DTO_ID);
        then(imageRepository)
                .should(never())
                .findById(EXISTING_IMAGE_DTO_ID);
    }

    @Test
    void findById_thenNotFindInBufferButFindInDateBase() {
        ImageDto imageDto = ImageUtils.getImageDto();
        when(bufferService.findImageById(EXISTING_IMAGE_DTO_ID)).thenReturn(Optional.empty());
        when(imageRepository.findById(EXISTING_IMAGE_DTO_ID)).thenReturn(Optional.of(imageDto.toEntity()));

        imageService.findById(EXISTING_IMAGE_DTO_ID);

        then(bufferService)
                .should()
                .findImageById(EXISTING_IMAGE_DTO_ID);
        then(imageRepository)
                .should()
                .findById(EXISTING_IMAGE_DTO_ID);

        then(bufferService)
                .should()
                .save(imageDto);
    }

    @Test
    void findById_thenNotFindInBufferAndNotFindInDateBase() {
        when(bufferService.findImageById(UNEXISTING_IMAGE_DTO_ID)).thenReturn(Optional.empty());
        when(imageRepository.findById(UNEXISTING_IMAGE_DTO_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> imageService.findById(UNEXISTING_IMAGE_DTO_ID))
                .isInstanceOf(ImageNotFoundException.class)
                .hasMessage("Image with id " + UNEXISTING_IMAGE_DTO_ID + " not found");

        then(bufferService)
                .should()
                .findImageById(UNEXISTING_IMAGE_DTO_ID);
        then(imageRepository)
                .should()
                .findById(UNEXISTING_IMAGE_DTO_ID);

        then(bufferService)
                .should(never())
                .save(any());
    }

    @Test
    void findByName_thenFindInBuffer() {
        ImageDto imageDto = ImageUtils.getImageDto();
        when(bufferService.findImageByName(EXISTING_IMAGE_DTO_NAME)).thenReturn(Optional.of(imageDto));

        imageService.findByName(EXISTING_IMAGE_DTO_NAME);

        then(bufferService)
                .should()
                .findImageByName(EXISTING_IMAGE_DTO_NAME);
        then(imageRepository)
                .should(never())
                .findByName(EXISTING_IMAGE_DTO_NAME);
    }

    @Test
    void findByName_thenNotFindInBufferButFindInDateBase() {
        ImageDto imageDto = ImageUtils.getImageDto();
        when(bufferService.findImageByName(EXISTING_IMAGE_DTO_NAME)).thenReturn(Optional.empty());
        when(imageRepository.findByName(EXISTING_IMAGE_DTO_NAME)).thenReturn(Optional.of(imageDto.toEntity()));

        imageService.findByName(EXISTING_IMAGE_DTO_NAME);

        then(bufferService)
                .should()
                .findImageByName(EXISTING_IMAGE_DTO_NAME);
        then(imageRepository)
                .should()
                .findByName(EXISTING_IMAGE_DTO_NAME);

        then(bufferService)
                .should()
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
                .should()
                .findImageByName(UNEXISTING_IMAGE_DTO_NAME);
        then(imageRepository)
                .should()
                .findByName(UNEXISTING_IMAGE_DTO_NAME);

        then(bufferService)
                .should(never())
                .save(any());
    }

    @Test
    void findAllImages_thenFindNotEmptyList() {
        List<ImageDto> imageListMock = StreamSupport.stream(getImageList().spliterator(), false).map(Image::toDto).toList();

        when(imageRepository.findAll()).thenReturn(getImageList());
        doNothing().when(bufferService).save(any());

        List<ImageDto> allImages = imageService.findAllImages();

        then(imageRepository)
                .should()
                .findAll();
        then(bufferService)
                .should(times(imageListMock.size()))
                .save(any());

        assertThat(allImages).isEqualTo(imageListMock);
    }

    @Test
    void findAllImages_thenFindEmptyList() {
        when(imageRepository.findAll()).thenReturn(Collections.emptyList());
        doNothing().when(bufferService).save(any());

        List<ImageDto> allImages = imageService.findAllImages();

        then(imageRepository)
                .should()
                .findAll();
        then(bufferService)
                .should(never())
                .save(any());

        assertThat(allImages).isEmpty();
    }

    @Test
    void create_thenSuccessfulCreate() {
        ImageDto imageDto = getImageDto();
        when(imageRepository.save(any())).thenReturn(getImageDto().toEntity());
        doNothing().when(bufferService).save(imageDto);

        ImageDto savedImageDto = imageService.create(imageDto);

        then(imageRepository)
                .should(times(1))
                .save(any());
        then(bufferService)
                .should()
                .save(any());

        assertThat(savedImageDto).isEqualTo(imageDto);
    }

    @Test
    void update_thenSuccessfulUpdate() {
        ImageDto imageDto = getImageDto();

        when(imageRepository.save(any())).thenReturn(getImageDto().toEntity());
        doNothing().when(bufferService).save(imageDto);

        ImageDto savedImageDto = imageService.update(imageDto);

        then(imageRepository)
                .should(times(1))
                .save(any());
        then(bufferService)
                .should()
                .save(any());

        assertThat(savedImageDto).isEqualTo(imageDto);
    }

    @Test
    void deleteById() {
        ImageDto imageDto = getImageDto();
        doNothing().when(imageRepository).deleteById(imageDto.getId());
        doNothing().when(bufferService).deleteImageById(imageDto.getId());

        imageService.deleteById(imageDto.getId());

        then(imageRepository)
                .should()
                .deleteById(imageDto.getId());
        then(bufferService)
                .should()
                .deleteImageById(imageDto.getId());
    }

    @Test
    void isUnique_thenUniqueByNotExistInDateBase() {
        ImageDto imageDto = getImageDto();
        when(imageRepository.findByName(imageDto.getName())).thenReturn(Optional.empty());

        boolean unique = imageService.isUnique(imageDto.getId(), imageDto.getName());

        then(imageRepository)
                .should()
                .findByName(imageDto.getName());

        assertThat(unique).isTrue();
    }

    @Test
    void isUnique_thenUniqueByEqualsId() {
        ImageDto imageDto = getImageDto();
        when(imageRepository.findByName(imageDto.getName())).thenReturn(Optional.of(imageDto.toEntity()));

        boolean unique = imageService.isUnique(imageDto.getId(), imageDto.getName());

        then(imageRepository)
                .should()
                .findByName(imageDto.getName());

        assertThat(unique).isTrue();
    }

    @Test
    void isUnique_thenNotUniqueById() {
        ImageDto imageDto = getImageDto();
        when(imageRepository.findByName(imageDto.getName())).thenReturn(Optional.of(ImageDto.builder().id(3L).name("anotherName").image(imageDto.getImage()).build().toEntity()));

        boolean unique = imageService.isUnique(imageDto.getId(), imageDto.getName());

        then(imageRepository)
                .should()
                .findByName(imageDto.getName());

        assertThat(unique).isFalse();
    }

    @Test
    void setImageNameByOriginalFileName_theSetName() {
        ImageDto imageDto = getImageDto();
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