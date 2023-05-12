package com.barber.hopak.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
@Log4j2
public class GlobalBindExceptionHandler extends AbstractGlobalException<String, BindException> {

    @Override
    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, BindException e) {
        log(req, resp, e);
        return new ResponseEntity<>("You sent an invalid object's parameters;\n" + getRequestUrlAndMethod(req), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private static void log(HttpServletRequest req, HttpServletResponse resp, BindException e) {
        logErrorData(req, resp, e);
        log.error("Object: {}", Objects.requireNonNull(e.getBindingResult().getTarget()).toString());
    }
}
