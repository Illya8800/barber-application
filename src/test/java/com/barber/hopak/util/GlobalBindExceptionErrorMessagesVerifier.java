package com.barber.hopak.util;

import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

public class GlobalBindExceptionErrorMessagesVerifier {
    public static void verifyExpectedErrorMessages(MvcResult mvcResult, String fieldName, String... expectedErrorMessages) {
        String contentAsString = getContentAsString(mvcResult);
        for (String errorMessage : expectedErrorMessages) {
            assertThat(contentAsString).contains(errorMessage);
            contentAsString = contentAsString.replace(errorMessage, "");
        }
        assertThat(contentAsString.replace(fieldName, "").matches("^[^A-Za-z]*$")).isTrue();
    }

    private static String getContentAsString(MvcResult mvcResult) {
        try {
            return mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
