package com.barber.hopak.util.buffer;

import com.barber.hopak.config.BufferPathConfig;
import com.barber.hopak.exception.buffer.BufferCantBeDeletedException;
import com.barber.hopak.exception.buffer.BufferedFileCantBeDeletedException;
import com.barber.hopak.exception.buffer.ImageCantBeConvertedForBufferException;
import com.barber.hopak.exception.buffer.ImagesBufferNotFountException;
import com.barber.hopak.util.ArraysC;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.web.domain.impl.ImageDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static com.barber.hopak.util.ImageUtil.BUFFER_FOLDER_NAME;
import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.FOLDER_SEPARATOR;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;
import static com.barber.hopak.util.entity.ImageTestUtils.EXISTING_IMAGE_DTO_NAME;

@Component
@RequiredArgsConstructor
public class BufferTestUtils {
    private final BufferPathConfig bufferPathConfig;
    public static final String UNEXISTING_FILE_NAME = " ";
    public static final String EXISTING_FILE_NAME = "testFile.png";
    public static final Long EXISTING_FILE_ID = 2L;
    public static final Long UNEXISTING_FILE_ID = -1L;
    public static final String BUFFERED_FILE_NAME = StringUtils3C.join(EXISTING_FILE_ID, ID_SEPARATOR, EXISTING_FILE_NAME, DOT_TXT);
    private String BUFFER_FOLDER_PATH;

    @PostConstruct
    void init() {
        BUFFER_FOLDER_PATH = bufferPathConfig.getPath();
    }

    public String getBufferFolderPath() {
        return StringUtils3C.join(BUFFER_FOLDER_PATH, FOLDER_SEPARATOR, BUFFER_FOLDER_NAME);
    }

    public void createTestFile() {
        try {
            final String BUFFERED_FILE_NAME = StringUtils3C.join(EXISTING_FILE_ID, ID_SEPARATOR, EXISTING_IMAGE_DTO_NAME, DOT_TXT);
            boolean isCreated = new File(getBufferFolderPath(), BUFFERED_FILE_NAME).createNewFile();
            if (!isCreated) throw new RuntimeException("Test image file can't be created");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteTestFile(String bufferedFileName) {
        return new File(getBufferFolderPath(), bufferedFileName).delete();
    }


    public File fillTestFile(ImageDto imageDto) {
        final String BUFFERED_FILE_NAME = StringUtils3C.join(EXISTING_FILE_ID, ID_SEPARATOR, EXISTING_FILE_NAME, DOT_TXT);
        File file = new File(getBufferFolderPath(), BUFFERED_FILE_NAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(ArraysC.toString(imageDto.getImage().getBytes()));
            return file;
        } catch (IOException e) {
            throw new ImageCantBeConvertedForBufferException("Image can't be saved as txt file." + e.getMessage());
        }

    }

    public void initBuffer() {
        createTestPackage();
    }

    private void createTestPackage() {
        File file = new File(getBufferFolderPath());
        boolean mkdir = file.mkdir();
        if (!mkdir && !file.exists()) throw new ImagesBufferNotFountException("Images buffer doesn't created");
    }

    public void destroyBuffer() {
        final File bufferFolder = new File(getBufferFolderPath());
        if (!bufferFolder.exists()) return;
        boolean deleted = bufferFolder.delete();
        if (!deleted) {
            Arrays.stream(Objects.requireNonNull(bufferFolder.listFiles())).forEach(file -> {
                String fileName = file.getName();
                boolean isFileDeleted = new File(bufferFolder, fileName).delete();
                if (!isFileDeleted) throw new BufferedFileCantBeDeletedException("Buffered file can't be deleted");
            });
            deleted = bufferFolder.delete();
            if (!deleted) throw new BufferCantBeDeletedException("Buffer doesn't deleted");
        }
    }
}
