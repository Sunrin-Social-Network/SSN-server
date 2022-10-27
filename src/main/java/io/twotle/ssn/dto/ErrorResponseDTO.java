package io.twotle.ssn.dto;

import io.swagger.annotations.SwaggerDefinition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponseDTO {
    private final int code;
    private final String message;
}
