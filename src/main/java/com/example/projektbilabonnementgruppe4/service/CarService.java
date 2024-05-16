package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.CarRepository;
import com.example.projektbilabonnementgruppe4.repository.CarStatusRepository;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import com.example.projektbilabonnementgruppe4.viewModel.CarWithStatus;
import com.example.projektbilabonnementgruppe4.viewModel.RentedCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarStatusRepository carStatusRepository;

    @Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    public void createCar(Car car) {
        carRepository.createCar(car);
    }

    public Car getCarByFrameNumber(String frameNumber) {
        try {
            return carRepository.getCarByFrameNumber(frameNumber);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Ingen bil fundet med stelnummer: " + frameNumber);
        }
    }

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public List<Car> getAllUnrentedCars() {
        List<Car> allCars = carRepository.getAllCars();
        List<RentalAgreement> rentedCars = rentalAgreementRepository.getAllRentalAgreements();

        for (RentalAgreement rentalAgreement : rentedCars) {
            allCars.removeIf(car -> car.getCarId().equals(rentalAgreement.getCarId()));
        }

        return allCars;
    }

    public List<CarWithStatus> getAllCarsWithStatus() {
        return carRepository.getAllCarsWithStatus();
    }
}
