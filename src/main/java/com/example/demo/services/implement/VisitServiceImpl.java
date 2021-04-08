package com.example.demo.services.implement;

import com.example.demo.models.Visit;
import com.example.demo.repositories.VisitRepo;
import com.example.demo.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    private final VisitRepo visitRepo;

    public VisitServiceImpl(VisitRepo visitRepo) {
        this.visitRepo = visitRepo;
    }

    @Override
    public List<Visit> findAll() {
        List<Visit> visits = new ArrayList<>();
        visitRepo.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepo.findById(aLong).orElse(null);
    }

    @Override
    public void delete(Visit object) {
        visitRepo.delete(object);
    }

    @Override
    public void deleteByID(Long aLong) {
        visitRepo.deleteById(aLong);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepo.save(object);
    }

    @Override
    public Visit findByPetId(Long id) {
        return visitRepo.findByPetId(id);
    }
}
