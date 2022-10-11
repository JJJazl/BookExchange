package com.example.bookexchange.persistence.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Request extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_from")
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_to")
    private User userTo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "request_book",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "sender_book_id")
    )
    private Set<Book> senderBookId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "request_book",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_book_id")
    )
    private Set<Book> recipientBookId;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime dateCreation;
}
