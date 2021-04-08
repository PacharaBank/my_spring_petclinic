package com.example.demo.repositories;

import com.example.demo.models.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepo extends CrudRepository<Visit, Long> {
    Visit findByPetId(Long id);
}
