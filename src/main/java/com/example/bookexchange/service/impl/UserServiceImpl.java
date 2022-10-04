package com.example.bookexchange.service.impl;

import com.example.bookexchange.exception.UserAlreadyExists;
import com.example.bookexchange.mapper.UserMapper;
import com.example.bookexchange.persistence.dto.UserDto;
import com.example.bookexchange.persistence.model.Role;
import com.example.bookexchange.persistence.repository.UserRepository;
import com.example.bookexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
//МОЖНО ЗАМЕНИТЬ КОНСТРУКТОР НА АННОТАЦИЮ
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;


    public UserServiceImpl(@Autowired UserRepository userRepo,
                           @Autowired PasswordEncoder encoder,
                           UserMapper userMapper) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto register(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExists("User with this email already exists!");
        }
        //возможно вынести в отдельный mapper(класс или метод)
        UserDto saved = Optional.of(userDto)
                        .map(userMapper::toEntity)
                        .map(user -> {
                            user.setPassword(encoder.encode(user.getPassword()));
                            user.setRole(Role.USER);
                            user.setBooks(Collections.emptySet());
                            user.setRequestsFrom(Collections.emptyList());
                            user.setRequestsTo(Collections.emptyList());
                            return userRepo.save(user);
                        })
                .map(userMapper::toDto)
                .orElseThrow();
        return saved;
    }

    @Override
    public void deleteById(long id) {
        userRepo.deleteById(id);
    }
}
