package com.blace.excursion.dto.error;

import lombok.Getter;

import java.util.Set;

@Getter
public class ValidationErrrorResponse extends ErrorResponse {

    private Set<ValidationError> errors;

    public ValidationErrrorResponse(int statusCode, String message, Set<ValidationError> errors) {
        super(statusCode, message);
        this.errors = errors;
    }

}
