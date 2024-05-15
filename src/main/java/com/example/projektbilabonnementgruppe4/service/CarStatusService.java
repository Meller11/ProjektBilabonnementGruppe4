package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.repository.CarStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CarStatusService {


    @Autowired
    private CarStatusRepository carStatusRepository;

    public void createCarStatus(Integer carId) {
        carStatusRepository.createCarStatus(carId, "Klar til udlejning", LocalDate.now());
    }
    public void updateCarStatus(Integer carId) {
        carStatusRepository.updateCarStatus(carId, "Udlejet", LocalDate.now());
    }
}

