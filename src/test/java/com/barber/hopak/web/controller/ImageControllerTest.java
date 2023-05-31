package com.barber.hopak.web.controller;

import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileFromDateBase;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.util.ImageUtil;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.util.buffer.BufferUtils;
import com.barber.hopak.web.domain.impl.ImageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static com.barber.hopak.exception.handler.AbstractGlobalException.getRequestUrlAndMethod;
import static com.barber.hopak.util.ImageUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ImageService<ImageDto, Long> imageService;

    @AfterAll
    static void destroyBuffer() {
        BufferUtils.destroyBuffer();
    }

    @BeforeEach
    void clearDbState() {
        List<ImageDto> allImages = imageService.findAllImages();
        allImages.forEach(image -> imageService.deleteById(image.getId()));
        ImageDto noImage = imageService.create(ImageDto.builder().name("no_image.png").image(new MultipartFileFromDateBase("no_image.png", IMAGE_DTO_BYTES)).build());
        setNoImageId(noImage.getId());
    }

    @Test
    void findImageById_thenFindImage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/images/{id}", getNoImageId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ImageDto imageByService = imageService.findById(getNoImageId());
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

        assertThat(json).isEqualTo("Image wasn't found;" + getRequestUrlAndMethod(mvcResult.getRequest()));
    }

    private String buildJsonString(String field, Long value) {
        return StringUtils3C.join("\"", field, "\":", value.toString(), ",");
    }

    private String buildJsonString(String field, String value) {
        return StringUtils3C.join("\"", field, "\":\"", value, "\",");
    }

    @Test
    void findImageByImageName_thenFindImage() throws Exception {
        ImageDto imageByService = imageService.findByName(ImageUtil.NO_IMAGE);

        MvcResult mvcResult = mockMvc.perform(get("/images/name/{imageName}", ImageUtil.NO_IMAGE)
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

        assertThat(json).isEqualTo("Image wasn't found;" + getRequestUrlAndMethod(mvcResult.getRequest()));
    }

    @Test
    void findAllImages_thenFindThreeImages() throws Exception {
        ImageDto imageDto1 = ImageDto.builder().id(2L).name("FirstImage").image(new MultipartFileFromDateBase(EXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();
        ImageDto imageDto2 = ImageDto.builder().id(3L).name("SecondImage").image(new MultipartFileFromDateBase(EXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();
        ImageDto imageDto3 = ImageDto.builder().id(4L).name("ThirdImage").image(new MultipartFileFromDateBase(EXISTING_IMAGE_DTO_NAME, IMAGE_DTO_BYTES)).build();
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
        assertThat(json).contains(buildJsonString("name", "no_image.png"));
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
        assertThat(json).contains(buildJsonString("name", "no_image.png"));
    }

/*    @Test
    void createImage() {
    }

    @Test
    void updateImage() {
    }

    @Test
    void deleteImage() {
    }

    @Test
    void isUniqueImage() {
    }*/
}