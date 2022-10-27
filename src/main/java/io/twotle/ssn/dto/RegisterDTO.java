package io.twotle.ssn.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.twotle.ssn.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@RequiredArgsConstructor
@JsonAutoDetect
public class RegisterDTO {
    @NotEmpty(message = "email must not be null")
    @Pattern(regexp="^\\d{2}sunrin\\d{3}@sunrint.hs.kr$",message="email must be used Sunrin Internet High School Mail")
    private String email;
    @NotEmpty(message = "password must not be null")
    private String password;
    @NotEmpty(message = "username must not be null")
    @Size(min = 3, max = 20, message = "username size is min 3, max 20")
    private String username;

    public RegisterDTO(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User toUserEntity() {
        return new User(email, password,username);
    }
}
