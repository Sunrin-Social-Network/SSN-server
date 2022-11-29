package io.twotle.ssn.service;

import io.twotle.ssn.component.CustomException;
import io.twotle.ssn.component.ExceptionCode;
import io.twotle.ssn.dto.EmailDTO;
import io.twotle.ssn.dto.LoginDTO;
import io.twotle.ssn.dto.RegisterDTO;
import io.twotle.ssn.entity.User;
import io.twotle.ssn.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User signUp(RegisterDTO registerDTO) throws CustomException{
        if(this.isEmailAvailable(registerDTO.getEmail()) && this.isUsernameAvailable(registerDTO.getUsername())) {
            throw new CustomException(ExceptionCode.ALREADY_REGISTERED);
        }
        User newUser = registerDTO.toUserEntity();
        newUser.hashPassword(passwordEncoder);
        return this.userRepository.save(newUser);
    }

    public User signIn(LoginDTO loginDTO) throws CustomException {
        //loginDTO.hashPassword(passwordEncoder);
        Optional<User> user = this.userRepository.findByEmail(loginDTO.getEmail());
        if(user.isEmpty() || !user.get().checkPassword(loginDTO.getPassword(), passwordEncoder)) throw new CustomException(ExceptionCode.USER_NOT_FOUND_ERROR);
        return user.get();
    }

    public boolean isEmailAvailable(String email) {
        Optional<User> byEmail = this.userRepository.findByEmail(email);
        return !byEmail.isEmpty();
    }

    public boolean isUsernameAvailable(String username) {
        Optional<User> byUsername = this.userRepository.findByUsername(username);
        return !byUsername.isEmpty();
    }






}
