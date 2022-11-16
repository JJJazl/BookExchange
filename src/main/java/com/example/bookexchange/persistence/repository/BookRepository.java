package com.example.bookexchange.persistence.repository;

import com.example.bookexchange.persistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b join b.bookImage bi where b.user.id =:id")
    List<Book> findAllBooksByUserId(Long id);

    //просмотреть запрос
    @Query("select b from Book b join b.user u where b.id =:id")
    Book getBookById(Long id);

    @Query(nativeQuery = true, value = "select * from book b join book_image bi on b.id = bi.book_id" +
            " order by b.id desc limit 5")
    List<Book> getLastAddedBooks(int count);
}
