package com.barber.hopak.exception.handler;

import com.barber.hopak.exception.image.ImageException;
import com.barber.hopak.exception.image.ImageNotFoundException;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.util.ImageUtil;
import com.barber.hopak.web.domain.impl.ImageDto;
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

    private final ImageService<ImageDto, Long> imageService;

    @ExceptionHandler(value = ImageNotFoundException.class)
    public ResponseEntity<ImageDto> handleException(HttpServletRequest req, HttpServletResponse resp, ImageNotFoundException e) {
        log.warn("Handling ImageNotFoundException. It's gonna return " + ImageUtil.NO_IMAGE);
        getRequestUrlAndMethod(req);
        return new ResponseEntity<>(imageService.findByName(ImageUtil.NO_IMAGE), HttpStatus.OK);
    }
    @Override
    @ExceptionHandler(value = ImageException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, ImageException e) {
        getRequestUrlAndMethod(req);
        return new ResponseEntity<>("Image can't be processed;" + getRequestUrlAndMethod(req), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
