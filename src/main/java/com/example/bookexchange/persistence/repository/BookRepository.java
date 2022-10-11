package com.example.bookexchange.persistence.repository;

import com.example.bookexchange.persistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b join b.userId u where u.email =:email")
    Set<Book> findAllBooksByUserEmail(String email);
}
