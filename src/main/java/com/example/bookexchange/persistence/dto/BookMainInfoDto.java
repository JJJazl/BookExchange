package com.example.bookexchange.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMainInfoDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private byte[] imageData;
}
