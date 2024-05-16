package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.service.CarService;
import com.example.projektbilabonnementgruppe4.service.CarStatusService;
import com.example.projektbilabonnementgruppe4.service.RentalAgreementService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "redirect:/rentalAgreements";
    }
    @GetMapping("/rented")
    public String showAllRentedCars(Model model) {
        List<RentalAgreement> rentedCars = rentalAgreementService.getAllRentedCars();
        model.addAttribute("rentedCars", rentedCars);
        return "car/rentedCars";
    }
}

