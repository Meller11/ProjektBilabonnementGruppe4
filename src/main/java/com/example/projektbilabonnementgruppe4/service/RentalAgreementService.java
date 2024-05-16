package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import com.example.projektbilabonnementgruppe4.viewModel.RentedCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalAgreementService {

   @Autowired
   private RentalAgreementRepository rentalAgreementRepository;

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
    public List<RentedCar> getAllRentedCars() {
        return rentalAgreementRepository.getAllRentedCars();
    }
}
