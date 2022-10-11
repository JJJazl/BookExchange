package com.example.bookexchange.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationRequestDto {

    @NotBlank
    @Pattern(regexp = ".+@.+\\..+")
    String email;

    @NotBlank
    @Size(min = 3)
    String password;
}
