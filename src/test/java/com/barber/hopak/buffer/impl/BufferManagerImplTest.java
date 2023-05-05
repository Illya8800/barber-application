package com.barber.hopak.buffer.impl;

import com.barber.hopak.buffer.FileSearcher;
import com.barber.hopak.exception.buffer.ImageCantBeConvertedException;
import com.barber.hopak.exception.image.SaveImageException;
import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileFromDateBase;
import com.barber.hopak.util.ImageUtils;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.buffer.BufferUtils;
import com.barber.hopak.web.domain.impl.ImageDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;
import static com.barber.hopak.util.ImageUtils.getBufferedFileName;
import static com.barber.hopak.util.buffer.BufferUtils.createTestFile;
import static com.barber.hopak.util.buffer.BufferUtils.deleteTestFile;
import static com.barber.hopak.util.buffer.BufferUtils.fillTestFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class BufferManagerImplTest {

    @Mock
    FileSearcher fileSearcher;
    @InjectMocks
    private BufferManagerImpl bufferManager;

    @BeforeAll
    static void initBuffer() {
        BufferUtils.initBuffer();
    }

    @AfterAll
    static void destroyBuffer() {
        BufferUtils.destroyBuffer();
    }

    @Test
    void save_thenSaveFile() {
        ImageDto imageDto = ImageUtils.getImageDto();
        when(fileSearcher.getBufferPath()).thenReturn(BufferUtils.getBufferFolderPath());

        File file = bufferManager.save(imageDto);

        then(fileSearcher).should().getBufferPath();

        String BUFFERED_FILE_NAME = StringUtils3C.join(imageDto.getId(), ID_SEPARATOR, imageDto.getImageName(), DOT_TXT);
        assertThat(file.getName()).isEqualTo(BUFFERED_FILE_NAME);
        boolean isDeleted = deleteTestFile(getBufferedFileName());
        assertThat(isDeleted).isTrue();
    }

    @Test
    void save_thenThrow() throws IOException {
        ImageDto imageDto = mock(ImageDto.class);
        MultipartFile imageMock = mock(MultipartFileFromDateBase.class);
        when(imageDto.getId()).thenReturn(ImageUtils.IMAGE_DTO_ID);
        when(imageDto.getImageName()).thenReturn(ImageUtils.IMAGE_DTO_NAME);
        when(imageDto.getImage()).thenReturn(imageMock);
        IOException ioException = mock(IOException.class);

        String exceptionText = "anyText";
        when(ioException.getMessage()).thenReturn(exceptionText);
        willThrow(ioException).given(imageMock).getBytes();

        assertThatThrownBy(() -> bufferManager.save(imageDto))
                .isInstanceOf(SaveImageException.class)
                .hasMessage("Image can't be saved as txt file." + exceptionText);
    }

    @Test
    void getBytesByFile_thenGetBytes() {
        createTestFile();
        File file = fillTestFile(ImageUtils.getImageDto());
        byte[] bytesByFile = bufferManager.getBytesByFile(file);
        assertThat(ImageUtils.IMAGE_DTO_BYTES).isEqualTo(bytesByFile);
        boolean isDeleted = deleteTestFile(getBufferedFileName());
        assertThat(isDeleted).isTrue();
    }

    @Test
    void getBytesByFile_thenThrowImageCantBeConvertedException() {
        createTestFile();
        File file = mock(File.class);
        when(file.exists()).thenReturn(false);

        assertThatThrownBy(() -> bufferManager.getBytesByFile(file), "Test image file can't be created", ImageCantBeConvertedException.class);
        deleteTestFile(BufferUtils.BUFFERED_FILE_NAME);
    }
}