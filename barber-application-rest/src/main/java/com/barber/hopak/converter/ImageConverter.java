package com.barber.hopak.converter;

import com.barber.hopak.exception.entity.image.inh.ImageConversionException;
import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileWithoutPath;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Log4j2
public class ImageConverter {

    public static Blob convertMultipartFileToBlob(MultipartFile file) {
        log.info("Converting MultipartFile to the Blob type");
        try (InputStream inputStream = file.getInputStream();
            @Cleanup ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] data = arrayOutputStream.toByteArray();
            return new SerialBlob(data);
        } catch (IOException | SQLException e) {
            throw new ImageConversionException("Can't convert the MultipartFile file to the Blob");
        }
    }

    public static MultipartFile convertBlobToMultipartFile(Blob blob, String fileName) {
        log.info("Converting Blob type to the MultipartFile");
        try {
            return new MultipartFileWithoutPath(fileName,
                    blob.getBytes(1, (int) blob.length())
            );
        } catch (SQLException e) {
            throw new ImageConversionException("Can't convert the Blob file to the MultipartFileFromDateBase");
        }
    }
}
