package com.example.bookexchange.service;

import com.example.bookexchange.persistence.dto.BookCreateDto;
import com.example.bookexchange.persistence.dto.BookDetailsInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface BookService {
    void save(BookCreateDto bookDto, Long userId, MultipartFile image);

    BookDetailsInfoDto getBookById(Long id);
    boolean deleteById(long id);
}
