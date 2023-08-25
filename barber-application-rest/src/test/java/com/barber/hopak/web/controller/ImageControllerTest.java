package com.barber.hopak.web.controller;

import com.barber.hopak.buffer.BufferState;
import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileWithoutPath;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.util.entity.ImageTestUtils;
import com.barber.hopak.web.domain.impl.ImageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.barber.hopak.constrain.DtoConstraintMessage.IMAGE_NAME_SHOULD_BE_UNIQUE;
import static com.barber.hopak.constrain.DtoConstraintMessage.IMAGE_ORIGINAL_FILE_NAME_SHOULD_BE_CORRECT;
import static com.barber.hopak.constrain.DtoConstraintMessage.IMAGE_TYPE_UNKNOWN;
import static com.barber.hopak.exception.handler.GlobalHandlerBodyMessagesContainer.GLOBAL_HANDLER_DELETE_UNEXISTING_ENTITY_EXCEPTION_TEXT;
import static com.barber.hopak.util.GlobalBindExceptionErrorMessagesVerifier.verifyExpectedErrorMessages;
import static com.barber.hopak.util.ImageUtil.NO_IMAGE;
import static com.barber.hopak.util.JsonPart.buildJsonString;
import static com.barber.hopak.util.entity.ImageTestUtils.EXISTING_IMAGE_DTO_NAME;
import static com.barber.hopak.util.entity.ImageTestUtils.IMAGE_DTO_BYTES;
import static com.barber.hopak.util.entity.ImageTestUtils.UNEXISTING_IMAGE_DTO_ID;
import static com.barber.hopak.util.entity.ImageTestUtils.UNEXISTING_IMAGE_DTO_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ImageControllerTest {
    private final ImageTestUtils imageTestUtils;
    private final BufferState bufferState;
    private final MockMvc mockMvc;
    private final ImageService<ImageDto, Long> imageService;

    @Autowired
    ImageControllerTest(ImageTestUtils imageTestUtils, BufferState bufferState, MockMvc mockMvc, ImageService<ImageDto, Long> imageService) {
        this.imageTestUtils = imageTestUtils;
        this.bufferState = bufferState;
        this.mockMvc = mockMvc;
        this.imageService = imageService;
    }

    @BeforeEach
    void clearDbState() {
        bufferState.init();
        List<ImageDto> allImages = imageService.findAll();
        allImages.forEach(image -> imageService.deleteById(image.getId()));
        ImageDto noImage = imageService.create(ImageDto.builder().name(NO_IMAGE).image(new MultipartFileWithoutPath(NO_IMAGE, IMAGE_DTO_BYTES)).build());
        imageTestUtils.setNoImageId(noImage.getId());
    }

    @AfterEach
    void destroyBuffer() {
        bufferState.destroy();
    }

    @Test
    void findImageById_thenFindImage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/images/{id}", imageTestUtils.getNoImageId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ImageDto imageByService = imageService.findById(imageTestUtils.getNoImageId());
        assertThat(json).contains(buildJsonString("id", imageByService.getId()));
        assertThat(json).contains(buildJsonString("name", imageByService.getName()));
    }

    @Test
    void findImageById_thenCaughtByGlobalImageExceptionHandler() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/images/{id}", UNEXISTING_IMAGE_DTO_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();

        assertThat(json).contains(buildJsonString("name", NO_IMAGE));
    }

    @Test
    void findImageByImageName_thenFindImage() throws Exception {
        ImageDto imageByService = imageService.findByName(NO_IMAGE);

        MvcResult mvcResult = mockMvc.perform(get("/images/name/{imageName}", NO_IMAGE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        assertThat(json).contains(buildJsonString("id", imageByService.getId()));
        assertThat(json).contains(buildJsonString("name", imageByService.getName()));
    }

    @Test
    void findImageByImageName_thenCaughtByGlobalImageExceptionHandler() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/images/name/{imageName}", UNEXISTING_IMAGE_DTO_NAME)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();

        assertThat(json).contains(buildJsonString("name", NO_IMAGE));
    }

    @Test
    void findAllImages_thenFindThreeImages() throws Exception {
        ImageDto imageDto1 = ImageDto.builder().id(2L).name("FirstImage").image(new MultipartFileWithoutPath(EXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();
        ImageDto imageDto2 = ImageDto.builder().id(3L).name("SecondImage").image(new MultipartFileWithoutPath(EXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();
        ImageDto imageDto3 = ImageDto.builder().id(4L).name("ThirdImage").image(new MultipartFileWithoutPath(EXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();
        imageService.create(imageDto1);
        imageService.create(imageDto2);
        imageService.create(imageDto3);

        MvcResult mvcResult = mockMvc.perform(get("/images")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        assertThat(new ObjectMapper().readValue(json, List.class)).hasSize(4);
        assertThat(json).contains(buildJsonString("name", NO_IMAGE));
        assertThat(json).contains(buildJsonString("name", imageDto1.getName()));
        assertThat(json).contains(buildJsonString("name", imageDto2.getName()));
        assertThat(json).contains(buildJsonString("name", imageDto3.getName()));
    }

    @Test
    void findAllImages_thenOnlyNoImage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/images")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        assertThat(new ObjectMapper().readValue(json, List.class)).hasSize(1);
        assertThat(json).contains(buildJsonString("name", NO_IMAGE));
    }

    @Test
    void createImage_thenCreate() throws Exception {
        ImageDto imageDto = imageTestUtils.getImageDto();
        imageDto.setId(3L);
        MockMultipartFile multipartFile = new MockMultipartFile(
                imageDto.getName(),
                imageDto.getName(),
                "image/png",
                imageDto.getImage().getBytes()
        );

        imageDto.setImage(multipartFile);

        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.POST, "/images")
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .flashAttr("imageDto", imageDto))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void createImage_thenThrowBindException() throws Exception {
        ImageDto imageDto = ImageDto.builder().name(UNEXISTING_IMAGE_DTO_NAME).image(new MultipartFileWithoutPath(UNEXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();

        MockMultipartFile multipartFile = new MockMultipartFile(
                imageDto.getName(),
                imageDto.getName(),
                "image/png",
                imageDto.getImage().getBytes()
        );

        imageDto.setImage(multipartFile);

        MvcResult mvcResult = mockMvc.perform(multipart(HttpMethod.POST, "/images")
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .flashAttr("imageDto", imageDto))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        verifyExpectedErrorMessages(mvcResult, "image", IMAGE_ORIGINAL_FILE_NAME_SHOULD_BE_CORRECT, IMAGE_TYPE_UNKNOWN);
    }

    @Test
    void createImage_thenThrowBindExceptionByDuplicateImageName() throws Exception {
        ImageDto imageDto = ImageDto.builder().name(EXISTING_IMAGE_DTO_NAME).image(new MultipartFileWithoutPath(EXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();
        imageService.create(imageDto);
        MockMultipartFile multipartFile = new MockMultipartFile(
                imageDto.getName(),
                imageDto.getName(),
                "image/png",
                imageDto.getImage().getBytes()
        );

        imageDto.setImage(multipartFile);

        MvcResult mvcResult = mockMvc.perform(multipart(HttpMethod.POST, "/images")
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .flashAttr("imageDto", imageDto))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andReturn();
        verifyExpectedErrorMessages(mvcResult, "imageDto", IMAGE_NAME_SHOULD_BE_UNIQUE);
    }

    @Test
    void updateImage_thenUpdate() throws Exception {
        final ImageDto imageDto1 = imageService.create(imageTestUtils.getImageDto());

        MockMultipartFile multipartFile = new MockMultipartFile(
                imageDto1.getName(),
                imageDto1.getName(),
                "image/png",
                new byte[]{0, 0, 0}
        );
        imageDto1.setImage(multipartFile);

        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.PATCH, "/images")
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .flashAttr("imageDto", imageDto1))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void updateImage_thenThrowBindExceptionByIllegalOriginalFileNameAndExtension() throws Exception {
        ImageDto imageDto = ImageDto.builder().name(UNEXISTING_IMAGE_DTO_NAME).image(new MultipartFileWithoutPath(UNEXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();

        MockMultipartFile multipartFile = new MockMultipartFile(
                imageDto.getName(),
                imageDto.getName(),
                "image/png",
                imageDto.getImage().getBytes()
        );

        imageDto.setImage(multipartFile);

        MvcResult mvcResult = mockMvc.perform(multipart(HttpMethod.PATCH, "/images")
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .flashAttr("imageDto", imageDto))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        verifyExpectedErrorMessages(mvcResult, "image", IMAGE_ORIGINAL_FILE_NAME_SHOULD_BE_CORRECT, IMAGE_TYPE_UNKNOWN);
    }

    @Test
    void deleteImage_thenDelete() throws Exception {
        mockMvc.perform(delete("/images/{id}", imageTestUtils.getNoImageId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteImage_thenCaughtByEmptyResultDataAccessException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/images/{id}", UNEXISTING_IMAGE_DTO_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString).isEqualTo(GLOBAL_HANDLER_DELETE_UNEXISTING_ENTITY_EXCEPTION_TEXT);
    }

    @Test
    void isUniqueImage_thenTrueAsNewEntity() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/images/unique?id=" + UNEXISTING_IMAGE_DTO_ID + "&name=" + UNEXISTING_IMAGE_DTO_NAME)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertThat(contentAsString).isEqualTo("false");
    }

    @Test
    void isUniqueImage_thenTrueAsUpdate() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/images/unique?id=" + imageTestUtils.getNoImageId() + "&name=" + NO_IMAGE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertThat(contentAsString).isEqualTo("true");
    }

    @Test
    void isUniqueImage_thenFalse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/images/unique?id=" + UNEXISTING_IMAGE_DTO_ID + "&name=" + NO_IMAGE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertThat(contentAsString).isEqualTo("false");
    }
}