package com.blace.excursion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthenticationRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
