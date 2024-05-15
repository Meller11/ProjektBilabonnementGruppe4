package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.EmployeeModel;
import com.example.projektbilabonnementgruppe4.service.CarService;
import com.example.projektbilabonnementgruppe4.service.RentalAgreementService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FinanceController {

    private final CarService carService;
    private final RentalAgreementService rentalAgreementService;

    @Autowired
    public FinanceController(CarService carService, RentalAgreementService rentalAgreementService) {
        this.carService = carService;
        this.rentalAgreementService = rentalAgreementService;
    }


    @GetMapping("/finance/")
    public String financeOverview(HttpSession session, Model model){
        EmployeeModel loggedInUser = (EmployeeModel) session.getAttribute("loggedInUser");
        if (loggedInUser != null){
            session.setAttribute("loggedInUser", loggedInUser);
            List<Car> cars = carService.getAllUnrentedCars();
            model.addAttribute("user", loggedInUser);
            model.addAttribute("cars", cars);
            return "/finance/financeOverview";
        } else {
            return "redirect:/";
        }
    }

}
