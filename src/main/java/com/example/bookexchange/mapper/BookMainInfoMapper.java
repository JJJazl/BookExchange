package com.example.bookexchange.mapper;

import com.example.bookexchange.persistence.dto.BookMainInfoDto;
import com.example.bookexchange.persistence.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMainInfoMapper {

    @Mapping(source = "bookImage.data", target = "imageData")
    BookMainInfoDto toDto(Book book);
}
