package com.example.demo.repositories;

import com.example.demo.models.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepo extends CrudRepository<Owner, Long> {
        List<Owner> findAllByFirstNameLike(String firstName);
}
