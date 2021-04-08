package com.example.demo.controllers;

import com.example.demo.models.Owner;
import com.example.demo.models.Pet;
import com.example.demo.models.PetType;
import com.example.demo.services.OwnerService;
import com.example.demo.services.PetService;
import com.example.demo.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final String VIEW_PET_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;


    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @GetMapping("/show")
    @ResponseBody
    public List<Pet> showAllPet(){
        List<Pet> pets = new ArrayList<>();
        List<Owner> ownerList = new ArrayList<>();
        ownerService.findAll().forEach(ownerList::add);
        for (int i = 0; i < ownerList.size(); i++) {
            int amountOfPet = ownerList.get(i).getPets().size();
            for (int j = 0; j < amountOfPet; j++) {
                pets.add(ownerList.get(i).getPets().get(j));
            }
        }
        System.out.println(pets);
        return pets;
    }

    @ModelAttribute("types")
    public List<PetType> populatePetType() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner bringOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @RequestMapping("pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEW_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, Pet pet,
                                      BindingResult result, Model model) {
        if (pet.getName().equalsIgnoreCase("")){
            pet.setOwner(owner);
            result.rejectValue("name","fillinformation");
        }
        if (pet.getBirthDate() == null){
            pet.setOwner(owner);
            result.rejectValue("birthDate","fillinformation");
        }
        if ( pet.isNew() && owner.getPet(pet.getName()) != null){
            pet.setOwner(owner);
            result.rejectValue("name", "duplicate", "already exist");
        }
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEW_PET_CREATE_OR_UPDATE_FORM;
        } else {
            owner.getPets().add(pet);
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initEditPet(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEW_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processEditForm(Pet pet, BindingResult bindingResult,
                                  Owner owner, Model model, @PathVariable Long petId){
        if (pet.getName().equalsIgnoreCase("")){
            System.out.println("No pet name provided");
            pet.setOwner(owner);
            bindingResult.rejectValue("name","fillinformation");
        }
        if (pet.getBirthDate() == null){
            pet.setOwner(owner);
            bindingResult.rejectValue("birthDate","fillinformation");
        }
        if (bindingResult.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEW_PET_CREATE_OR_UPDATE_FORM;
        }
        else {
            Pet oldPet = petService.findById(petId);
            oldPet.setName(pet.getName());
            oldPet.setPetType(pet.getPetType());
            oldPet.setOwner(owner);
            oldPet.setBirthDate(pet.getBirthDate());
            petService.save(oldPet);
            return "redirect:/owners/" + owner.getId();
        }
    }

}
