package com.barber.hopak.service.buffer.impl;

import com.barber.hopak.exception.ImageCantBeConvertedException;
import com.barber.hopak.service.buffer.BufferManager;
import com.barber.hopak.service.buffer.BufferedFileName;
import com.barber.hopak.service.buffer.FileSearcher;
import com.barber.hopak.web.domain.impl.ImageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor
public class BufferManagerImpl implements BufferManager<File, Long> {
    private static final FileSearcher fileSearcher = new FileSearcherImpl();

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
        File file = new File(fileSearcher.getBufferPath(), new BufferedFileName(imageDto.getId(), imageDto.getImageName()).getFileName());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String s = Arrays.toString(imageDto.getImage().getBytes());
            writer.write(s.substring(1, s.length() - 1));
            return file;
        } catch (IOException e) {
            throw new ImageCantBeConvertedException("Image can't be saved as txt file. \n" + e.getMessage());
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
            byte[] byteArray = getByteArrayFromString(sb);
            generateImageFromByteArray(byteArray, file);
            return byteArray;
        }
        throw new ImageCantBeConvertedException("Image can't be converted from the txt file to byte[]");
    }

    private void generateImageFromByteArray(byte[] byteArray, File file) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(byteArray)) {
            BufferedImage image = ImageIO.read(bais);
            String imageName = new BufferedFileName(file).getImageName();
            ImageIO.write(image, imageName.substring(imageName.lastIndexOf('.') + 1), new File(imageName));
        } catch (IOException e) {
            log.error(" Converting byte array to image from file {} was unsuccessful\n{}", file.getName(), e.getMessage());
        }
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
        String SPLIT_REGEX = ", ";
        return ArrayUtils.toPrimitive(Arrays.stream(sb.toString().split(SPLIT_REGEX))
                .map(Byte::parseByte)
                .toArray(Byte[]::new));
    }
}
