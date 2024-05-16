package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import com.example.projektbilabonnementgruppe4.viewModel.RentedCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class RentalAgreementService {

   @Autowired
   private RentalAgreementRepository rentalAgreementRepository;

    public void createRentalAgreement(RentalAgreement rentalAgreement) {
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

    public double getAverageOfMileageOfContracts(){
        double averageMileagePerContract = rentalAgreementRepository.getTotalMileageOfContracts() / getAllRentalAgreements().size();
        return averageMileagePerContract;
    }

}
