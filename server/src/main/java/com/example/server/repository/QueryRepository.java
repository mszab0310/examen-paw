package com.example.server.repository;

import com.example.server.model.Queries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Queries, Long> {

}
