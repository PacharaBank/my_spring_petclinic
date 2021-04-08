package com.example.demo.repositories;

import com.example.demo.models.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepo extends CrudRepository<Vet, Long> {
}
