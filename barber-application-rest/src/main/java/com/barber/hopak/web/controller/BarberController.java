package com.barber.hopak.web.controller;

import com.barber.hopak.service.BarberService;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.BarberDto;
import com.barber.hopak.web.domain.impl.ImageDto;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/barbers")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Barber Management", description = "APIs for managing barbers")
public class BarberController {
    private final BarberService<BarberDto, Long> barberService;
    private final ImageService<ImageDto, Long> imageService;

    @Operation(summary = "Get a barber by id", description = "Returns a barber as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The barber was not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<BarberDto> findBarberById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findBarberById\" mapping");
        return new ResponseEntity<>(barberService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Find all barbers (paginated)", description = "Get a paginated list of all barbers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the paginated list of barbers"),
    })
    @GetMapping()
    public ResponseEntity<List<BarberDto>> findAllBarbers() {
        log.info("Controller processing the GET \"findAllBarbers\" mapping");
        return new ResponseEntity<>(barberService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create an barber", description = "Upload and create a new barber.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Barber created successfully"),
            @ApiResponse(responseCode = "422", description = "Probably illegal value(s) in request")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createBarber(@ModelAttribute("barberDto") @Valid BarberDto barberDto,
                                             @RequestParam("image") MultipartFile image/*,
                                             @ModelAttribute("imageDto") @Valid ImageDto imageDto*/) {
        log.info("Controller processing the POST \"createBarber\" mapping");
//        imageService.setImageNameByOriginalFileName(imageDto);
//        barberDto.setAvatar(imageDto);
        barberService.create(barberDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update an barber", description = "Update an existing barber's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Barber updated successfully."),
            @ApiResponse(responseCode = "422", description = "Bad request. Probably illegal value(s) in request")
    })
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateBarber(@ModelAttribute("barberDto") @Valid BarberDto barberDto,
                                             @ModelAttribute("imageDto") @Valid ImageDto imageDto) {
        log.info("Controller processing the PATCH \"updateBarber\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        barberDto.setAvatar(imageDto);
        barberService.update(barberDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete an barber", description = "Delete an existing barber's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Barber deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarberById(@PathVariable("id") Long id) {
        log.info("Controller processing the DELETE \"deleteBarberById\" mapping");
        barberService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}