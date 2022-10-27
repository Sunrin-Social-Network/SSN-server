package io.twotle.ssn.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, 1, "Already Registered"),
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 0, "Something Error");

    private final HttpStatus status;
    private final int code;
    private final String message;

    ExceptionCode(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
