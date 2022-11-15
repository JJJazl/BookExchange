package com.example.bookexchange.web.controller;

import com.example.bookexchange.persistence.dto.BookCreateDto;
import com.example.bookexchange.persistence.dto.BookDetailsInfoDto;
import com.example.bookexchange.persistence.dto.BookMainInfoDto;
import com.example.bookexchange.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> save(
            @RequestPart MultipartFile image,
            @RequestPart BookCreateDto bookDto,
            @RequestParam Long userId) {
        bookService.save(bookDto, userId, image);
        return ResponseEntity.status(HttpStatus.OK).body("Book added!");
    }

    @GetMapping(value = "get-book/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookDetailsInfoDto> getBookInfo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id));
    }

    @GetMapping("all-user-books/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookMainInfoDto>> getAllBooksByUserId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooksByUserId(id));
    }

    @GetMapping()
    public ResponseEntity<List<BookMainInfoDto>> getBooksForMainPage(@RequestParam int countOfBook) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getLastAddedBooks(countOfBook));
    }
}
