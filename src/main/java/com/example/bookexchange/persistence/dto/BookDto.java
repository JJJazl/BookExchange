package com.example.bookexchange.persistence.dto;

import com.example.bookexchange.persistence.model.Genre;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String title;
    private long ISBN;
    private String author;
    private Set<Genre> genre;
    private String description;
    private String publisher;
    private UserDto owner;
}
