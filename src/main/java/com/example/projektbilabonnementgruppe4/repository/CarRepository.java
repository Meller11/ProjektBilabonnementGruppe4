package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.time.LocalDate;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createCar(Car car) {
        String sql = "INSERT INTO car (frame_number, brand, model, colour, gear_type, fuel_type, price, registration_fee, emission, acquisition_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getFrameNumber(), car.getBrand(), car.getModel(),
                car.getColour(), car.getGearType(), car.getFuelType(), car.getPrice(), car.getRegistrationFee(),
                car.getEmission(), car.getAcquisitionDate());
    }

    public Car getCarByFrameNumber(String frameNumber) {
        String sql = "SELECT * FROM car WHERE frame_number = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{frameNumber}, new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getAllCars() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class));
    }
}
