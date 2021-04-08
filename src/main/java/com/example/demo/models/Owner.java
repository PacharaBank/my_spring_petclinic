package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends Person{

    private String address;
    private String telephone;
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();


    public Pet getPet(String name){
        for (Pet pet : pets){
            if (!pet.isNew()){
                String compName = pet.getName();
                if (compName.equalsIgnoreCase(name)){
                    return pet;
                }
            }
        }
        return null;
    }

}
