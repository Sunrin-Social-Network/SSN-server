package io.twotle.ssn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.twotle.ssn.component.CustomException;
import io.twotle.ssn.dto.EmailDTO;
import io.twotle.ssn.dto.ExistResponseDTO;
import io.twotle.ssn.dto.RegisterDTO;
import io.twotle.ssn.dto.ResultResponseDTO;
import io.twotle.ssn.entity.User;
import io.twotle.ssn.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Api(tags = {"1. Auth"})
public class AuthController {
    private final AuthService authService;

    @ApiOperation(value = "Register", notes = "Create a new user.")
    @PostMapping("/new")
    public ResponseEntity<ResultResponseDTO> signUp(@RequestBody @Validated RegisterDTO registerDTO) throws CustomException {
        User newUser = this.authService.signUp(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultResponseDTO(true));
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






}
