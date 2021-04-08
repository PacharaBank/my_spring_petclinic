package com.example.demo.services.implement;

import com.example.demo.models.Vet;
import com.example.demo.repositories.VetRepo;
import com.example.demo.services.PetService;
import com.example.demo.services.VetService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VetServiceImpl implements VetService {

    private final VetRepo vetRepo;

    public VetServiceImpl(VetRepo vetRepo) {
        this.vetRepo = vetRepo;
    }

    @Override
    public List<Vet> findAll() {
        List<Vet> list = new ArrayList<>();
        vetRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepo.findById(aLong).orElse(null);
    }

    @Override
    public void delete(Vet object) {
        vetRepo.delete(object);
    }

    @Override
    public void deleteByID(Long aLong) {
        vetRepo.deleteById(aLong);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepo.save(object);
    }
}
