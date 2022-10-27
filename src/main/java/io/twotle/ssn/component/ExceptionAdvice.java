package io.twotle.ssn.component;

import io.twotle.ssn.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorResponseDTO> customExceptionHandler(CustomException e) {
        return ResponseEntity.status(e.getErrorcode().getStatus()).body(
                new ErrorResponseDTO(e.getErrorcode().getCode(), e.getErrorcode().getMessage())
        );
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDTO> validationExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponseDTO(1001, "Validation Failed")
        );
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseDTO> defaultErrorHandler(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDTO( 1000, e.getMessage())
        );
    }
}
