package com.example.bookexchange.persistence.repository;

import com.example.bookexchange.persistence.model.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageRepository extends JpaRepository<BookImage, Long> {

    @Query(value = "select bi from BookImage bi join bi.book b where b.id =:bookId")
    BookImage findBookImageByBookId(Long bookId);
}
