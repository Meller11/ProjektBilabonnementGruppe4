package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/create")
    public String showCreateCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "/car/createCar";
    }

    @PostMapping("/create")
    public String createCar(Car car) {
        carService.addCar(car);
        Car foundCar = carService.getCarByFrameNumber(car.getFrameNumber());
        carService.addCarStatus(foundCar.getCarId());
        return "redirect:/car/allCarsWithStatus";
    }

    @GetMapping("/allCarsWithStatus")
    public String showAllCarsWithStatus(Model model) {
        model.addAttribute("carsWithStatus", carService.getAllCarsWithStatus());
        return "car/allCarsWithStatus";
    }

    @GetMapping("/all")
    public String showAllCars(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "car/allCars";
    }
    @GetMapping("/unrented")
    public String showAllUnrentedCars(Model model) {
        List<Car> unrentedCars = carService.getAllUnrentedCars();
        model.addAttribute("unrentedCars", unrentedCars);
        return "car/unrentedCars";
    }
}
