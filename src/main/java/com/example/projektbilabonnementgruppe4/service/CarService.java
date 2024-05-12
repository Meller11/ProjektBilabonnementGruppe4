package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.CarStatus;
import com.example.projektbilabonnementgruppe4.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

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


}
