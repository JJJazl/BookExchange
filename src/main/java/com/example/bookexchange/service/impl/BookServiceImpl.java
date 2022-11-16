package com.example.bookexchange.service.impl;

import com.example.bookexchange.exception.BookAlreadyExists;
import com.example.bookexchange.exception.BookNotFoundException;
import com.example.bookexchange.mapper.BookCreateMapper;
import com.example.bookexchange.mapper.BookDetailsInfoMapper;
import com.example.bookexchange.mapper.BookMainInfoMapper;
import com.example.bookexchange.persistence.dto.BookCreateDto;
import com.example.bookexchange.persistence.dto.BookDetailsInfoDto;
import com.example.bookexchange.persistence.dto.BookMainInfoDto;
import com.example.bookexchange.persistence.model.Book;
import com.example.bookexchange.persistence.model.BookImage;
import com.example.bookexchange.persistence.model.User;
import com.example.bookexchange.persistence.repository.BookRepository;
import com.example.bookexchange.persistence.repository.UserRepository;
import com.example.bookexchange.service.BookImageService;
import com.example.bookexchange.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookImageService bookImageService;

    private final UserRepository userRepository;
    private final BookCreateMapper bookCreateMapper;
    private final BookDetailsInfoMapper bookDetailsInfoMapper;
    private final BookMainInfoMapper bookMainInfoMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           BookImageService bookImageService,
                           UserRepository userRepository,
                           BookCreateMapper bookCreateMapper,
                           BookDetailsInfoMapper bookDetailsInfoMapper,
                           BookMainInfoMapper bookMainInfoMapper) {
        this.bookRepository = bookRepository;
        this.bookImageService = bookImageService;
        this.userRepository = userRepository;
        this.bookCreateMapper = bookCreateMapper;
        this.bookDetailsInfoMapper = bookDetailsInfoMapper;
        this.bookMainInfoMapper = bookMainInfoMapper;
    }

    @Override
    public void save(BookCreateDto bookDto, Long userId, MultipartFile image) {
        List<Book> userBooks = bookRepository.findAllBooksByUserId(userId);

        Optional<Book> existingBook = userBooks.stream()
                .filter(book -> isBookExist(bookDto).test(book))
                .findFirst();
        if (existingBook.isPresent()) {
            throw new BookAlreadyExists("The same book already exists");
        }

        User user = userRepository.findById(userId).orElseThrow();
        Book book = Optional.of(bookDto)
                .map(bookCreateMapper::toEntity).orElseThrow();
        book.setUser(user);

        bookRepository.save(book);
        bookImageService.upload(book, image);
    }

    private Predicate<Book> isBookExist(BookCreateDto bookCreateDto) {
        return book -> book.getISBN().equals(bookCreateDto.getISBN())
                && book.getTitle().equals(bookCreateDto.getTitle());
    }

    @Override
    public BookDetailsInfoDto getBookById(Long id) {
        BookDetailsInfoDto bookDetailsInfoDto = Optional.of(bookRepository.getBookById(id))
                .map(bookDetailsInfoMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException("Book does not exist with id = " + id));
        BookImage bookImage = bookImageService.getImageUrl(id);
        bookDetailsInfoDto.setImageData(bookImage.getData());
        /*String imageUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(bookImage.getUrl())
                .toUriString();
        bookDetailsInfoDto.setImageUrl(imageUrl);*/
        return bookDetailsInfoDto;
    }

    @Override
    public List<BookMainInfoDto> getAllBooksByUserId(Long userId) {
        //добавить проверку на юзера
        return bookRepository.findAllBooksByUserId(userId).stream()
                .map(bookMainInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookMainInfoDto> getLastAddedBooks(int countOfBook) {
        return bookRepository.getLastAddedBooks(countOfBook).stream()
                .map(bookMainInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(long id) {
        //удалить book_image запись!!!
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return true;
                }).orElse(false);
    }
}
