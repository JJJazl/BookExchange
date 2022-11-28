package com.example.bookexchange.persistence.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Book extends BaseEntity<Long> {
    @NotBlank
    private String title;
    @NotBlank
    @Length(min = 10)
    private String ISBN;
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
    private User user;

    @OneToOne(mappedBy = "book")
    private BookImage bookImage;

    @OneToMany(mappedBy = "senderBookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Request> sentRequests;

    @OneToMany(mappedBy = "recipientBookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Request> receivedRequests;
}
