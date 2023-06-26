package com.barber.hopak.buffer.impl;

import com.barber.hopak.buffer.BufferState;
import com.barber.hopak.buffer.FileSearcher;
import com.barber.hopak.exception.buffer.ImageCantBeConvertedForBufferException;
import com.barber.hopak.exception.entity.image.inh.SaveImageException;
import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileWithoutPath;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.buffer.BufferTestUtils;
import com.barber.hopak.util.entity.ImageTestUtils;
import com.barber.hopak.web.domain.impl.ImageDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;
import static com.barber.hopak.util.buffer.BufferTestUtils.EXISTING_FILE_ID;
import static com.barber.hopak.util.buffer.BufferTestUtils.EXISTING_FILE_NAME;
import static com.barber.hopak.util.buffer.BufferTestUtils.UNEXISTING_FILE_ID;
import static com.barber.hopak.util.buffer.BufferTestUtils.UNEXISTING_FILE_NAME;
import static com.barber.hopak.util.entity.ImageTestUtils.IMAGE_DTO_BYTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class BufferManagerImplTest {
    private final BufferTestUtils bufferTestUtils;
    private final ImageTestUtils imageTestUtils;
    private final BufferState bufferState;
    @Mock
    private BufferState bufferStateMock;
    @Mock
    private FileSearcher fileSearcher;
    @InjectMocks
    private BufferManagerImpl bufferManager;

    @Autowired
    BufferManagerImplTest(BufferTestUtils bufferTestUtils, ImageTestUtils imageTestUtils, BufferState bufferState) {
        this.bufferTestUtils = bufferTestUtils;
        this.imageTestUtils = imageTestUtils;
        this.bufferState = bufferState;
    }

    @BeforeEach
    void initBuffer() {
        bufferTestUtils.initBuffer();
    }

    @AfterEach
    void destroyBuffer() {
        bufferTestUtils.destroyBuffer();
    }

    @Test
    void save_thenSaveFile() {
        ImageDto imageDto = imageTestUtils.getImageDto();
        when(bufferStateMock.getBufferPath()).thenReturn(bufferState.getBufferPath());

        File file = bufferManager.save(imageDto);

        then(bufferStateMock)
                .should(times(1))
                .getBufferPath();

        String BUFFERED_FILE_NAME = StringUtils3C.join(imageDto.getId(), ID_SEPARATOR, imageDto.getName(), DOT_TXT);
        assertThat(file.getName()).isEqualTo(BUFFERED_FILE_NAME);
        boolean isDeleted = bufferTestUtils.deleteTestFile(imageTestUtils.getBufferedFileName());
        assertThat(isDeleted).isTrue();
    }

    @Test
    void save_thenThrow() throws IOException {
        ImageDto imageDtoMock = mock(ImageDto.class);
        MultipartFile imageMock = mock(MultipartFileWithoutPath.class);
        when(bufferStateMock.getBufferPath()).thenReturn(bufferState.getBufferPath());
        when(imageDtoMock.getId()).thenReturn(EXISTING_FILE_ID);
        when(imageDtoMock.getName()).thenReturn(EXISTING_FILE_NAME);
        when(imageDtoMock.getImage()).thenReturn(imageMock);
        IOException ioExceptionMock = mock(IOException.class);
        String exceptionText = "anyText";
        when(ioExceptionMock.getMessage()).thenReturn(exceptionText);
        willThrow(ioExceptionMock).given(imageMock).getBytes();

        assertThatThrownBy(() -> bufferManager.save(imageDtoMock))
                .isInstanceOf(SaveImageException.class)
                .hasMessage("Image can't be saved as txt file." + exceptionText);

        then(bufferStateMock)
                .should(times(1))
                .getBufferPath();
    }

    @Test
    void findFileById_thenFind() {
        when(fileSearcher.getFileById(EXISTING_FILE_ID))
                .thenReturn(Optional.of(new File(imageTestUtils.getBufferedFileName())));

        Optional<File> file = bufferManager.findFileById(EXISTING_FILE_ID);

        then(fileSearcher)
                .should()
                .getFileById(EXISTING_FILE_ID);

        assertThat(file).isPresent();
        assertThat(file.get().getName()).isEqualTo(imageTestUtils.getBufferedFileName());
    }

    @Test
    void findFileById_thenNotFind() {
        when(fileSearcher.getFileById(UNEXISTING_FILE_ID))
                .thenReturn(Optional.empty());

        Optional<File> file = bufferManager.findFileById(UNEXISTING_FILE_ID);

        then(fileSearcher)
                .should()
                .getFileById(UNEXISTING_FILE_ID);

        assertThat(file).isEmpty();
    }

    @Test
    void findFileByName_thenFind() {
        when(fileSearcher.getFileByName(EXISTING_FILE_NAME))
                .thenReturn(Optional.of(new File(imageTestUtils.getBufferedFileName())));

        Optional<File> file = bufferManager.findFileByName(EXISTING_FILE_NAME);

        then(fileSearcher)
                .should()
                .getFileByName(EXISTING_FILE_NAME);

        assertThat(file).isPresent();
        assertThat(file.get().getName()).isEqualTo(imageTestUtils.getBufferedFileName());
    }

    @Test
    void findFileByName_thenNotFind() {
        when(fileSearcher.getFileByName(UNEXISTING_FILE_NAME))
                .thenReturn(Optional.empty());

        Optional<File> file = bufferManager.findFileByName(UNEXISTING_FILE_NAME);

        then(fileSearcher)
                .should()
                .getFileByName(UNEXISTING_FILE_NAME);

        assertThat(file).isEmpty();
    }

    @Test
    void getBytesByFile_thenGetBytes() {
        bufferTestUtils.createTestFile();
        File file = bufferTestUtils.fillTestFile(imageTestUtils.getImageDto());
        byte[] bytesByFile = bufferManager.getBytesByFile(file);
        assertThat(IMAGE_DTO_BYTES).isEqualTo(bytesByFile);
        boolean isDeleted = bufferTestUtils.deleteTestFile(file.getName());
        assertThat(isDeleted).isTrue();
    }

    @Test
    void getBytesByFile_thenThrowImageCantBeConvertedException() {
        File fileMock = mock(File.class);
        when(fileMock.exists()).thenReturn(false);

        assertThatThrownBy(() -> bufferManager.getBytesByFile(fileMock))
                .isInstanceOf(ImageCantBeConvertedForBufferException.class)
                .hasMessage("Image can't be converted from the txt file to byte[]");

    }
}