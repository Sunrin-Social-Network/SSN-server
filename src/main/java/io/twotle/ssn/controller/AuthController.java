package io.twotle.ssn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.twotle.ssn.component.CustomException;
import io.twotle.ssn.jwt.AccountDetails;
import io.twotle.ssn.jwt.JwtProvider;
import io.twotle.ssn.dto.*;
import io.twotle.ssn.entity.User;
import io.twotle.ssn.jwt.TokenResponseDTO;
import io.twotle.ssn.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Api(tags = {"1. Auth"})
public class AuthController {
    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @ApiOperation(value = "Register", notes = "Create a new user.")
    @PostMapping("/new")
    public ResponseEntity<ResultResponseDTO> signUp(@RequestBody @Validated RegisterDTO registerDTO) throws CustomException {
        User newUser = this.authService.signUp(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultResponseDTO(true));
    }

    @ApiOperation(value = "Login", notes = "Login with Email/PW")
    @PostMapping("/local")
    public ResponseEntity<TokenResponseDTO> signIn(@RequestBody @Validated LoginDTO loginDTO) throws CustomException, JsonProcessingException {
        User user = this.authService.signIn(loginDTO);
        TokenResponseDTO response = this.jwtProvider.createTokensByLogin(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Refresh", notes = "Refresh AccessToken")
    @GetMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refresh(@AuthenticationPrincipal @ApiIgnore AccountDetails accountDetails) throws JsonProcessingException, CustomException {
        User user = accountDetails.getUser();
        TokenResponseDTO response = this.jwtProvider.refresh(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value="Email using check", notes = "Check your email is using."   )
    @GetMapping("/by-email/{email}/exists")
    public ResponseEntity<ExistResponseDTO> isExistEmail(@PathVariable(name = "email") String email) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(new ExistResponseDTO(this.authService.isEmailAvailable(email)));
    }

    @ApiOperation(value = "Username using check", notes = "Check your username is using.")
    @GetMapping("/by-username/{username}/exists")
    public ResponseEntity<ExistResponseDTO> isExistUsername(@PathVariable(name = "username") String username) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(new ExistResponseDTO(this.authService.isUsernameAvailable(username)));
    }

//    @ApiOperation(value = "Get My User Data", notes = "Get My User Data")
//    @GetMapping("/user")
//    public ResponseEntity<UserDataDTO> getUserData(@AuthenticationPrincipal @ApiIgnore AccountDetails accountDetails) throws CustomException {
//
//    }






}
