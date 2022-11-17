package com.example.bookexchange.persistence.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
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

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "request_book",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "sender_book_id")
    )
    private List<Book> senderBookId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "request_book",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_book_id")
    )
    private List<Book> recipientBookId;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_book_id")
    private Book senderBookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_book_id")
    private Book recipientBookId;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime dateCreation;
}
