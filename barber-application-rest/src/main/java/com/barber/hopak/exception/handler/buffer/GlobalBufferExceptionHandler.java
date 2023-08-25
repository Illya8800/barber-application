package com.barber.hopak.exception.handler.buffer;

import com.barber.hopak.exception.buffer.BufferException;
import com.barber.hopak.exception.handler.AbstractGlobalException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalBufferExceptionHandler extends AbstractGlobalException<String, BufferException> {

    @Override
    @ExceptionHandler(value = BufferException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, BufferException e) {
        logErrorData(req, resp, e);
        return new ResponseEntity<>("Buffer threw an error" + getRequestUrlAndMethod(req), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
