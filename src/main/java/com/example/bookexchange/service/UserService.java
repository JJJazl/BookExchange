package com.example.bookexchange.service;

import com.example.bookexchange.persistence.dto.UserDto;

public interface UserService {
    UserDto addUser(UserDto userDto);

    boolean deleteById(long id);
}
