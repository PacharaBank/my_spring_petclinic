package com.example.demo.bootstrap;

import com.example.demo.models.*;
import com.example.demo.services.OwnerService;
import com.example.demo.services.PetTypeService;
import com.example.demo.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


public class dataloader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public dataloader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData(){

        PetType type1 = new PetType();
        type1.setName("Dog");
        petTypeService.save(type1);

        PetType type2 = new PetType();
        type2.setName("Cat");
        petTypeService.save(type2);

        Owner owner1 = new Owner();
        owner1.setFirstName("Sam");
        owner1.setLastName("Delton");
        owner1.setAddress("114/65");
        owner1.setCity("Bangkok");
        owner1.setTelephone("0124845555");

        Visit visit1 = new Visit();
        visit1.setDate(LocalDate.now());
        visit1.setDescription("Normal visit");

        Pet pet1 = new Pet();
        pet1.setName("Dogie");
        pet1.setPetType(type1);
        pet1.setBirthDate(LocalDate.of(2015,01,05));
        pet1.setOwner(owner1);
        pet1.getVisitList().add(visit1);

        visit1.setPet(pet1);

        owner1.getPets().add(pet1);
        ownerService.save(owner1);

        Vet vet1 = new Vet();
        vet1.setFirstName("Jodan");
        vet1.setLastName("Baleford");
        vet1.setSpecialty("Dentistry");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mark");
        vet2.setLastName("Albrem");
        vet2.setSpecialty("Chemistry");

        vetService.save(vet2);


    }
}
