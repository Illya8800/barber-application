package com.barber.hopak.util;

import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileFromDateBase;
import com.barber.hopak.web.domain.impl.ImageDto;

import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;
import static com.barber.hopak.util.buffer.BufferUtils.EXISTING_FILE;

public class ImageUtils {
    public static final Long IMAGE_DTO_ID = 1L;
    public static final byte[] IMAGE_DTO_BYTES = {0};
    public static final String IMAGE_DTO_NAME = EXISTING_FILE;

    public static ImageDto getImageDto() {
        return ImageDto.builder()
                .id(IMAGE_DTO_ID)
                .imageName(IMAGE_DTO_NAME)
                .image(new MultipartFileFromDateBase(IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();
    }

    public static String getBufferedFileName() {
        return StringUtils3C.join(IMAGE_DTO_ID, ID_SEPARATOR, IMAGE_DTO_NAME, DOT_TXT);
    }
}
