package io.twotle.ssn.jwt;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class AccountDetails extends User {

    private final io.twotle.ssn.entity.User user;

    public AccountDetails(io.twotle.ssn.entity.User user) {
        super(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
        this.user = user;
    }
}
