package com.barber.hopak.buffer.impl;

import com.barber.hopak.buffer.BufferManager;
import com.barber.hopak.buffer.BufferedFileName;
import com.barber.hopak.buffer.FileSearcher;
import com.barber.hopak.exception.buffer.ImageCantBeConvertedException;
import com.barber.hopak.exception.image.SaveImageException;
import com.barber.hopak.util.ArraysC;
import com.barber.hopak.web.domain.impl.ImageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.barber.hopak.util.ImageUtil.CONVERTER_SPLIT_REGEX;

@Component
@Log4j2
@RequiredArgsConstructor
public class BufferManagerImpl implements BufferManager<File, Long> {
    private final FileSearcher fileSearcher;

    /**
     * @param imageDto should contain id, imageName and the image
     *                 It will save in the BASIC_BUFFER_PATH.
     *                 File will get the next name "id-imageName.png.txt". Example: 1-no-image.png.txt
     * @return file with the image's bytes
     * @throws ImageCantBeConvertedException If image can't be saved
     */
    @Override
    public File save(ImageDto imageDto) {
        log.info("Rewriting file as bytes in buffer");
        File file = new File(fileSearcher.getBufferPath(), new BufferedFileName(imageDto.getId(), imageDto.getName()).getFileName());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(ArraysC.toString(imageDto.getImage().getBytes()));
            return file;
        } catch (IOException e) {
            log.error("The file {} has been deleted: {}", file.getName(), file.delete());
            throw new SaveImageException("Image can't be saved as txt file." + e.getMessage());
        }
    }

    @Override
    public Optional<File> findFileById(Long id) {
        log.info("Finding file with id {}", id);
        return fileSearcher.getFileById(id);
    }

    @Override
    public Optional<File> findFileByName(String imageName) {
        log.info("Finding file with imageName {}", imageName);
        return fileSearcher.getFileByName(imageName);
    }

    @Override
    public byte[] getBytesByFile(File file) {
        log.info("Reading {} in buffer", file.getName());
        StringBuilder sb = new StringBuilder();
        if (file.exists()) {
            buildStringWithBytes(sb, file);
            return getByteArrayFromString(sb);
        }
        throw new ImageCantBeConvertedException("Image can't be converted from the txt file to byte[]");
    }

    private void buildStringWithBytes(StringBuilder sb, File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String result = reader.lines().collect(Collectors.joining());
            sb.append(result);
        } catch (IOException e) {
            log.error("Cant read file and convert to string with bytes. Name {}", file.getName());
        }
    }

    private byte[] getByteArrayFromString(StringBuilder sb) {
        return ArrayUtils.toPrimitive(java.util.Arrays.stream(sb.toString().split(CONVERTER_SPLIT_REGEX)).map(Byte::parseByte).toArray(Byte[]::new));
    }
}