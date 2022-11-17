package com.example.bookexchange.service.impl;

import com.example.bookexchange.mapper.RequestCreateMapper;
import com.example.bookexchange.persistence.dto.RequestCreateDto;
import com.example.bookexchange.persistence.model.Status;
import com.example.bookexchange.persistence.repository.RequestRepository;
import com.example.bookexchange.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestCreateMapper requestCreateMapper;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository,
                              RequestCreateMapper requestCreateMapper) {
        this.requestRepository = requestRepository;
        this.requestCreateMapper = requestCreateMapper;
    }

    @Override
    public void saveRequest(RequestCreateDto requestDto) {
        requestRepository.save(Optional.of(requestDto)
                .map(requestCreateMapper::toEntity)
                .map(request -> {
                    request.setStatus(Status.PENDING);
                    request.setDateCreation(LocalDateTime.now());
                    return request;
                })
                .orElseThrow());
    }
}
