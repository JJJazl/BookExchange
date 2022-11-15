package com.example.bookexchange.mapper;

import com.example.bookexchange.persistence.dto.UserInfoForRequestDto;
import com.example.bookexchange.persistence.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInfoForRequestMapper {

    User toEntity(UserInfoForRequestDto userInfoForRequestDto);

    UserInfoForRequestDto toDto(User user);
}
