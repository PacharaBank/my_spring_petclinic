package com.example.demo.services;

import com.example.demo.models.Visit;

public interface VisitService extends CrudService<Visit, Long> {
    Visit findByPetId(Long id);
}
