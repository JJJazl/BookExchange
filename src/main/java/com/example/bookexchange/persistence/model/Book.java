package com.example.bookexchange.persistence.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;
    @NotBlank
    @Min(10)
    private long ISBN;
    @NotBlank
    private String author;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"))
    private Set<Genre> genre;

    private String description;
    private String publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToMany(mappedBy = "sendingBookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Request> receivedRequests;

    @ManyToMany(mappedBy = "receivingBookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Request> sentRequests;
}
