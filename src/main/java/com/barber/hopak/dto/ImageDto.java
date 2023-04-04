package com.barber.hopak.dto;

import com.barber.hopak.constrain.FileSize;
import com.barber.hopak.constrain.ImageExtensionsName;
import com.barber.hopak.constrain.UniqueImageName;
import com.barber.hopak.model.Image;
import com.barber.hopak.model.enumeration.ImageExtensions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.sql.Blob;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.IMAGE_FILE_IS_NOT_SELECTED;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.IMAGE_NAME_SHOULD_NOT_BE_BLANK;
import static com.barber.hopak.constrain.message.DtoConstraintMessage.IMAGE_TYPE_UNKNOWN;

@Builder
public class ImageDto {

    private Long id;
    @NotBlank(message = IMAGE_NAME_SHOULD_NOT_BE_BLANK)
    @Length(min = 1, max = 50, message = "")
    @UniqueImageName
    private String imageName;
    @NotBlank(message = IMAGE_TYPE_UNKNOWN)
    @ImageExtensionsName
    private ImageExtensions fileExtensions;
    @NotNull(message = IMAGE_FILE_IS_NOT_SELECTED)
    @FileSize(message = "dwdwd")
    private Blob image;

    public Image toEntity() {
        return new Image(
                this.id,
                this.imageName,
                this.fileExtensions,
                this.image
        );
    }
}
