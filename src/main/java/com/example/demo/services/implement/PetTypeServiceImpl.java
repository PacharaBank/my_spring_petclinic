package com.example.demo.services.implement;

import com.example.demo.models.PetType;
import com.example.demo.repositories.PetTypeRepo;
import com.example.demo.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetTypeServiceImpl implements PetTypeService {

    private final PetTypeRepo petTypeRepo;

    public PetTypeServiceImpl(PetTypeRepo petTypeRepo) {
        this.petTypeRepo = petTypeRepo;
    }

    @Override
    public List<PetType> findAll() {
        List<PetType> petTypes = new ArrayList<>();
        petTypeRepo.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepo.findById(aLong).orElse(null);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepo.delete(object);
    }

    @Override
    public void deleteByID(Long aLong) {
        petTypeRepo.deleteById(aLong);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepo.save(object);
    }
}
