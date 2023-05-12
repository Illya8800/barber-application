package com.barber.hopak.exception.handler;

import com.barber.hopak.exception.image.ImageException;
import com.barber.hopak.exception.image.ImageNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalImageExceptionHandler extends AbstractGlobalException<String, ImageException> {
    @Override
    @ExceptionHandler(value = ImageException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, ImageException e) {
        getRequestUrlAndMethod(req);
        return new ResponseEntity<>("Image can't be processed;" + getRequestUrlAndMethod(req), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(value = ImageNotFoundException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, ImageNotFoundException e) {
        getRequestUrlAndMethod(req);
        return new ResponseEntity<>("Image wasn't found;" + getRequestUrlAndMethod(req), HttpStatus.BAD_REQUEST);
    }
}
