package com.example.bookexchange.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateDto {
    private Long sendersBookId;
    private Long recipientBookId;
    private Long userFromId;
    private Long userToId;
}
