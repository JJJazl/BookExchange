package com.example.bookexchange.service;

import com.example.bookexchange.persistence.dto.UserDto;

public interface UserService {
    UserDto register(UserDto userDto);

    void deleteById(long id);
}
