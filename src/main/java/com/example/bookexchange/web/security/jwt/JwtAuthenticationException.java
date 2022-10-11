package com.example.bookexchange.web.security.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.security.sasl.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {
    private HttpStatus httpStatus;

    public JwtAuthenticationException(String detail) {
        super(detail);
    }

    public JwtAuthenticationException(String detail, HttpStatus httpStatus) {
        super(detail);
        this.httpStatus = httpStatus;
    }
}
