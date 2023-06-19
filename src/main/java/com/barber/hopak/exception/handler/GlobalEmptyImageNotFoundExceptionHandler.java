package com.barber.hopak.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class GlobalEmptyImageNotFoundExceptionHandler extends AbstractGlobalException<String, EmptyResultDataAccessException>{

    @Override
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, EmptyResultDataAccessException e) {
        logErrorData(req, resp, e);
        return new ResponseEntity<>("Entity with this id doesn't exist", HttpStatus.NO_CONTENT);
    }
}
