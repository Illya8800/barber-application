package com.barber.hopak.converter;

import lombok.extern.log4j.Log4j2;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@Log4j2
public class ImageConverter {
    public static Optional<Blob> convertMultipartFileToBlob(MultipartFile file) {
        log.info("Converting MultipartFile to the Blob type");
        try (InputStream inputStream = file.getInputStream();
             ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] data = arrayOutputStream.toByteArray();
            return Optional.of(new SerialBlob(data));
        } catch (IOException | SQLException e) {
            log.error("Can't convert file to the Blob");
            return Optional.empty();
        }
    }

    public static Optional<MultipartFile> convertBlobToMultipartFile(Blob blob, String fileName) {
        log.info("Converting Blob type to the MultipartFile");
        try {
            MultipartFile multipartFile = new MockMultipartFile(fileName,
                    blob.getBinaryStream().readAllBytes());
            return Optional.of(multipartFile);
        } catch (SQLException | IOException e) {
            log.error("Can't convert file to the MultipartFile");
            return Optional.empty();
        }
    }
}
