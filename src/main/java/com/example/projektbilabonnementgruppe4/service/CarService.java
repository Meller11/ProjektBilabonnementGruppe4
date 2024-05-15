package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.CarStatus;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.CarRepository;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    /*public void addCar(Car car) {
        carRepository.addCar(car);

    }*/
    public void addCar(Car car) {
        carRepository.addCar(car);
        Car foundCar = carRepository.getCarByFrameNumber(car.getFrameNumber());
        this.addCarStatus(foundCar.getCarId(), "Klar til udlejning");
    }
    public void addCarStatus(Integer carId, String status) {
        carRepository.addCarStatus(carId, status, LocalDate.now());
    }
    /*public void addCarStatus(Integer carId) {
        carRepository.addCarStatus(carId, "Klar til udlejning", LocalDate.now());
    }*/

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
            if (rentalAgreement.getCar() != null) {
                allCars.removeIf(car -> car.getCarId() == rentalAgreement.getCar().getCarId());
            }
        }
            return allCars;
        }
        public Car getCarById ( int carId){
            return carRepository.getCarById(carId);
        }
        public void updateCarStatus (CarStatus carStatus){
            carRepository.updateCarStatus(carStatus);
        }
    }
