package com.example.bookexchange.mapper;

import com.example.bookexchange.persistence.dto.BookDetailsInfoDto;
import com.example.bookexchange.persistence.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookDetailsInfoMapper {
    @Mapping(source = "user", target = "userDto")
    //@Mapping(source = "bookImage", target = "imageUrl")
    BookDetailsInfoDto toDto(Book book);

    Book toEntity(BookDetailsInfoDto bookDto);
}
