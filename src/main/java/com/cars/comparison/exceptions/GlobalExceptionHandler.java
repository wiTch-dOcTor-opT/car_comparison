package com.cars.comparison.exceptions;

import com.cars.comparison.aop.ControllerLoggingAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<String> handleDuplicateEntityException(DuplicateEntityException ex) {
        logger.error("DuplicateEntityException occurred in handleDuplicateEntityException method", ex);
        return new ResponseEntity<>("The entry already exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleServiceException(ServiceException ex) {
        logger.error("ServiceException occurred in handleServiceException method", ex);
        return new ResponseEntity<>("An error has occured. Please contact support.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

