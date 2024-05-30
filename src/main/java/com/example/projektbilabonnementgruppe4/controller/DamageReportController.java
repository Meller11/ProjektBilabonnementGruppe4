package com.example.projektbilabonnementgruppe4.controller;
import com.example.projektbilabonnementgruppe4.model.DamageReport;
import com.example.projektbilabonnementgruppe4.model.Employee;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.service.CarStatusService;
import com.example.projektbilabonnementgruppe4.repository.viewModel.DamageReportWithCarAndRA;
import com.example.projektbilabonnementgruppe4.service.DamageReportService;
import com.example.projektbilabonnementgruppe4.service.RentalAgreementService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("damageReport")
public class DamageReportController {

    @Autowired
    private  DamageReportService damageReportService;
    @Autowired
    private  RentalAgreementService rentalAgreementService;
    @Autowired
    private  CarStatusService carStatusService;

    // Forside for damageReport, finder og indlæser alle kontrakter der er klar til skadesrapporter
    // samt opretter ny skadesrapport hvis der ikke er en for en kontrakt.

    @GetMapping("/")
    public String damageReport(Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null){
        List<DamageReportWithCarAndRA> damageReportNotDone = new ArrayList<>();
        List<DamageReportWithCarAndRA> damageReportDone = new ArrayList<>();
        List <Integer> rentalAgreementIDs = new ArrayList<>();
        List <Integer> damageReportIDs = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();


        // Opretter lister med ID'er for alle lejekontrakter og alle skaderapporter.
        for (int i = 0; rentalAgreementService.getAllRentalAgreements().size()>i;i++) {
        rentalAgreementIDs.add(rentalAgreementService.getAllRentalAgreements().get(i).getContractId());
        }
        for (int i = 0; damageReportService.listOfAllDamageReports().size()>i;i++) {
            damageReportIDs.add(damageReportService.listOfAllDamageReports().get(i).getContractId());
        }

        // Tjekker om der er skadesrapporter der ikke findes ved at refere ID'erne overfor hinanden, hvis ikke, bliver der oprettet en ny skadesrapport med det ID der mangler.
        for (int i = 0; rentalAgreementService.getAllRentalAgreements().size() > i; i++) {
            RentalAgreement rentalAgreement = rentalAgreementService.getRentalAgreement(rentalAgreementService.getAllRentalAgreements().get(i).getContractId());
            if (!damageReportIDs.contains(rentalAgreement.getContractId())) {
                DamageReport newDamageReport = new DamageReport(rentalAgreement.getContractId(), false, false, false, 0, rentalAgreement.getContractEndDate(), false);
                damageReportService.createDamageReport(newDamageReport);
            }
        }

        // Sætter det ind på hjemmesiden i forhold til dags dato og om den er færdig eller ej.
        List<DamageReportWithCarAndRA> damageReportOverview = damageReportService.damageReportOverview();
        for (int i = 0; damageReportOverview.size() > i; i++) {
            if (damageReportOverview.get(i).getReportDate().compareTo(currentDate) <= 0 && !damageReportOverview.get(i).isDamageReportDone()) {
                damageReportNotDone.add(damageReportOverview.get(i));
            } else if (damageReportOverview.get(i).isDamageReportDone()) {
                damageReportDone.add(damageReportOverview.get(i));
            }
        }
        model.addAttribute("damageReportNotDone", damageReportNotDone);
        model.addAttribute("damageReportDone", damageReportDone);
        return "damageReport/damageReport";
    } else {
            return "redirect:/";
    }
    }

    //Sletning af skaderapport, opretter en nye skadesrapport efter genindlæsning af /damageReport
    //Sætter også bilstatus tilbage til "udlejet"

    @PostMapping("/deleteDamageReport/{contract_id}")
    public String deleteDamageReport(@PathVariable int contract_id, HttpSession session){
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null){
        carStatusService.updateCarStatus(damageReportService.damageReportByID(contract_id).getCarId(), "Udlejet");
        damageReportService.deleteDamageReport(contract_id);
        return "redirect:/damageReport/";
    } else {
        return "redirect:/";
        }
    }

    //Visning til færdiggøring af skade rapport.

    @GetMapping("/finalizeReport/{contract_id}")
    public String finalizeDamageReport(@ModelAttribute("damageReport") DamageReport updateDamageReport, @PathVariable int contract_id, Model model, HttpSession session){
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null){
        DamageReportWithCarAndRA finalizeDamageReport = damageReportService.damageReportByID(contract_id);
        model.addAttribute("finalizeDamageReport", finalizeDamageReport);
        return "damageReport/finalizeDamageReport";
        } else {
        return "redirect:/";
        }
    }

    //Indsendelse af skade rapport samt opdatering af bil status til "Klar Til Salg"
    @PostMapping("/finalizeReport/{contract_id}")
    public String finalizeDamageReport(@ModelAttribute("damageReport") DamageReport updateDamageReport, @PathVariable int contract_id, HttpSession session){
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null){
        damageReportService.updateDamageReport(updateDamageReport, contract_id);
        carStatusService.updateCarStatus(damageReportService.damageReportByID(contract_id).getCarId(), "Klar Til Salg");
        return "redirect:/damageReport/finalReport/{contract_id}";
        } else {
        return "redirect:/";
        }
    }

    //Visning af færdiggjort skade rapport samt udregning af skade + overkørte kilometer.
    @GetMapping("/finalReport/{contract_id}")
    public String finalDamageReport(@PathVariable int contract_id, Model model, HttpSession session){
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null){
        DamageReportWithCarAndRA finalDamageReport = damageReportService.damageReportByID(contract_id);
        int priceForDamages = 0;
        if (finalDamageReport.isWindshieldDamage()){
           priceForDamages += 3000;
        }
        if (finalDamageReport.isPaintDamage()){
            priceForDamages += 1500;
        }
        if (finalDamageReport.isRimDamage()){
            priceForDamages += 400;
        }

        int mileageOfContract = rentalAgreementService.getTotalMilesPerContract(contract_id) - damageReportService.damageReportByID(contract_id).getMileage();
        double priceOfTooManyMiles;
        if (mileageOfContract >= 0){
            priceOfTooManyMiles = 0;
        } else {
            priceOfTooManyMiles = -((rentalAgreementService.getTotalMilesPerContract(contract_id) - damageReportService.damageReportByID(contract_id).getMileage())*0.75);
        }
        double totalPriceOfContract = priceForDamages + priceOfTooManyMiles;
        model.addAttribute("priceForMileageTotal", priceOfTooManyMiles);
        model.addAttribute("totalPriceOfContract", totalPriceOfContract);
        model.addAttribute("priceForDamages", priceForDamages);
        model.addAttribute("finalDamageReport", finalDamageReport);
        return "damageReport/finalDamageReport";
        } else {
        return "redirect:/";
        }
    }

}
