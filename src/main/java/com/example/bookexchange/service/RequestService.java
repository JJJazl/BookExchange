package com.example.bookexchange.service;

import com.example.bookexchange.persistence.dto.RequestCreateDto;

public interface RequestService {

    void saveRequest(RequestCreateDto requestDto);


}
