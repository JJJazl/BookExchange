package com.example.bookexchange.service;

import com.example.bookexchange.persistence.dto.BookCreateDto;
import com.example.bookexchange.persistence.dto.BookDetailsInfoDto;
import com.example.bookexchange.persistence.dto.BookMainInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BookService {
    void save(BookCreateDto bookDto, Long userId, MultipartFile image);

    BookDetailsInfoDto getBookById(Long id);

    List<BookMainInfoDto> getAllBooksByUserId(Long userId);

    List<BookMainInfoDto> getLastAddedBooks(int countOfBook);
    boolean deleteById(long id);
}
