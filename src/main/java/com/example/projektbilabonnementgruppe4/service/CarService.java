package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.CarStatus;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.CarRepository;
import com.example.projektbilabonnementgruppe4.repository.CarStatusRepository;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.*;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarStatusRepository carStatusRepository;

    @Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    public void addCar(Car car) {
        carRepository.addCar(car);

    }

    public void addCarStatus(Integer carId) {
        carRepository.addCarStatus(carId, "Klar til udlejning", LocalDate.now());
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

    public List<Map<String, Object>> getAllCarsWithStatus() {
        List<Car> cars = carRepository.getAllCars();
        List<CarStatus> statuses = carStatusRepository.getAllCarStatuses();
        List<Map<String, Object>> carsWithStatus = new ArrayList<>();

        Map<Integer, String> statusMap = new HashMap<>();
        for (CarStatus status : statuses) {
            statusMap.put(status.getCarId(), status.getCarStatusType());
        }

        for (Car car : cars) {
            Map<String, Object> carInfo = new HashMap<>();
            carInfo.put("car", car);
            carInfo.put("status", statusMap.get(car.getCarId()));
            carsWithStatus.add(carInfo);
        }
        return carsWithStatus;
    }
}
