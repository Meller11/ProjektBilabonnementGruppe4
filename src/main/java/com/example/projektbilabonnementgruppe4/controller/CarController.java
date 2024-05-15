package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.EmployeeModel;
import com.example.projektbilabonnementgruppe4.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/create")
    public String showCreateCarForm(Model model, HttpSession session) {
        EmployeeModel loggedInUser = (EmployeeModel) session.getAttribute("loggedInUser");

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
        carService.createCarStatus(foundCar.getCarId());
        return "redirect:/car/allCarsWithStatus";
    }

    @GetMapping("/allCarsWithStatus")
    public String showAllCarsWithStatus(Model model, HttpSession session) {
        EmployeeModel loggedInUser = (EmployeeModel) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("carsWithStatus", carService.getAllCarsWithStatus());
            return "car/allCarsWithStatus";
        } else {
            return "redirect:/";
        }
    }

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

    @GetMapping("/unrented")
    public String showAllUnrentedCars(Model model, HttpSession session) {
        EmployeeModel loggedInUser = (EmployeeModel) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("unrentedCars", carService.getAllUnrentedCars());
            return "car/unrentedCars";
        } else {
            return "redirect:/";
        }
    }
}
