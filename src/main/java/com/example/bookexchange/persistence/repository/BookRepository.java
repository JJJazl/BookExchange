package com.example.bookexchange.persistence.repository;

import com.example.bookexchange.persistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b join b.user u where u.id =:id")
    /*@Query(value = "select b from Book b join b on b.user.id = u.id where u.id =:id",
            nativeQuery = true)*/
    List<Book> findAllBooksByUserId(Long id);
    @Query(value = "select b from Book b join b.user u where b.id =:id")
    Book getBookById(Long id);
}
