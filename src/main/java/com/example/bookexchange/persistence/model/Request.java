package com.example.bookexchange.persistence.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Request extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_from")
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_to")
    private User userTo;

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
