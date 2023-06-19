package com.barber.hopak.exception.handler;

import com.barber.hopak.exception.BarberNotFoundException;
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
public class GlobalBarberNotFoundExceptionHandler extends AbstractGlobalException<String, BarberNotFoundException>{

    @Override
    @ExceptionHandler(BarberNotFoundException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, BarberNotFoundException e) {
        logErrorData(req, resp, e);
        return new ResponseEntity<>("Barber with this id doesn't exist", HttpStatus.NO_CONTENT);
    }
}
