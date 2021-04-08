package com.example.demo.controllers;

import com.example.demo.models.Vet;
import com.example.demo.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"vets","vets.html"})
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping
    public String IndexPage(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Vet> allVet(){
        List<Vet> vets = new ArrayList<>();
        vetService.findAll().forEach(vets::add);
        return vets;
    }

    @PostMapping("/add")
    public void addVet(@RequestBody Vet vet){
        vetService.save(vet);
    }

    @DeleteMapping("/{vetId}/remove")
    public void removeVet(@PathVariable Long vetId){
        vetService.deleteByID(vetId);
    }
}
