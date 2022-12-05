package com.blace.excursion.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationError {

    private String fieldName;

    private String errorDescription;

}
