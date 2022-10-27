package io.twotle.ssn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExistResponseDTO {
    private boolean exist;

    @Builder
    public ExistResponseDTO(boolean result) {
        this.exist = result;
    }
}