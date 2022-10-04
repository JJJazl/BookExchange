package com.example.bookexchange.persistence.model;

import jdk.jfr.Timestamp;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_from")
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_to")
    private User userTo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "request_sended_book",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> sendingBookId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "request_received_book",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> receivingBookId;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime dateCreation;
}
