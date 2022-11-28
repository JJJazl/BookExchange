package com.example.bookexchange.service.impl;

import com.example.bookexchange.mapper.RequestCreateMapper;
import com.example.bookexchange.mapper.RequestInfoMapper;
import com.example.bookexchange.persistence.dto.RequestCreateDto;
import com.example.bookexchange.persistence.dto.RequestInfoDto;
import com.example.bookexchange.persistence.dto.RequestProcessDto;
import com.example.bookexchange.persistence.model.Book;
import com.example.bookexchange.persistence.model.Request;
import com.example.bookexchange.persistence.model.Status;
import com.example.bookexchange.persistence.model.User;
import com.example.bookexchange.persistence.repository.BookRepository;
import com.example.bookexchange.persistence.repository.RequestRepository;
import com.example.bookexchange.persistence.repository.UserRepository;
import com.example.bookexchange.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RequestCreateMapper requestCreateMapper;

    private final RequestInfoMapper requestInfoMapper;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository,
                              BookRepository bookRepository,
                              UserRepository userRepository,
                              RequestCreateMapper requestCreateMapper,
                              RequestInfoMapper requestInfoMapper) {
        this.requestRepository = requestRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.requestCreateMapper = requestCreateMapper;
        this.requestInfoMapper = requestInfoMapper;
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

    @Override
    public List<RequestInfoDto> getAllRequestsByUserId(Long userId) {
        var list = requestRepository.getAllRequestsByUserId(userId);
        return requestRepository.getAllRequestsByUserId(userId).stream()
                .map(requestInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void acceptRequest(RequestProcessDto requestDto) {
        /*var sender = userRepository.findById(requestDto.getUserFromId())
                .orElseThrow();
        var recipient = userRepository.findById(requestDto.getUserToId())
                .orElseThrow();
        var senderBook = bookRepository.findById(requestDto.getSenderBookId())
                .orElseThrow();
        senderBook.setUser(recipient);
        var recipientBook = bookRepository.findById(requestDto.getRecipientBookId())
                .orElseThrow();
        recipientBook.setUser(sender);

        bookRepository.save(senderBook);
        bookRepository.save(recipientBook);

        Request request = requestRepository.findById(requestDto.getId()).orElseThrow();
        request.setStatus(Status.ACCEPTED);

        requestRepository.save(request);*/
        Book senderBook = bookRepository.findById(requestDto.getSenderBookId())
                .orElseThrow();
        Book recipientBook = bookRepository.findById(requestDto.getRecipientBookId())
                .orElseThrow();

        User sender = senderBook.getUser();

        senderBook.setUser(recipientBook.getUser());
        recipientBook.setUser(sender);

        Request request = requestRepository.findById(requestDto.getId()).orElseThrow();
        request.setStatus(Status.ACCEPTED);

        bookRepository.save(senderBook);
        bookRepository.save(recipientBook);
        requestRepository.save(request);
    }

    @Override
    public void canselRequest(Long id) {

    }
}
