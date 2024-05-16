package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class RentalAgreementService {

    private final RentalAgreementRepository rentalAgreementRepository;

    public RentalAgreementService(RentalAgreementRepository rentalAgreementRepository) {
        this.rentalAgreementRepository = rentalAgreementRepository;
    }

    public RentalAgreement createRentalAgreement(RentalAgreement rentalAgreement) {
        return rentalAgreementRepository.createRentalAgreement(rentalAgreement);
    }
    public List<RentalAgreement> getAllRentalAgreements() {
        return rentalAgreementRepository.getAllRentalAgreements();
    }

    public RentalAgreement getRentalAgreement(int rentalAgreementId) {
        return rentalAgreementRepository.getRentalAgreement(rentalAgreementId);
    }

    public RentalAgreement updateRentalAgreement(RentalAgreement rentalAgreement) {
        return rentalAgreementRepository.updateRentalAgreement(rentalAgreement);
    }

    public void deleteRentalAgreement(int rentalAgreementId) {
        rentalAgreementRepository.deleteRentalAgreement(rentalAgreementId);
    }
    public List<RentalAgreement> getAllRentedCars() {
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
}
