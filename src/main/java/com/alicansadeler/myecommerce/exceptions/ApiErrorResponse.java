package com.alicansadeler.myecommerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class ApiErrorResponse {
    private String message;
    private int status;
}
