package com.example.bookexchange.persistence.repository;

import com.example.bookexchange.persistence.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
