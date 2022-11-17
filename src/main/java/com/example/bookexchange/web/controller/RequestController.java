package com.example.bookexchange.web.controller;

import com.example.bookexchange.persistence.dto.RequestCreateDto;
import com.example.bookexchange.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vi/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/process-request")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> save(@RequestBody RequestCreateDto requestDto) {
        requestService.saveRequest(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("Request is pending");
    }
}
