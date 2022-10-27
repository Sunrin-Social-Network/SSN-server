package io.twotle.ssn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponseDTO {
    private boolean result;

    @Builder
    public ResultResponseDTO(boolean result) {
        this.result = result;
    }
}
