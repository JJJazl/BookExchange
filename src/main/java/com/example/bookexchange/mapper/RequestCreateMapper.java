package com.example.bookexchange.mapper;

import com.example.bookexchange.persistence.dto.RequestCreateDto;
import com.example.bookexchange.persistence.model.Book;
import com.example.bookexchange.persistence.model.Request;
import com.example.bookexchange.persistence.model.Status;
import com.example.bookexchange.persistence.model.User;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface RequestCreateMapper {

    default Request toEntity(RequestCreateDto requestDto) {
        if (requestDto == null) {
            //ИЗМЕНИТЬ!!!!
            return null;
        }

        User userTo = new User();
        userTo.setId(requestDto.getUserToId());

        User userFrom = new User();
        userFrom.setId(requestDto.getUserFromId());

        Book sendersBook = new Book();
        sendersBook.setId(requestDto.getSendersBookId());

        Book recipientBook = new Book();
        recipientBook.setId(requestDto.getRecipientBookId());

        Request request = new Request();

        request.setUserTo(userTo);
        request.setUserFrom(userFrom);
        request.setSenderBookId(sendersBook);
        request.setRecipientBookId(recipientBook);

        return request;
    }
}
