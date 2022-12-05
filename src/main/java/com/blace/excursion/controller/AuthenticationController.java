package com.blace.excursion.controller;

import com.blace.excursion.dto.AuthenticationRequest;
import com.blace.excursion.dto.UserTokenState;
import com.blace.excursion.dto.error.ValidationError;
import com.blace.excursion.dto.error.ValidationErrrorResponse;
import com.blace.excursion.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@RestController
@RequestMapping(value = "api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PutMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody @Validated AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        UserTokenState userTokenState;

        try {
            userTokenState = this.authenticationService.loginUser(authenticationRequest);

        } catch (AuthenticationException authenticationException) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userTokenState, HttpStatus.OK);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrrorResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

        HashSet<ValidationError> errors = new HashSet<ValidationError>();

        for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errors.add(new ValidationError(error.getField(), error.getDefaultMessage()));
        }

        return new ResponseEntity<>(new ValidationErrrorResponse(400, "Invalid request data", errors), HttpStatus.BAD_REQUEST);
    }
}