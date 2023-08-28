package com.barber.hopak.web.controller;

import com.barber.hopak.service.HaircutService;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.HaircutDto;
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

import java.util.List;

@RestController
@RequestMapping("/haircuts")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Haircut Management", description = "APIs for managing haircuts")
public class HaircutController {
    private final HaircutService<HaircutDto, Long> haircutService;
    private final ImageService<ImageDto, Long> imageService;

    @Operation(summary = "Get a haircut by id", description = "Returns a haircut as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The haircut was not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<HaircutDto> findHaircutById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findHaircutById\" mapping");
        return new ResponseEntity<>(haircutService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get a haircut by haircutName", description = "Returns a haircut as per the haircutName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The haircut was not found")
    })
    @GetMapping("/name/{haircutName}")
    public ResponseEntity<HaircutDto> findHaircutByName(@PathVariable String haircutName) {
        log.info("Controller processing the GET \"findHaircutByName\" mapping");
        return new ResponseEntity<>(haircutService.findByName(haircutName), HttpStatus.OK);
    }

    @Operation(summary = "Find all haircuts (paginated)", description = "Get a paginated list of all haircuts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the paginated list of haircuts"),
    })
    @GetMapping
    public ResponseEntity<List<HaircutDto>> findAllHaircuts() {
        log.info("Controller processing the GET \"findAllHaircuts\" mapping");
        return new ResponseEntity<>(haircutService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create an haircut", description = "Upload and create a new haircut.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Haircut created successfully"),
            @ApiResponse(responseCode = "422", description = "Probably illegal value(s) in request")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createHaircut(@ModelAttribute("haircutDto") @Valid HaircutDto haircutDto,
                                              @ModelAttribute("imageDto") @Valid ImageDto imageDto) {
        log.info("Controller processing the POST \"createHaircut\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        haircutDto.setAvatar(imageDto);
        haircutService.create(haircutDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update an haircut", description = "Update an existing haircut's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Haircut updated successfully."),
            @ApiResponse(responseCode = "422", description = "Bad request. Probably illegal value(s) in request")
    })
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateHaircut(@ModelAttribute("haircutDto") @Valid HaircutDto haircutDto,
                                              @ModelAttribute("imageDto") @Valid ImageDto imageDto) {
        log.info("Controller processing the PATCH \"updateHaircut\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        haircutDto.setAvatar(imageDto);
        haircutService.update(haircutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete an haircut", description = "Delete an existing haircut's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Haircut deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteHaircutById(@ModelAttribute("haircutDto") @Valid HaircutDto haircutDto) {
        log.info("Controller processing the DELETE \"deleteHaircutById\" mapping");
        haircutService.delete(haircutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Checks a haircut on unique", description = "Checks a haircut by name and id on unique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The checking result"),
    })
    @GetMapping("/unique")
    public ResponseEntity<Boolean> isUniqueHaircut(@RequestParam(name = "id") Long id, @RequestParam(name = "name") String name) {
        log.info("Controller processing the GET \"isUniqueHaircut\" mapping");
        return new ResponseEntity<>(haircutService.isUnique(id, name), HttpStatus.OK);
    }
}
