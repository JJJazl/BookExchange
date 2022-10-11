package com.example.bookexchange.mapper;

import com.example.bookexchange.persistence.dto.UserDto;
import com.example.bookexchange.persistence.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
