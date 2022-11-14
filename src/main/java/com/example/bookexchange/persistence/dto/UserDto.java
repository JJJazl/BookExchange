package com.example.bookexchange.persistence.dto;

import com.example.bookexchange.persistence.model.Book;
import com.example.bookexchange.persistence.model.Request;
import com.example.bookexchange.persistence.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id; //добавил
    private String username;
    private String email;
    private String password;
    private Role role;
    private Set<Book> books;
    private List<Request> requestsFrom;
    private List<Request> requestsTo;
}
