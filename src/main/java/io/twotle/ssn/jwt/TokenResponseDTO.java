package io.twotle.ssn.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponseDTO {
    private final String access;
    private final String refresh;
}
