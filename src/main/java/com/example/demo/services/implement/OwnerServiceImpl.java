package com.example.demo.services.implement;

import com.example.demo.models.Owner;
import com.example.demo.repositories.OwnerRepo;
import com.example.demo.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepo ownerRepo;

    public OwnerServiceImpl(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    @Override
    public List<Owner> findAll() {
        List<Owner> list = new ArrayList<>();
        ownerRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Owner object) {
        ownerRepo.delete(object);
    }

    @Override
    public void deleteByID(Long aLong) {
        ownerRepo.deleteById(aLong);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepo.save(object);
    }

    @Override
    public List<Owner> findAllByFirstNameLike(String firstName) {
        return ownerRepo.findAllByFirstNameLike(firstName);
    }
}
