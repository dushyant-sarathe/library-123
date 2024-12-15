package com.libraray.controller;



import com.libraray.dto.AuthenticationResponse;
import com.libraray.dto.PersonDTO;
import com.libraray.dto.SignIn;
import com.libraray.dto.SignUp;
import com.libraray.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Authentication")
@CrossOrigin("*")
public class AuthController {

     private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<PersonDTO> registerUser(@RequestBody @Valid SignUp signUpDto){

        PersonDTO res;
        res = userService.registerUser(signUpDto);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid SignIn loginDto) {

        AuthenticationResponse res;
        res = userService.authenticateUser(loginDto);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
