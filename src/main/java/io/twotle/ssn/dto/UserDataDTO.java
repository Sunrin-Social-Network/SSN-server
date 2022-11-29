package io.twotle.ssn.dto;

import io.twotle.ssn.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataDTO {
    private String email;
    private String username;
    private String profileUrl;

    public void fromUser(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.profileUrl = user.getProfileUrl();
    }
}
