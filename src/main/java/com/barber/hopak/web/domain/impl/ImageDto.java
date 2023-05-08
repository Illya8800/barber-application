package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.FileSize;
import com.barber.hopak.constrain.ImageExtensionsName;
import com.barber.hopak.constrain.UniqueImageName;
import com.barber.hopak.converter.ImageConverter;
import com.barber.hopak.model.impl.Image;
import com.barber.hopak.web.domain.DTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.IMAGE_FILE_IS_NOT_SELECTED;

@Builder
@UniqueImageName(message = "cwwcwc")
@Data
public class ImageDto implements DTO<Image> {

    private Long id;
    private String name;
    @NotNull(message = IMAGE_FILE_IS_NOT_SELECTED)
    @ImageExtensionsName
    @FileSize(message = "dwdwd")
    private MultipartFile image;

    @Override
    public Image toEntity() {
        return new Image(
                id,
                name,
                ImageConverter.convertMultipartFileToBlob(image)
        );
    }
}
