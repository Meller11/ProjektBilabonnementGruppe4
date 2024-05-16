package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.CarRepository;
import com.example.projektbilabonnementgruppe4.repository.CarStatusRepository;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import com.example.projektbilabonnementgruppe4.viewModel.CarWithStatus;
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

    public void updateCar(Car car) {
        carRepository.updateCar(car);
    }

    public void deleteCarById(Integer carId) {
        carRepository.deleteCarById(carId);
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
        List<RentalAgreement> rentedCars = rentalAgreementRepository.getAllRentedCars();

        for (RentalAgreement rentalAgreement : rentedCars) {
            allCars.removeIf(car -> car.getCarId() == rentalAgreement.getCarId());
        }

        return allCars;
    }

    public List<CarWithStatus> getAllCarsWithStatus() {
        return carRepository.getAllCarsWithStatus();
    }
}
