package com.example.demo.controllers;

import com.example.demo.models.Pet;
import com.example.demo.models.Visit;
import com.example.demo.services.PetService;
import com.example.demo.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setDisAllowField(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisitList().add(visit);
        return visit;
    }

    @RequestMapping("/new")
    public String initAddVisit(@PathVariable Long petId, Model model) {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/new")
    public String processAddVisit(@Valid Visit visit, @PathVariable Long ownerId,
                                  BindingResult bindingResult, @PathVariable Long petId) {
        if (bindingResult.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            Pet pet = petService.findById(petId);
            pet.getVisitList().add(visit);
            visit.setPet(pet);
            visitService.save(visit);
            return "redirect:/owners/" + ownerId;
        }
    }
}
