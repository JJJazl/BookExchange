package com.example.bookexchange.service;

import com.example.bookexchange.persistence.model.Book;
import com.example.bookexchange.persistence.model.BookImage;
import org.springframework.web.multipart.MultipartFile;

public interface BookImageService {

    String upload(Book book, MultipartFile multipartFile);

    BookImage getImageUrl(Long id);
}
