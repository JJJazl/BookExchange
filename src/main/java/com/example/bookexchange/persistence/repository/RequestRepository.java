package com.example.bookexchange.persistence.repository;

import com.example.bookexchange.persistence.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(nativeQuery = true,
            value = "select * " +
                    "from Request r " +
                    "join user_account ua " +
                    "on r.user_to = ua.id " +
                    "where r.user_to = :userId " +
                    "order by r.date_creation DESC")
    List<Request> getAllRequestsByUserId(@Param(value = "userId") Long userId);
}
