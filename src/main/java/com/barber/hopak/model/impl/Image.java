package com.barber.hopak.model.impl;

import com.barber.hopak.converter.ImageConverter;
import com.barber.hopak.web.domain.impl.ImageDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Entity
@Table(name = "image")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Image implements com.barber.hopak.model.Entity<ImageDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    @Lob
    @Column(nullable = false)
    private Blob image;

    @Override
    public ImageDto toDto() {
        return ImageDto.builder()
                .id(id)
                .name(name)
                .image(ImageConverter.convertBlobToMultipartFile(image, name))
                .build();
    }
}