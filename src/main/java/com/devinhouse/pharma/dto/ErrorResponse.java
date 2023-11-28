package com.devinhouse.pharma.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    private HttpStatus status;

    private String error;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.error = message;
    }

}
