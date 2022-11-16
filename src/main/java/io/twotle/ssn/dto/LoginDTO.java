package io.twotle.ssn.dto;

import io.twotle.ssn.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public void hashPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
