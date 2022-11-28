package com.example.bookexchange.mapper;

import com.example.bookexchange.persistence.dto.RequestInfoDto;
import com.example.bookexchange.persistence.model.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestInfoMapper {

    @Mapping(source = "recipientBookId.title", target = "recipientBookTitle")
    @Mapping(source = "senderBookId.title", target = "senderBookTitle")
    @Mapping(source = "userFrom.username", target = "username")
    RequestInfoDto toDto(Request request);
}
