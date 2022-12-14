package com.example.bookexchange.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInfoDto {
    private Long id;
    private String recipientBookTitle;
    private String senderBookTitle;
    private String username;
    private String status;
}
