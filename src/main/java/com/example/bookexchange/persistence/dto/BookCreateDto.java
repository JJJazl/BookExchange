package com.example.bookexchange.persistence.dto;

import com.example.bookexchange.persistence.model.Genre;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDto {
    private String title;
    private String ISBN;
    private String author;
    private Set<Genre> genre;
    private String description;
    private String publisher;
}
