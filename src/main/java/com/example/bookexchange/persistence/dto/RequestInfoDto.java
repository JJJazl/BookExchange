package com.example.bookexchange.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInfoDto {
    private Long id;
    private String username;
    private LocalDate dateOfRegistration;
    private String receivedBookName;
    private String ownBookName;
}
