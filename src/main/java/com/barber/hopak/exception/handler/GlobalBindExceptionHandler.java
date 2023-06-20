package com.barber.hopak.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Log4j2
public class GlobalBindExceptionHandler extends AbstractGlobalException<Map<String, List<String>>, BindException> {

    @Override
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, List<String>>> handleException(HttpServletRequest req, HttpServletResponse resp, BindException e) {
        log(req, resp, e);
        Map<String, List<String>> errorFieldsAndMessages = getErrorFieldsAndMessages(e.getBindingResult());
        log.error(errorFieldsAndMessages);
        return new ResponseEntity<>(errorFieldsAndMessages, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private static void log(HttpServletRequest req, HttpServletResponse resp, BindException e) {
        logErrorData(req, resp, e);
        log.error("Object: {}", Objects.requireNonNull(e.getBindingResult().getTarget()).toString());
    }

/*    private Map<String, List<String>> getErrorFieldsAndMessages(BindingResult bindingResult) {
        HashMap<String, List<String>> fieldErrors = new HashMap<>();
        List<String> errorMessage = new ArrayList<>();
        String currErrField = "";

        for (ObjectError errField : bindingResult.getAllErrors()) {

            if (currErrField.equals(errField.getObjectName())) {
                errorMessage.add(errField.getDefaultMessage());
                fieldErrors.put(currErrField.substring(currErrField.lastIndexOf('.') + 1), errorMessage);
            } else {
                errorMessage = new ArrayList<>(3);
                errorMessage.add(errField.getDefaultMessage());
                currErrField = errField.getObjectName();
                fieldErrors.put(currErrField.substring(currErrField.lastIndexOf('.') + 1), errorMessage);
            }
        }


        return fieldErrors;
    }*/

    private Map<String, List<String>> getErrorFieldsAndMessages(BindingResult bindingResult) {
        Map<String, List<String>> fieldErrors = new HashMap<>();

        for (ObjectError error : bindingResult.getAllErrors()) {
            String fieldName = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            String errorMessage = error.getDefaultMessage();

            fieldErrors.computeIfAbsent(fieldName, key -> new ArrayList<>()).add(errorMessage);
        }

        return fieldErrors;
    }
}

