package com.example.bookexchange.persistence.repository;

import com.example.bookexchange.persistence.model.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageRepository extends JpaRepository<BookImage, Long> {
    BookImage findBookImageByBookId(Long bookId);

}
