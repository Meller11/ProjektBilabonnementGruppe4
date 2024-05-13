package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.CarRepository;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    public void addCar(Car car) {
        System.out.println("service");
        carRepository.addCar(car);
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
}
