package com.example.projektbilabonnementgruppe4.controller;
import com.example.projektbilabonnementgruppe4.model.DamageReport;
import com.example.projektbilabonnementgruppe4.service.CarStatusService;
import com.example.projektbilabonnementgruppe4.viewModel.DamageReportWithCarAndRA;
import com.example.projektbilabonnementgruppe4.service.DamageReportService;
import com.example.projektbilabonnementgruppe4.service.RentalAgreementService;
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

    private final DamageReportService damageReportService;
    private final RentalAgreementService rentalAgreementService;
    private final CarStatusService carStatusService;

    @Autowired
    public DamageReportController(DamageReportService damageReportService, RentalAgreementService rentalAgreementService, CarStatusService carStatusService){
        this.damageReportService = damageReportService;
        this.rentalAgreementService = rentalAgreementService;
        this.carStatusService = carStatusService;
    }

    @GetMapping("/")
    public String damageReport(Model model) {
        List<DamageReportWithCarAndRA> damageReportNotDone = new ArrayList<>();
        List<DamageReportWithCarAndRA> damageReportDone = new ArrayList<>();
        List <Integer> rentalAgreementIDs = new ArrayList<>();
        List <Integer> damageReportIDs = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; rentalAgreementService.getAllRentalAgreements().size()>i;i++) {
        rentalAgreementIDs.add(rentalAgreementService.getAllRentalAgreements().get(i).getContractId());
        }
        for (int i = 0; damageReportService.listOfAllDamageReports().size()>i;i++) {
            damageReportIDs.add(damageReportService.listOfAllDamageReports().get(i).getContractId());
        }
        for (int rentalAgreementID : rentalAgreementIDs) {
            if (!damageReportIDs.contains(rentalAgreementID)) {
                DamageReport newDamageReport = new DamageReport(rentalAgreementID, false, false, false, 0, currentDate, false);
                damageReportService.createDamageReport(newDamageReport);
            }
        }
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
    }



    @GetMapping("/FinalizeReport/{contract_id}")
    public String finalizeDamageReport(@ModelAttribute("damageReport") DamageReport updateDamageReport, @PathVariable int contract_id, Model model){
        DamageReportWithCarAndRA finalizeDamageReport = damageReportService.damageReportByID(contract_id);
        model.addAttribute("finalizeDamageReport", finalizeDamageReport);
        return "damageReport/finalizeDamageReport";
    }

    @PostMapping("/finalizeReport/{contract_id}")
    public String finalizeDamageReport(@ModelAttribute("damageReport") DamageReport updateDamageReport, @PathVariable int contract_id){
        damageReportService.updateDamageReport(updateDamageReport, contract_id);
        carStatusService.updateCarStatus(damageReportService.damageReportByID(contract_id).getCarId(), "Klar Til Salg");
        return "redirect:/damageReport/FinalReport/{contract_id}";
    }

    @PostMapping("/deleteDamageReport/{contract_id}")
    public String deleteDamageReport(@PathVariable int contract_id){
        carStatusService.updateCarStatus(damageReportService.damageReportByID(contract_id).getCarId(), "Udlejet");
        damageReportService.deleteDamageReport(contract_id);
        return "redirect:/damageReport/";
    }

    @GetMapping("/FinalReport/{contract_id}")
    public String finalDamageReport(@PathVariable int contract_id, Model model){
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
    }

}
