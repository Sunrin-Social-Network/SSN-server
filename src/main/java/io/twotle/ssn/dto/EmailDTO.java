package io.twotle.ssn.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@RequiredArgsConstructor
@JsonAutoDetect
public class EmailDTO {
    @NotEmpty(message = "email must not be null")
    @Pattern(regexp="^\\d{2}sunrin\\d{3}@sunrint.hs.kr$",message="email must be used Sunrin Internet High School Mail")
    private String email;
}
