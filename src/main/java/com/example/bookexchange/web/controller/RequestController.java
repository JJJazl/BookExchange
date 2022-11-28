package com.example.bookexchange.web.controller;

import com.example.bookexchange.persistence.dto.RequestCreateDto;
import com.example.bookexchange.persistence.dto.RequestInfoDto;
import com.example.bookexchange.persistence.dto.RequestProcessDto;
import com.example.bookexchange.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/requests")
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

    @GetMapping("/get-all-request-to-user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<RequestInfoDto>> getAllRequestsToUser(
            @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(requestService.getAllRequestsByUserId(userId));
    }

    @PostMapping("/accept-exchange")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> acceptExchange(@RequestBody RequestProcessDto requestDto) {
        requestService.acceptRequest(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("Request is accepted");
    }
}
