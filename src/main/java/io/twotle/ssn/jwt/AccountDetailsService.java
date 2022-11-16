package io.twotle.ssn.jwt;

import io.twotle.ssn.entity.User;
import io.twotle.ssn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found User"));
        return new AccountDetails(user);
    }
}