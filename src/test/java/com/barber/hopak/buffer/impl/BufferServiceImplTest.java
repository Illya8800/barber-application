package com.barber.hopak.buffer.impl;

import com.barber.hopak.buffer.BufferContainerState;
import com.barber.hopak.buffer.BufferManager;
import com.barber.hopak.util.entity.ImageTestUtils;
import com.barber.hopak.web.domain.impl.ImageDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Optional;

import static com.barber.hopak.util.buffer.BufferTestUtils.BUFFERED_FILE_NAME;
import static com.barber.hopak.util.entity.ImageTestUtils.EXISTING_IMAGE_DTO_ID;
import static com.barber.hopak.util.entity.ImageTestUtils.EXISTING_IMAGE_DTO_NAME;
import static com.barber.hopak.util.entity.ImageTestUtils.IMAGE_DTO_BYTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BufferServiceImplTest {
    private final ImageTestUtils imageTestUtils;
    @Mock
    private BufferManager<File, Long> bufferManager;
    @Mock
    private BufferContainerState containerState;
    @InjectMocks
    BufferServiceImpl bufferService;

    @Autowired
    BufferServiceImplTest(ImageTestUtils imageTestUtils) {
        this.imageTestUtils = imageTestUtils;
    }

    @Test
    void save_thenSaveImage() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        File fileMock = mock(File.class);
        when(bufferManager.save(imageDto)).thenReturn(fileMock);
        when(containerState.isBufferContain(imageDto.getName())).thenReturn(false);


        bufferService.save(imageDto);

        then(bufferManager)
                .should()
                .save(imageDto);
        then(containerState)
                .should()
                .add(fileMock.getName());
    }

    @Test
    void save_thenNotSaveImage() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        File fileMock = mock(File.class);
        when(containerState.isBufferContain(imageDto.getName())).thenReturn(true);

        bufferService.save(imageDto);

        then(bufferManager)
                .should(never())
                .save(imageDto);
        then(containerState)
                .should(never())
                .add(fileMock.getName());
    }

    @Test
    void findImageById_thenFindImage() {
        when(containerState.isBufferContain(EXISTING_IMAGE_DTO_ID)).thenReturn(true);
        Optional<File> file = Optional.of(new File(BUFFERED_FILE_NAME));
        when(bufferManager.findFileById(EXISTING_IMAGE_DTO_ID)).thenReturn(file);
        when(bufferManager.getBytesByFile(file.get())).thenReturn(IMAGE_DTO_BYTES);

        Optional<ImageDto> image = bufferService.findImageById(EXISTING_IMAGE_DTO_ID);

        then(containerState).should()
                .isBufferContain(EXISTING_IMAGE_DTO_ID);
        then(bufferManager).should()
                .findFileById(EXISTING_IMAGE_DTO_ID);
        then(bufferManager).should()
                .getBytesByFile(file.get());
        assertThat(image).isPresent();
        assertThat(image.get().toEntity()).isEqualTo(imageTestUtils.getImageDto().toEntity());
    }

    @Test
    void findImageById_thenNotFind() {
        when(containerState.isBufferContain(EXISTING_IMAGE_DTO_ID)).thenReturn(false);
        Optional<File> file = Optional.of(new File(BUFFERED_FILE_NAME));

        Optional<ImageDto> image = bufferService.findImageById(EXISTING_IMAGE_DTO_ID);

        then(containerState)
                .should()
                .isBufferContain(EXISTING_IMAGE_DTO_ID);
        then(bufferManager)
                .should(never())
                .findFileById(EXISTING_IMAGE_DTO_ID);
        then(bufferManager)
                .should(never())
                .getBytesByFile(file.get());

        assertThat(image).isEmpty();
    }

    @Test
    void findImageByName_thenFindImage() {
        when(containerState.isBufferContain(EXISTING_IMAGE_DTO_NAME)).thenReturn(true);
        Optional<File> file = Optional.of(new File(BUFFERED_FILE_NAME));
        when(bufferManager.findFileByName(EXISTING_IMAGE_DTO_NAME)).thenReturn(file);
        when(bufferManager.getBytesByFile(file.get())).thenReturn(IMAGE_DTO_BYTES);

        Optional<ImageDto> image = bufferService.findImageByName(EXISTING_IMAGE_DTO_NAME);

        then(containerState).should()
                .isBufferContain(EXISTING_IMAGE_DTO_NAME);
        then(bufferManager).should()
                .findFileByName(EXISTING_IMAGE_DTO_NAME);
        then(bufferManager).should()
                .getBytesByFile(file.get());
        assertThat(image).isPresent();
        assertThat(image.get().toEntity()).isEqualTo(imageTestUtils.getImageDto().toEntity());
    }

    @Test
    void findImageByName_thenNotFind() {
        when(containerState.isBufferContain(EXISTING_IMAGE_DTO_NAME)).thenReturn(false);
        Optional<File> file = Optional.of(new File(BUFFERED_FILE_NAME));

        Optional<ImageDto> image = bufferService.findImageByName(EXISTING_IMAGE_DTO_NAME);

        then(containerState)
                .should()
                .isBufferContain(EXISTING_IMAGE_DTO_NAME);
        then(bufferManager)
                .should(never())
                .findFileByName(EXISTING_IMAGE_DTO_NAME);
        then(bufferManager)
                .should(never())
                .getBytesByFile(file.get());

        assertThat(image).isEmpty();
    }

    @Test
    void deleteImageByName_thenDelete() {
        File fileMock = mock(File.class);
        Optional<File> file = Optional.of(fileMock);

        when(bufferManager.findFileByName(EXISTING_IMAGE_DTO_NAME)).thenReturn(file);
        when(fileMock.exists()).thenReturn(true);
        when(fileMock.delete()).thenReturn(true);
        doNothing().when(containerState).removeByName(EXISTING_IMAGE_DTO_NAME);

        bufferService.deleteImageByName(EXISTING_IMAGE_DTO_NAME);

        then(bufferManager)
                .should()
                .findFileByName(EXISTING_IMAGE_DTO_NAME);

        verify(file.get(), times(1)).exists();
        verify(file.get(), times(1)).delete();

        then(containerState)
                .should()
                .removeByName(EXISTING_IMAGE_DTO_NAME);
    }

    @Test
    void deleteImageByName_thenNotDelete() {
        Optional<File> file = Optional.empty();
        when(bufferManager.findFileByName(EXISTING_IMAGE_DTO_NAME)).thenReturn(file);

        bufferService.deleteImageByName(EXISTING_IMAGE_DTO_NAME);

        then(bufferManager)
                .should()
                .findFileByName(EXISTING_IMAGE_DTO_NAME);
        then(containerState)
                .should(never())
                .removeByName(EXISTING_IMAGE_DTO_NAME);
    }

    @Test
    void deleteImageById_thenDelete() {
        File fileMock = mock(File.class);
        Optional<File> file = Optional.of(fileMock);

        when(bufferManager.findFileById(EXISTING_IMAGE_DTO_ID)).thenReturn(file);
        when(fileMock.exists()).thenReturn(true);
        when(fileMock.delete()).thenReturn(true);
        doNothing().when(containerState).removeById(EXISTING_IMAGE_DTO_ID);

        bufferService.deleteImageById(EXISTING_IMAGE_DTO_ID);

        then(bufferManager)
                .should()
                .findFileById(EXISTING_IMAGE_DTO_ID);

        verify(file.get(), times(1)).exists();
        verify(file.get(), times(1)).delete();

        then(containerState)
                .should()
                .removeById(EXISTING_IMAGE_DTO_ID);
    }

    @Test
    void deleteImageById_thenNotDelete() {
        Optional<File> file = Optional.empty();
        when(bufferManager.findFileById(EXISTING_IMAGE_DTO_ID)).thenReturn(file);

        bufferService.deleteImageById(EXISTING_IMAGE_DTO_ID);

        then(bufferManager)
                .should()
                .findFileById(EXISTING_IMAGE_DTO_ID);
        then(containerState)
                .should(never())
                .removeById(EXISTING_IMAGE_DTO_ID);
    }
}