package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.CarStatus;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalAgreementService {

    @Autowired
    private CarService carService;

    private final RentalAgreementRepository rentalAgreementRepository;

    public RentalAgreementService(RentalAgreementRepository rentalAgreementRepository) {
        this.rentalAgreementRepository = rentalAgreementRepository;
    }

    public RentalAgreement createRentalAgreement(RentalAgreement rentalAgreement) {
        RentalAgreement createdRentalAgreement = rentalAgreementRepository.createRentalAgreement(rentalAgreement);

        CarStatus carStatus = new CarStatus();
        carStatus.setCarId(createdRentalAgreement.getCar().getCarId());
        carStatus.setCarStatusType("Udlejet");
        carStatus.setStatusDate(LocalDate.now());

        carService.updateCarStatus(carStatus);

        return createdRentalAgreement;
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
        RentalAgreement rentalAgreement = rentalAgreementRepository.getRentalAgreement(rentalAgreementId);
        if (rentalAgreement != null) {
            Car car = rentalAgreement.getCar();
            if (car != null) {
                CarStatus carStatus = car.getCarStatus();
                if (carStatus != null) {
                    carStatus.setCarStatusType("Klar til udlejning");
                    carStatus.setStatusDate(LocalDate.now());
                    carService.updateCarStatus(carStatus);
                }
            }
        }
        rentalAgreementRepository.deleteRentalAgreement(rentalAgreementId);
    }
    public List<RentalAgreement> getAllRentedCars() {
        return rentalAgreementRepository.getAllRentedCars();
    }
}
