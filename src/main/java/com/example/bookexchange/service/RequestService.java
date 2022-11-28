package com.example.bookexchange.service;

import com.example.bookexchange.persistence.dto.RequestCreateDto;
import com.example.bookexchange.persistence.dto.RequestInfoDto;
import com.example.bookexchange.persistence.dto.RequestProcessDto;

import java.util.List;

public interface RequestService {

    void saveRequest(RequestCreateDto requestDto);

    List<RequestInfoDto> getAllRequestsByUserId(Long userId);

    void acceptRequest(RequestProcessDto requestDto);

    void canselRequest(Long id);
}
