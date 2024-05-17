package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.EmployeeModel;
import com.example.projektbilabonnementgruppe4.service.CarService;
import com.example.projektbilabonnementgruppe4.service.RentalAgreementService;
import com.example.projektbilabonnementgruppe4.viewModel.CarWithStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("finance")
public class FinanceController {

    private final CarService carService;
    private final RentalAgreementService rentalAgreementService;

    @Autowired
    public FinanceController(CarService carService, RentalAgreementService rentalAgreementService) {
        this.carService = carService;
        this.rentalAgreementService = rentalAgreementService;
    }


    @GetMapping("/")
    public String financeOverview(HttpSession session, Model model){
        EmployeeModel loggedInUser = (EmployeeModel) session.getAttribute("loggedInUser");
        //if (loggedInUser != null){
            session.setAttribute("loggedInUser", loggedInUser);
            List<CarWithStatus> carWithStatus = carService.getAllCarsWithStatus();
            model.addAttribute("user", loggedInUser);
            model.addAttribute("cars", carWithStatus);
            model.addAttribute("averageContractMonthlyFee", rentalAgreementService.getAverageMonthlyFee());
            model.addAttribute("averageCostOfCars", carService.getAveragePriceOfAllCars());
            model.addAttribute("totalPriceOfAllCars", carService.getTotalPriceOfAllCars());
            model.addAttribute("averageMonthsLeft", rentalAgreementService.getDifferenceInMonthsAverageRemaining());
            model.addAttribute("averageMonthsTotal", rentalAgreementService.getDifferenceInMonthsAverageTotal());
            model.addAttribute("averageMilagePerContract", rentalAgreementService.getAverageOfMileageOfContracts());
            model.addAttribute("totalPriceOfMileageFromToday", rentalAgreementService.getTotalPriceOfAllMileageFromCurrentDateToEndDateOfContracts());
            model.addAttribute("totalPriceOfAllContractsMilage", rentalAgreementService.totalPriceOfAllMileageInAllContracts());
            return "/finance/financeOverview";
       // } else {
       //     return "redirect:/";
       // }
    }

}
