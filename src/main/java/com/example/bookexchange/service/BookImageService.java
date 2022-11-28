package com.example.bookexchange.service;

import com.example.bookexchange.persistence.model.Book;
import com.example.bookexchange.persistence.model.BookImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface BookImageService {

    String upload(Book book, MultipartFile multipartFile);

    Optional<BookImage> getImageByBookId(Long bookId);

    void deleteById(Long id);
}
