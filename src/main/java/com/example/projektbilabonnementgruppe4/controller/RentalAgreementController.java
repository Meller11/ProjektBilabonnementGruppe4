package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.service.CarStatusService;
import com.example.projektbilabonnementgruppe4.service.RentalAgreementService;
import com.example.projektbilabonnementgruppe4.viewModel.RentedCar;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class RentalAgreementController {

    @Autowired
    private RentalAgreementService rentalAgreementService;

    @Autowired
    private CarStatusService carStatusService;

    @GetMapping("/rentalAgreements")
    public String showAllForm(Model model) {
        List<RentalAgreement> rentalAgreements = rentalAgreementService.getAllRentalAgreements();
        model.addAttribute("rentalAgreements", rentalAgreements);
        return "showAllRentalAgreements";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("contractId") int contractId, Model model) {
        RentalAgreement rentalAgreement = rentalAgreementService.getRentalAgreement(contractId);
        model.addAttribute("rentalAgreement", rentalAgreement);
        return "updateRentalAgreement";
    }

    @PostMapping("/update")
    public String updateRentalAgreement(@ModelAttribute ("rentalAgreement") RentalAgreement rentalAgreement){
        rentalAgreementService.updateRentalAgreement(rentalAgreement);
        return "redirect:/rented";
    }

    @PostMapping("/delete")
    public String deleteRentalAgreement(@RequestParam("contractId") int contractId, @RequestParam("carId") int carId){
        rentalAgreementService.deleteRentalAgreement(contractId);
        carStatusService.updateCarStatus(carId, "Klar til udlejning");
        return "redirect:/rented";
    }

    @GetMapping("/createRentalAgreement")
    public String showCreateForm(Model model, @RequestParam("carId") int carId, HttpSession session){
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        model.addAttribute("rentalAgreement", new RentalAgreement());
        model.addAttribute("carId", carId);
        return "createRentalAgreement";
    }
    @PostMapping("/rentalAgreements")
    public String createRentalAgreement(RentalAgreement rentalAgreement, @RequestParam("carId") int carId) {
        rentalAgreementService.createRentalAgreement(rentalAgreement);
        carStatusService.updateCarStatus(carId, "Udlejet");
        return "redirect:/rented";
    }
    @GetMapping("/rented")
    public String showAllRentedCars(Model model) {
        List<RentedCar> rentedCars = rentalAgreementService.getAllRentedCars();
        model.addAttribute("rentedCars", rentedCars);
        return "car/rentedCars";
    }
}

