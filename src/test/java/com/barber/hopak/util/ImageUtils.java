package com.barber.hopak.util;

import com.barber.hopak.model.impl.Image;
import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileFromDateBase;
import com.barber.hopak.web.domain.impl.ImageDto;

import java.util.List;

import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;
import static com.barber.hopak.util.buffer.BufferUtils.EXISTING_FILE_ID;
import static com.barber.hopak.util.buffer.BufferUtils.EXISTING_FILE_NAME;
import static com.barber.hopak.util.buffer.BufferUtils.UNEXISTING_FILE_ID;
import static com.barber.hopak.util.buffer.BufferUtils.UNEXISTING_FILE_NAME;

public class ImageUtils {
    public static final Long EXISTING_IMAGE_DTO_ID = EXISTING_FILE_ID;

    private static Long NO_IMAGE_ID = 1L;
    public static final Long UNEXISTING_IMAGE_DTO_ID = UNEXISTING_FILE_ID;
    public static final byte[] IMAGE_DTO_BYTES = {0, 1, 2, 3};
    public static final String EXISTING_IMAGE_DTO_NAME = EXISTING_FILE_NAME;
    public static final String UNEXISTING_IMAGE_DTO_NAME = UNEXISTING_FILE_NAME;

    public static ImageDto getImageDto() {
        return ImageDto.builder()
                .id(EXISTING_IMAGE_DTO_ID)
                .name(EXISTING_IMAGE_DTO_NAME)
                .image(new MultipartFileFromDateBase(EXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();
    }
    public static Long getNoImageId() {
        return NO_IMAGE_ID;
    }

    public static void setNoImageId(Long noImageId) {
        NO_IMAGE_ID = noImageId;
    }

    public static List<Image> getImageList() {
        String imageName2 = "Second ImageName";
        String imageName3 = "Third ImageName";
        String imageName4 = "Forth ImageName";
        ImageDto image = ImageDto.builder()
                .id(2L)
                .name(imageName2)
                .image(new MultipartFileFromDateBase(imageName2, new byte[] {2, 3, 4, 5})).build();

        ImageDto image1 = ImageDto.builder()
                .id(3L)
                .name(imageName3)
                .image(new MultipartFileFromDateBase(imageName3, new byte[] {3, 4, 5, 6})).build();

        ImageDto image2 = ImageDto.builder()
                .id(4L)
                .name(imageName4)
                .image(new MultipartFileFromDateBase(imageName4, new byte[] {4, 5, 6, 7})).build();


        return List.of(image.toEntity(), image1.toEntity(), image2.toEntity());
    }

    public static String getBufferedFileName() {
        return StringUtils3C.join(EXISTING_IMAGE_DTO_ID, ID_SEPARATOR, EXISTING_IMAGE_DTO_NAME, DOT_TXT);
    }
}
