package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.Employee;
import com.example.projektbilabonnementgruppe4.service.CarService;
import com.example.projektbilabonnementgruppe4.service.CarStatusService;
import com.example.projektbilabonnementgruppe4.viewModel.CarWithStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/* Klassen er skrevet af Lasse Fosgaard, med undtagelse af få linjer.
   Klassen styrer visninger der har med bilerne i systemet at gøre, såsom opret/rediger/slet bil
   smat forskellige visninger af biler (liste af alle biler i flåden f.eks.) */

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarStatusService carStatusService;

    //Viser formular for oprettelse af bil
    @GetMapping("/create")
    public String showCreateCarForm(Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("car", new Car());
            return "car/createCar";
        } else {
            return "redirect:/";
        }
    }

    //Behandler formularen for oprettelse af bil og kalder carStatusService for samtidig at oprette en status for bilen.
    @PostMapping("/create")
    public String createCar(Car car) {
        carService.createCar(car);
        Car foundCar = carService.getCarByFrameNumber(car.getFrameNumber());
        carStatusService.createCarStatus(foundCar.getCarId());
        return "redirect:allCarsWithStatus";
    }

    //Viser formular for redigering af bil
    @GetMapping("/showEditCarForm")
    public String showEditCarForm(@RequestParam("frameNumber") String frameNumber, Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Car car = carService.getCarByFrameNumber(frameNumber);
            model.addAttribute("car", car);
            return "car/updateCar";
        } else {
            return "redirect:/";
        }
    }

    //Behandler formularen for redigering af bil
    @PostMapping("/updateCar")
    public String updateCar(@ModelAttribute("car") Car car) {
        carService.updateCar(car);
        return "redirect:allCarsWithStatus";
    }

    //Behandler en forespørgsel om at slette en bil
    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("frameNumber") String frameNumber) {
        Car foundCar = carService.getCarByFrameNumber(frameNumber);
        carService.deleteCarById(foundCar.getCarId());
        return "redirect:allCarsWithStatus";
    }

    /*Visning for alle biler i databasen, samt deres status. Visningen returnerer CarWithStatus objekter (viewModel),
    som er en kombination af Car og CarStatus informationer */
    @GetMapping("/allCarsWithStatus")
    public String showAllCarsWithStatus(Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("carsWithStatus", carService.getAllCarsWithStatus());
            return "car/allCarsWithStatus";
        } else {
            return "redirect:/";
        }
    }

    // Viser alle ikke udlejede biler, henter bilerne igennem service layer og adder dem til modellen.
    @GetMapping("/unrented")
    public String showAllUnrentedCars(Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("carsWithStatus", carService.getAllCarsWithStatus());
            return "car/unrentedCars";
        } else {
            return "redirect:/";
        }
    }

    // Viser en liste af biler (søgeresultater) ud fra den indtastede query.
    @GetMapping("/search")
    public String searchCars(@RequestParam("query") String query, Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            List<CarWithStatus> searchResults = carService.searchCarsWithStatus(query);
            model.addAttribute("searchResults", searchResults);
            return "searchResults";
        } else {
            return "redirect:/";
        }
    }

}
