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
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Objects;

@Entity
@Table(name = "image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        if (!Objects.equals(id, image1.id)) return false;
        if (!Objects.equals(name, image1.name)) return false;
        return Objects.equals(image, image1.image);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}