package com.barber.hopak.exception.handler;

import com.barber.hopak.util.StringUtils3C;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;

@Log4j2
public abstract class AbstractGlobalException<R, E> {

    public abstract ResponseEntity<R> handleException(HttpServletRequest req, HttpServletResponse resp, E e);

    public static String getRequestUrlAndMethod(HttpServletRequest req) {
        return StringUtils3C.join("Request URL: ", req.getRequestURL(), ";\nMethod: ", req.getMethod());
    }
    public static void logErrorData(HttpServletRequest req, HttpServletResponse resp, Exception  e) {
        log.error("Request URL: {}", req.getRequestURL());
        log.error("Method: {}", req.getMethod());
        log.error("Exception class: {}", e.getClass());
        log.error("Message: {}", e.getMessage());
        log.info("Request parameters :");
        req.getParameterMap().forEach((key, value) -> log.info("Key: {}; Values: [{}]", key, value));
    }
}
