package com.barber.hopak.model;

import com.barber.hopak.dto.ImageDto;
import com.barber.hopak.model.enumeration.ImageExtensions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Entity
@Table(name = "image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String imageName;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('PNG','JPG')", nullable = false)
    private ImageExtensions fileExtensions;
    @Lob
    @Column(nullable = false)
    private Blob image;

    public ImageDto toDto() {
        return ImageDto.builder()
                .id(this.id)
                .imageName(this.imageName)
                .fileExtensions(this.fileExtensions)
                .image(this.image)
                .build();
    }
}