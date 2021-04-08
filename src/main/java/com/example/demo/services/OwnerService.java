package com.example.demo.services;

import com.example.demo.models.Owner;

import java.util.List;

public interface OwnerService extends CrudService <Owner, Long>{
    List<Owner> findAllByFirstNameLike(String firstName);
}
