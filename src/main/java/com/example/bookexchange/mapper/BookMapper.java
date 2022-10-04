package com.example.bookexchange.mapper;

import com.example.bookexchange.persistence.dto.BookDto;
import com.example.bookexchange.persistence.model.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}
