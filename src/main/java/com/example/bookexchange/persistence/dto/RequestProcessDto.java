package com.example.bookexchange.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProcessDto {
    Long id;
    Long userFromId;
    Long userToId;
    Long recipientBookId;
    Long senderBookId;
}
