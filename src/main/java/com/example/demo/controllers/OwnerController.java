package com.example.demo.controllers;

import com.example.demo.models.Owner;
import com.example.demo.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void SetAllowedFields(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwner(Model model) {
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(@Valid Owner owner, Model model, BindingResult result) {

        List<Owner> ownerList = ownerService.findAllByFirstNameLike("%" + owner.getFirstName() + "%");
        if (ownerList.isEmpty()) {
            result.rejectValue("firstName", "notFound");
            return "owners/findOwners";
        }
        else if (ownerList.size() == 1){
            owner = ownerList.iterator().next();
            return "redirect:/owners/" + owner.getId();
        }
        else {
            model.addAttribute("selection", ownerList);
            return "owners/ownersList";
        }
    }

    @GetMapping("{ownerId}")
    public String showOwner(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/ownerDetails";
    }

    @RequestMapping("/new")
    public String initCreationForm(Model model) {
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Owner owner, BindingResult result) {
        boolean isValid = true;
        if (owner.getFirstName().equalsIgnoreCase("")) {
            isValid = false;
            result.rejectValue("firstName", "fillinformation");
        }if (owner.getLastName().equalsIgnoreCase("")){
            isValid = false;
            result.rejectValue("lastName", "fillinformation");
        }if (owner.getAddress().equalsIgnoreCase("")){
            isValid = false;
            result.rejectValue("address", "fillinformation");
        }if (owner.getCity().equalsIgnoreCase("")){
            isValid = false;
            result.rejectValue("city", "fillinformation");
        }if ((owner.getTelephone().equalsIgnoreCase(""))){
            isValid = false;
            result.rejectValue("telephone", "fillinformation");
        }
        if (isValid){
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
        return "owners/createOrUpdateOwnerForm";
    }

    @RequestMapping("{ownerId}/edit")
    public String initEditForm(Model model, @PathVariable Long ownerId){
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("{ownerId}/edit")
    public String processEditForm(@Valid Owner owner, BindingResult bindingResult,
                                  @PathVariable Long ownerId){
        boolean isValid = true;
        if(bindingResult.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }
        if (owner.getFirstName().equalsIgnoreCase("")) {
            isValid = false;
            bindingResult.rejectValue("firstName", "fillinformation");
        }if (owner.getLastName().equalsIgnoreCase("")){
            isValid = false;
            bindingResult.rejectValue("lastName", "fillinformation");
        }if (owner.getAddress().equalsIgnoreCase("")){
            isValid = false;
            bindingResult.rejectValue("address", "fillinformation");
        }if (owner.getCity().equalsIgnoreCase("")){
            isValid = false;
            bindingResult.rejectValue("city", "fillinformation");
        }if ((owner.getTelephone().equalsIgnoreCase(""))){
            isValid = false;
            bindingResult.rejectValue("telephone", "fillinformation");
        }
        if (isValid){
            Owner oldOwner = ownerService.findById(ownerId);
            oldOwner.setFirstName(owner.getFirstName());
            oldOwner.setLastName(owner.getLastName());
            oldOwner.setTelephone(owner.getTelephone());
            oldOwner.setAddress(owner.getAddress());
            oldOwner.setCity(owner.getCity());
            ownerService.save(oldOwner);
            return "redirect:/owners/" + oldOwner.getId();
        }
        else return "owners/createOrUpdateOwnerForm";
    }

    @DeleteMapping("/{ownerId}/delete")
    public void deleteOwner(@PathVariable Long ownerId){

        ownerService.deleteByID(ownerId);
    }

}
