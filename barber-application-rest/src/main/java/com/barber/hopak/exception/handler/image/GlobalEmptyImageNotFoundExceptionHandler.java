package com.barber.hopak.exception.handler.image;

import com.barber.hopak.exception.handler.AbstractGlobalException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.barber.hopak.exception.handler.GlobalHandlerBodyMessagesContainer.GLOBAL_HANDLER_DELETE_UNEXISTING_ENTITY_EXCEPTION_TEXT;

@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class GlobalEmptyImageNotFoundExceptionHandler extends AbstractGlobalException<String, EmptyResultDataAccessException> {
/**deleteById(UNEXISTING_ID)*/
    @Override
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, EmptyResultDataAccessException e) {
        logErrorData(req, resp, e);
        return new ResponseEntity<>(GLOBAL_HANDLER_DELETE_UNEXISTING_ENTITY_EXCEPTION_TEXT, HttpStatus.BAD_REQUEST);
    }
}
