package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import com.example.projektbilabonnementgruppe4.repository.viewModel.RentedCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class RentalAgreementService {

   @Autowired
   private RentalAgreementRepository rentalAgreementRepository;

    public void  createRentalAgreement(RentalAgreement rentalAgreement) {
         rentalAgreementRepository.createRentalAgreement(rentalAgreement);
    }

    public List<RentalAgreement> getAllRentalAgreements() {
        return rentalAgreementRepository.getAllRentalAgreements();
    }

    public RentalAgreement getRentalAgreement(int rentalAgreementId) {
        return rentalAgreementRepository.getRentalAgreement(rentalAgreementId);
    }

    public void updateRentalAgreement(RentalAgreement rentalAgreement) {
        rentalAgreementRepository.updateRentalAgreement(rentalAgreement);
    }

    public void deleteRentalAgreement(int rentalAgreementId) {
        rentalAgreementRepository.deleteRentalAgreement(rentalAgreementId);
    }
    public List<RentedCar> getAllRentedCars() {
        return rentalAgreementRepository.getAllRentedCars();
    }

    public int getAverageMonthlyFee(){
        return rentalAgreementRepository.getAverageMonthlyFee();
    }

    // Total forskel fra start dato til slut dato i en specifik kontrakt.

    public int getDifferenceInMonthsForContractID(int contractID){
        Period period = Period.between(getRentalAgreement(contractID).getContractStartDate(), getRentalAgreement(contractID).getContractEndDate());
        int totalYears = 0;
        int totalMonths = 0;
        totalYears += period.getYears();
        totalMonths += (totalYears*12);
        totalMonths += period.getMonths();
        return totalMonths;
    }

    // Udregner den gennemsnitlige tid for alle leje kontrakter der er tilbage i kontrakterne.

    public int getDifferenceInMonthsAverageRemaining(){
        LocalDate today = LocalDate.now();
        Period period = null;
        int totalMonths = 0;
        int totalYears = 0;
        int monthDifferenceAverage= 0;
        for (int i = 0; getAllRentalAgreements().size()>i; i++){
            period = Period.between(today, getAllRentalAgreements().get(i).getContractEndDate());
            totalYears += period.getYears();
            totalMonths += (totalYears*12);
            totalMonths += period.getMonths();
        }
        monthDifferenceAverage = totalMonths / getAllRentalAgreements().size();
        return monthDifferenceAverage;
    }

    // Udregner den totale gennemsnitlige tid alle kontrakter er oprettet med.
    public int getDifferenceInMonthsAverageTotal(){
        Period period = null;
        int totalMonths = 0;
        int totalYears = 0;
        int monthDifferenceAverage= 0;
        for (int i = 0; getAllRentalAgreements().size()>i; i++){
            period = Period.between(getAllRentalAgreements().get(i).getContractStartDate(), getAllRentalAgreements().get(i).getContractEndDate());
            totalYears += period.getYears();
            totalMonths += (totalYears*12);
            totalMonths += period.getMonths();
        }
        monthDifferenceAverage = totalMonths / getAllRentalAgreements().size();
        return monthDifferenceAverage;
    }

    // Udregner gennemsnit af kilometer tilkøb i lejekontrakter.
    public double getAverageOfMileageOfContracts(){
        double averageMileagePerContract = rentalAgreementRepository.getTotalMileageOfContracts() / getAllRentalAgreements().size();
        return averageMileagePerContract;
    }
    // Udregner total kilometer for en kontrakt.

    public int getTotalMilesPerContract(int contractID){
        int totalMileageForContract = 0;
        switch ((int) rentalAgreementRepository.getMileageOfContract(contractID)) {
            case 1500 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 1500;
            case 1750 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 1750;
            case 2000 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 2000;
            case 2500 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 2500;
            case 3000 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 3000;
            case 3500 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 3500;
            case 4000 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 4000;
            case 4500 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 4500;
            default -> {
            }
        }
        return totalMileageForContract;
    }
    // Udregner den totale tilkøbte pris af en kontrakts kilometer.

    public double getTotalPriceOfMileageInContract(int contractID){
        double totalMileageForContract = 0;
        switch ((int) rentalAgreementRepository.getMileageOfContract(contractID)) {
                case 1750 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 300;
                case 2000 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 590;
                case 2500 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 1160;
                case 3000 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 1710;
                case 3500 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 2240;
                case 4000 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 2750;
                case 4500 -> totalMileageForContract = getDifferenceInMonthsForContractID(contractID) * 3240;
                default -> {
                }
            }
            return totalMileageForContract;
        }

        // Total pris af alle ekstra kilometer købt i kontrakter.

    public double totalPriceOfAllMileageInAllContracts(){
        double totalPrice = 0;
        for (int i = 0; getAllRentalAgreements().size()>i; i++){
            totalPrice += getTotalMilesPerContract(getAllRentalAgreements().get(i).getContractId());
        }
        return totalPrice;
    }

    //Udregner den totale mængde måneder tilbage af en kontrakt fra dags dato.

    public int getMonthsRemainingOnContract(int contractID){
        LocalDate today = LocalDate.now();
        RentalAgreement rentalAgreement = getRentalAgreement(contractID);
        Period period = null;
        int monthsRemaining = 0;
        int totalYears = 0;
        period = Period.between(today, rentalAgreement.getContractEndDate());
        totalYears += period.getYears();
        monthsRemaining += (totalYears*12);
        monthsRemaining += period.getMonths();
        return monthsRemaining;
    }

    // Får den totale pris fra dags dato til slut dato af kontrakt, på de ekstra tilkøb af kilometer en kontrakt potentielt kunne have.
    public double getTotalPriceOfAllMileageFromCurrentDateToEndDateOfContracts(){
        double totalPrice = 0;
        for (int i = 0; getAllRentalAgreements().size()>i; i++){
            int contractID = getAllRentalAgreements().get(i).getContractId();
            switch ((int)getAllRentalAgreements().get(i).getMileagePerMonth()){
                case 1750:
                    totalPrice += getMonthsRemainingOnContract(contractID) * 300;
                    break;
                case 2000:
                    totalPrice += getMonthsRemainingOnContract(contractID) * 590;
                    break;
                case 2500:
                    totalPrice += getMonthsRemainingOnContract(contractID) * 1160;
                    break;
                case 3000:
                    totalPrice += getMonthsRemainingOnContract(contractID) * 1710;
                    break;
                case 3500:
                    totalPrice += getMonthsRemainingOnContract(contractID) * 2240;
                    break;
                case 4000:
                    totalPrice += getMonthsRemainingOnContract(contractID) * 2750;
                    break;
                case 4500:
                    totalPrice += getMonthsRemainingOnContract(contractID) * 3240;
                    break;
                default: totalPrice += 0;
                    break;

            }
        }
        return totalPrice;
    }

}