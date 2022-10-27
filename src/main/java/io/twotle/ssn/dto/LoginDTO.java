package io.twotle.ssn.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class LoginDTO {
    @NotEmpty(message = "email must not be null")
    @Pattern(regexp="^\\d{2}sunrin\\d{3}@sunrint.hs.kr$",message="email must be used Sunrin Internet High School Mail")
    private String email;
    @NotEmpty(message = "password must not be null")
    private String password;
}
