package com.barber.hopak.web.controller;

import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.ImageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Image Management", description = "APIs for managing images")
public class ImageController {
    private final ImageService<ImageDto, Long> imageService;

    @Operation(summary = "Get a image by id", description = "Returns an image as per the id, or else return no_image.png (id=1)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The image was not found. Returned no_image")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ImageDto> findImageById(
            @PathVariable @Parameter(name = "id", description = "An image id", example = "1") Long id) {
        log.info("Controller processing the GET \"findImageById\" mapping");
        return new ResponseEntity<>(imageService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get a image by imageName", description = "Returns an image as per the imageName, or else return no_image.png (id=1)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The image was not found. Returned no_image")
    })
    @GetMapping("/name/{imageName}")
    public ResponseEntity<ImageDto> findImageByImageName(
            @PathVariable @Parameter(name = "imageName", description = "Image name", example = "any_image.png") String imageName) {
        log.info("Controller processing the GET \"findImageByImageName\" mapping");
        return new ResponseEntity<>(imageService.findByName(imageName), HttpStatus.OK);
    }

    @Operation(summary = "Find all images (paginated)", description = "Get a paginated list of all images")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the paginated list of images"),
    })
    @GetMapping
    public ResponseEntity<List<ImageDto>> findAllImages() {
        log.info("Controller processing the GET \"findAllImages\" mapping");
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create an image", description = "Upload and create a new image.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Image created successfully"),
            @ApiResponse(responseCode = "422", description = "Probably illegal value(s) in request")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createImage(@Valid @ModelAttribute ImageDto imageDto) {
        log.info("Controller processing the POST \"createImage\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        imageService.create(imageDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update an image", description = "Update an existing image's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Image updated successfully."),
            @ApiResponse(responseCode = "422", description = "Bad request. Probably illegal value(s) in request")
    })
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateImage(
            @Valid @ModelAttribute ImageDto imageDto
    ) {
        log.info("Controller processing the PATCH \"updateImage\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        imageService.update(imageDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete an image", description = "Delete an existing image's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Image deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(
            @PathVariable @Parameter(name = "id", description = "An image id", example = "1") Long id) {
        log.info("Controller processing the DELETE \"deleteImage\" mapping");
        imageService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Checks an image on unique", description = "Checks an image by name and id on unique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The checking result"),
    })
    @GetMapping("/unique")
    public ResponseEntity<Boolean> isUniqueImage(
            @RequestParam(name = "id") @Parameter(name = "id", description = "An image id", example = "1") Long id,
            @RequestParam(name = "name") @Parameter(name = "name", description = "An image name", example = "any_image.png") String name) {
        log.info("Controller processing the GET \"isUniqueImage\" mapping");
        return new ResponseEntity<>(imageService.isUnique(id, name), HttpStatus.OK);
    }
}