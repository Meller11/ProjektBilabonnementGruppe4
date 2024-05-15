package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.CarStatus;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.service.CarService;
import com.example.projektbilabonnementgruppe4.service.RentalAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RentalAgreementController {

    @Autowired
    private CarService carService;

    @Autowired
    RentalAgreementService rentalAgreementService;;

    //Consturctor injection
    /*private final RentalAgreementService rentalAgreementService;

    public RentalAgreementController(RentalAgreementService rentalAgreementService) {
        this.rentalAgreementService = rentalAgreementService;
    }*/


    @GetMapping("/rentalAgreements")
    public String showAllForm(Model model) {
        List<RentalAgreement> rentalAgreements = rentalAgreementService.getAllRentalAgreements();
        model.addAttribute("rentalAgreements", rentalAgreements);
        List<Car> allCars = carService.getAllCars();
        model.addAttribute("allCars", allCars);
        return "showAllRentalAgreements";
    }
    @GetMapping("/rentalAgreements/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        RentalAgreement rentalAgreement = rentalAgreementService.getRentalAgreement(id);
        model.addAttribute("rentalAgreement", rentalAgreement);
        model.addAttribute("allCars", carService.getAllCars());
        return "updateRentalAgreement";
    }
    @PostMapping("/rentalAgreements/update")
    public String updateRentalAgreement(@ModelAttribute RentalAgreement rentalAgreement) {
        rentalAgreementService.updateRentalAgreement(rentalAgreement);
        return "redirect:/rentalAgreements";
    }
    /*@PostMapping("/rentalAgreements/update/{id}")
    public String updateRentalAgreement(@PathVariable("id") int id, RentalAgreement rentalAgreement, @RequestParam("carId") int carId) {
        Car car = carService.getCarById(carId);
        if (car == null) {
            // returnerer en fejlmeddelelse eller kaster en exception
            throw new IllegalArgumentException("Ingen bil fundet med id: " + carId);
        }
        if (rentalAgreement.getContractNumber() == null) {
            throw new IllegalArgumentException("Kontrakt nummer kan ikke være null");
        }
        rentalAgreement.setCar(car);
        rentalAgreement.setContractId(id);
        rentalAgreementService.updateRentalAgreement(rentalAgreement);
        return "redirect:/rentalAgreements";
    }*/
    @PostMapping("/rentalAgreements/delete/{id}")
    public String deleteRentalAgreement(@PathVariable("id") int id) {
        rentalAgreementService.deleteRentalAgreement(id);
        return "redirect:/rentalAgreements";
    }

    @GetMapping("/createRentalAgreement")
    public String showCreateForm(Model model) {
        model.addAttribute("rentalAgreement", new RentalAgreement());
        List<Car> allCars = carService.getAllCars();
        model.addAttribute("allCars", allCars);
        return "createRentalAgreement";
    }
    @PostMapping("/rentalAgreements")
    public String createRentalAgreement(RentalAgreement rentalAgreement, @RequestParam("carId") int carId, RedirectAttributes redirectAttributes) {
        Car car = carService.getCarById(carId);
        if (car == null) {
            // returnerer en fejlmeddelelse eller kaster en exception
            redirectAttributes.addFlashAttribute("errorMessage", "No car found with id: " + carId);
            return "redirect:/createRentalAgreement";
        }
        rentalAgreement.setCar(car);
        rentalAgreementService.createRentalAgreement(rentalAgreement);

        // Opdater bilens status til "udlejet"
        CarStatus carStatus = new CarStatus();
        carStatus.setCarId(car.getCarId());
        carStatus.setCarStatusType("Udlejet");
        carStatus.setStatusDate(LocalDate.now());
        carService.updateCarStatus(carStatus);

        return "redirect:/rentalAgreements";
    }
    @GetMapping("/rented")
    public String showRentedCars(Model model) {
        List<RentalAgreement> rentedCars = rentalAgreementService.getAllRentedCars();
        //rentedCars.removeIf(rentalAgreement -> rentalAgreement.getCar() == null || !rentalAgreement.getCar().getCarStatus().getCarStatusType().equals("Udlejet"));
        model.addAttribute("rentedCars", rentedCars);
        return "car/rentedCars";
    }
    @GetMapping("/unrented")
    public String showAllUnrentedCars(Model model) {
        List<Car> unrentedCars = carService.getAllUnrentedCars();
        model.addAttribute("unrentedCars", unrentedCars);
        return "car/unrentedCars";
    }
}

