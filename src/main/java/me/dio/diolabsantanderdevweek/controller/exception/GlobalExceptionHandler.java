package me.dio.diolabsantanderdevweek.controller.exception;

import me.dio.diolabsantanderdevweek.service.exception.BusinessException;
import me.dio.diolabsantanderdevweek.service.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNoContentException(final NotFoundException e) {
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>("Resource ID Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleNoContentException(final BusinessException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnexpectedException(final Throwable ex) {
        String message = "Unexpected server error.";
        LOGGER.error(message, ex);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
