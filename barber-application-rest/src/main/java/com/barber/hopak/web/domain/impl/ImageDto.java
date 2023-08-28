package com.barber.hopak.web.domain.impl;

import com.barber.hopak.constrain.image.FileSize;
import com.barber.hopak.constrain.image.ImageExtensionsName;
import com.barber.hopak.constrain.image.OriginalFileNameNotBlank;
import com.barber.hopak.constrain.image.UniqueImageName;
import com.barber.hopak.converter.ImageConverter;
import com.barber.hopak.model.impl.Image;
import com.barber.hopak.web.domain.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import static com.barber.hopak.constrain.DtoConstraintMessage.IMAGE_FILE_IS_NOT_SELECTED;
import static com.barber.hopak.constrain.DtoConstraintMessage.IMAGE_SHOULD_NOT_BE_NULL;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@UniqueImageName
@NotNull(message = IMAGE_SHOULD_NOT_BE_NULL)
public class ImageDto implements DTO<Image> {
    @Schema(description = "Image ID", example = "2")
    private Long id;
    @Schema(description = "Image name", example = "NickBarber")
    private String name;
    @Schema(description = "Image", example = "An image", format = "binary")
    @NotNull(message = IMAGE_FILE_IS_NOT_SELECTED)
    @OriginalFileNameNotBlank
    @ImageExtensionsName
    @FileSize
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



