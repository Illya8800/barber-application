package com.barber.hopak.converter;

import com.barber.hopak.exception.ImageCantBeConvertedException;
import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileFromDateBase;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class ImageConverter {
    private static final String IMAGE_MEDIA_TYPE = "image/";

    public static Blob convertMultipartFileToBlob(MultipartFile file) {
        log.info("Converting MultipartFile to the Blob type");
        try (InputStream inputStream = file.getInputStream();
             ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] data = arrayOutputStream.toByteArray();
            return new SerialBlob(data);
        } catch (IOException | SQLException e) {
            throw new ImageCantBeConvertedException("Can't convert the MultipartFile file to the Blob");
        }
    }
    public static MultipartFile convertBlobToMultipartFile(Blob blob, String fileName) {
        log.info("Converting Blob type to the MultipartFile");
        try {
            return new MultipartFileFromDateBase(fileName,
                    StringUtils.join(List.of(IMAGE_MEDIA_TYPE, fileName.substring(fileName.lastIndexOf('.') + 1)) , null),
                    blob.getBytes(1, (int) blob.length())
            );
        } catch (SQLException e) {
            throw new ImageCantBeConvertedException("Can't convert the Blob file to the MultipartFileFromDateBase");
        }
    }
}
