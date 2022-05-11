package com.socialnetwork.project.exception;

import com.socialnetwork.project.dto.ErrorDTO;
import com.socialnetwork.project.util.ErrorCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorDTO handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ErrorDTO.builder()
                .message(ex.getMessage())
                .errorCode(ErrorCodeException.MISSING_ARGUMENT)
                .build();
    }

    @ExceptionHandler(ChatException.class)
    public ResponseEntity<Object> handleNotFoundError(ChatException ex) {
        if(ex.getErrorCodeException().equals(ErrorCodeException.UNAUTHORIZED)) {
            return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
        }
        if(ex.getErrorCodeException().equals(ErrorCodeException.FORBIDDEN)) {
            return new ResponseEntity<>("forbidden", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(ErrorDTO.builder()
                .message(ex.getMessage())
                .errorCode(ex.getErrorCodeException())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
