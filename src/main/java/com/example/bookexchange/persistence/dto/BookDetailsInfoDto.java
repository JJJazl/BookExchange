package com.example.bookexchange.persistence.dto;

import com.example.bookexchange.persistence.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailsInfoDto {
    private String title;
    private String ISBN;
    private String author;
    private Set<Genre> genre;
    private String description;
    private String publisher;
    private String imageUrl;
    private UserInfoForRequestDto userDto;
}
