package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.Employee;
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
@RequestMapping("/rentalAgreements")
public class RentalAgreementController {

    @Autowired
    private RentalAgreementService rentalAgreementService;

    @Autowired
    private CarStatusService carStatusService;

    /*@GetMapping("/rentalAgreements")
    public String showAllForm(Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            List<RentalAgreement> rentalAgreements = rentalAgreementService.getAllRentalAgreements();
            model.addAttribute("rentalAgreements", rentalAgreements);
            return "showAllRentalAgreements";
        } else {
            return "redirect:/";
        }
    }*/
    @GetMapping("/rented")
    public String showAllRentedCars(Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            List<RentedCar> rentedCars = rentalAgreementService.getAllRentedCars();
            model.addAttribute("rentedCars", rentedCars);
            return "/car/rentedCars";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, @RequestParam("carId") int carId, HttpSession session){
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
            model.addAttribute("rentalAgreement", new RentalAgreement());
            model.addAttribute("carId", carId);
            return "/rentalAgreement/createRentalAgreement";
        }else {
            return "redirect:/";
        }
    }

    @PostMapping("/create")
    public String createRentalAgreement(RentalAgreement rentalAgreement, @RequestParam("carId") int carId, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            rentalAgreementService.createRentalAgreement(rentalAgreement);
            carStatusService.updateCarStatus(carId, "Udlejet");
            return "redirect:/rentalAgreements/rented";
        }else {
            return "redirect:/";
        }

    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("contractId") int contractId, Model model, HttpSession session){
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            RentalAgreement rentalAgreement = rentalAgreementService.getRentalAgreement(contractId);
            model.addAttribute("rentalAgreement", rentalAgreement);
            return "/rentalAgreement/updateRentalAgreement";
        }else {
            return "redirect:/";
        }
    }

    @PostMapping("/update")
    public String updateRentalAgreement(@ModelAttribute ("rentalAgreement") RentalAgreement rentalAgreement, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            rentalAgreementService.updateRentalAgreement(rentalAgreement);
            return "redirect:/rentalAgreements/rented";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/delete")
    public String deleteRentalAgreement(@RequestParam("contractId") int contractId, @RequestParam("carId") int carId, HttpSession session){
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            rentalAgreementService.deleteRentalAgreement(contractId);
            carStatusService.updateCarStatus(carId, "Klar til udlejning");
            return "redirect:/rentalAgreements/rented";
        } else {
            return "redirect:/";
        }
    }
}

