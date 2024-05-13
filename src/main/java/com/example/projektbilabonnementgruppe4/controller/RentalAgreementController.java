package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.service.RentalAgreementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RentalAgreementController {

    private final RentalAgreementService rentalAgreementService;

    public RentalAgreementController(RentalAgreementService rentalAgreementService) {
        this.rentalAgreementService = rentalAgreementService;
    }


    @GetMapping("/rentalAgreements")
    public String showAllForm(Model model) {
        List<RentalAgreement> rentalAgreements = rentalAgreementService.getAllRentalAgreements();
        model.addAttribute("rentalAgreements", rentalAgreements);
        return "showAllRentalAgreements";
    }

    @PostMapping("/rentalAgreements/update/{id}")
    public String updateRentalAgreement(@PathVariable("id") int id, RentalAgreement rentalAgreement) {
        rentalAgreement.setContractId(id);
        rentalAgreementService.updateRentalAgreement(rentalAgreement);
        return "redirect:/showAllRentalAgreements";
    }

    @PostMapping("/rentalAgreements/delete/{id}")
    public String deleteRentalAgreement(@PathVariable("id") int id) {
        rentalAgreementService.deleteRentalAgreement(id);
        return "redirect:/showAllRentalAgreements";
    }

    @GetMapping("/createRentalAgreement")
    public String showCreateForm(Model model) {
        model.addAttribute("rentalAgreement", new RentalAgreement());
        return "createRentalAgreement";
    }

    @PostMapping("/rentalAgreements")
    public String createRentalAgreement(RentalAgreement rentalAgreement) {
        rentalAgreementService.createRentalAgreement(rentalAgreement);
        return "redirect:/rentalAgreements";
    }
    @GetMapping("/rented")
    public String showAllRentedCars(Model model) {
        List<RentalAgreement> rentedCars = rentalAgreementService.getAllRentedCars();
        model.addAttribute("rentedCars", rentedCars);
        return "car/rentedCars";
    }
}

