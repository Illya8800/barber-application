package com.barber.hopak.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalEmptyResultDataAccessExceptionHandler extends AbstractGlobalException<String, EmptyResultDataAccessException>{
    @Override
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, EmptyResultDataAccessException e) {
        logErrorData(req, resp, e);
        return new ResponseEntity<>("Entity with this id doesn't exist", HttpStatus.NO_CONTENT);
    }
}
