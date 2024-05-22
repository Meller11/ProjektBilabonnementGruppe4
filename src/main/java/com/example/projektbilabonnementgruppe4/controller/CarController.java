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

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarStatusService carStatusService;

    @GetMapping("/create")
    public String showCreateCarForm(Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("car", new Car());
            return "/car/createCar";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/create")
    public String createCar(Car car) {
        carService.createCar(car);
        Car foundCar = carService.getCarByFrameNumber(car.getFrameNumber());
        carStatusService.createCarStatus(foundCar.getCarId());
        return "redirect:allCarsWithStatus";
    }

    @GetMapping("/showEditCarForm")
    public String showEditCarForm(@RequestParam("frameNumber") String frameNumber, Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Car car = carService.getCarByFrameNumber(frameNumber);
            model.addAttribute("car", car);
            return "/car/updateCar";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updateCar")
    public String updateCar(@ModelAttribute("car") Car car) {
        carService.updateCar(car);
        return "redirect:/cars/allCarsWithStatus";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("frameNumber") String frameNumber) {
        Car foundCar = carService.getCarByFrameNumber(frameNumber);
        carService.deleteCarById(foundCar.getCarId());
        return "redirect:/cars/allCarsWithStatus";
    }

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

    // Viser alle biler, henter bilerne igennem service layer og adder dem til modellen.
    @GetMapping("/all")
    public String showAllCars(Model model, HttpSession session) {
        EmployeeModel loggedInUser = (EmployeeModel) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("cars", carService.getAllCars());
            return "car/allCars";
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

    @GetMapping("/search")
    public String searchCars(@RequestParam("query") String query, Model model) {
        List<CarWithStatus> searchResults = carService.searchCarsWithStatus(query);
        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }

}
