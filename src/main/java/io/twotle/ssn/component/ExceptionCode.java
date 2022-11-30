package io.twotle.ssn.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, 1, "Already Registered"),
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 0, "Something Error"),
    USER_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, 2, "User not found"),

    NO_FILE_ERROR(HttpStatus.BAD_REQUEST,3,"No File found"),
    NO_MEAL_DATA(HttpStatus.INTERNAL_SERVER_ERROR, 4, "NO Meal Data");

    private final HttpStatus status;
    private final int code;
    private final String message;

    ExceptionCode(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
