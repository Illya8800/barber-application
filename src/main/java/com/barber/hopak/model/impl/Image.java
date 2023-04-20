package com.barber.hopak.model.impl;

import com.barber.hopak.converter.ImageConverter;
import com.barber.hopak.web.domain.impl.ImageDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private String imageName;
    @Lob
    @Column(nullable = false)
    private Blob image;

    @Override
    public ImageDto toDto() {
        return ImageDto.builder()
                .id(id)
                .imageName(imageName)
                .image(ImageConverter.convertBlobToMultipartFile(image, imageName))
                .build();
    }
}