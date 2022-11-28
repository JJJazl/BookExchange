package com.example.bookexchange.service.impl;

import com.example.bookexchange.exception.FileNotSavedException;
import com.example.bookexchange.persistence.model.Book;
import com.example.bookexchange.persistence.model.BookImage;
import com.example.bookexchange.persistence.repository.BookImageRepository;
import com.example.bookexchange.service.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class BookImageServiceImpl implements BookImageService {

    private final BookImageRepository bookImageRepository;

    @Autowired
    public BookImageServiceImpl(BookImageRepository bookImageRepository) {
        this.bookImageRepository = bookImageRepository;
    }

    @Override
    public String upload(Book book, MultipartFile multipartFile) {
        String imageUrl = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            bookImageRepository.save(new BookImage(imageUrl, multipartFile.getBytes(), book));
        } catch (IOException e) {
            throw new FileNotSavedException("File hasn't been saved");
        }
        return imageUrl;
    }

    @Override
    public Optional<BookImage> getImageByBookId(Long bookId) {
        return Optional.ofNullable(bookImageRepository.findBookImageByBookId(bookId));
    }

    @Override
    public void deleteById(Long id) {
        bookImageRepository.deleteById(id);
    }
}
