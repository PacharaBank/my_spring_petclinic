package com.example.demo.services.implement;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepo;
import com.example.demo.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepo petRepo;

    public PetServiceImpl(PetRepo petRepo) {
        this.petRepo = petRepo;
    }

    @Override
    public List<Pet> findAll() {
        List<Pet> list = new ArrayList<>();
        petRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepo.findById(aLong).orElse(null);
    }

    @Override
    public void delete(Pet object) {
        petRepo.delete(object);
    }

    @Override
    public void deleteByID(Long aLong) {
        petRepo.deleteById(aLong);
    }

    @Override
    public Pet save(Pet object) {
        return petRepo.save(object);
    }
}
