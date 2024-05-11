package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addCar(Car car) {
        String sql = "INSERT INTO car (frame_number, brand, model, colour, gear_type, price, registration_fee, emission, acquisition_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getFrameNumber(), car.getBrand(), car.getModel(),
                car.getColour(), car.getGearType(), car.getPrice(), car.getRegistrationFee(),
                car.getEmission(), car.getAcquisitionDate());
    }

}
