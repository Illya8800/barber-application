package com.barber.hopak.pojo;

import com.barber.hopak.web.domain.impl.ImageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Log4j2
public class ImagePojo {
    private Long id;
    private String imageName;
    private MultipartFile image;

    public ImageDto toDto() {
        return ImageDto.builder()
                .id(id)
                .imageName("")
                .image(/*convertToBlobFromMultipartFile(image)*/null)
                .build();
    }
}