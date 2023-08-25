package com.barber.hopak.exception.handler.image;

import com.barber.hopak.exception.entity.image.ImageException;
import com.barber.hopak.exception.handler.AbstractGlobalException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class GlobalImageExceptionHandler extends AbstractGlobalException<String, ImageException> {
    @Override
    @ExceptionHandler(value = ImageException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, ImageException e) {
        return new ResponseEntity<>("Image can't be processed;" + getRequestUrlAndMethod(req), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
