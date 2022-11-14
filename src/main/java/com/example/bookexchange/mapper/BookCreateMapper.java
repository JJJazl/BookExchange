package com.example.bookexchange.mapper;

import com.example.bookexchange.persistence.dto.BookCreateDto;
import com.example.bookexchange.persistence.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookCreateMapper {
    BookCreateDto toDto(Book book);
    //@Mapping(source = "userDto", target = "user")
    Book toEntity(BookCreateDto bookDto);
}
