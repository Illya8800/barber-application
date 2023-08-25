package com.barber.hopak.exception.handler;

import com.barber.hopak.exception.entity.image.ImageNotFoundException;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.util.ImageUtil;
import com.barber.hopak.web.domain.impl.ImageDto;
import jakarta.persistence.EntityNotFoundException;
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
public class GlobalEntityNotFoundExceptionHandler extends AbstractGlobalException<String, EntityNotFoundException> {
    private final ImageService<ImageDto, Long> imageService;

    @ExceptionHandler(value = ImageNotFoundException.class)
    public ResponseEntity<ImageDto> handleException(HttpServletRequest req, HttpServletResponse resp, ImageNotFoundException e) {
        log.warn("Handling ImageNotFoundException. It's gonna return " + ImageUtil.NO_IMAGE);
        return new ResponseEntity<>(imageService.findByName(ImageUtil.NO_IMAGE), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleException(HttpServletRequest req, HttpServletResponse resp, EntityNotFoundException e) {
        logErrorData(req, resp, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
