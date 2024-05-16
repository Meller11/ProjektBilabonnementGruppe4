package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.viewModel.CarWithStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void updateCar(Car car) {
        String sql = "UPDATE car SET frame_number = ?, brand = ?, model = ?, colour = ?, gear_type = ?, fuel_type = ?, price = ?, registration_fee = ?, emission = ?, acquisition_date = ? WHERE car_id = ?";
        jdbcTemplate.update(sql, car.getFrameNumber(), car.getBrand(), car.getModel(), car.getColour(), car.getGearType(), car.getFuelType(), car.getPrice(), car.getRegistrationFee(), car.getEmission(), car.getAcquisitionDate(), car.getCarId());
    }

    public void deleteCarById(Integer carId) {
        String sql = "DELETE FROM car WHERE car_id = ?";
        jdbcTemplate.update(sql, carId);
    }

    public Car getCarByFrameNumber(String frameNumber) {
        String sql = "SELECT * FROM car WHERE frame_number = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{frameNumber}, new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getAllCars() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class));
    }

    public List<CarWithStatus> getAllCarsWithStatus() {
        String sql = "SELECT c.car_id, c.frame_number, c.brand, c.model, c.colour, cs.car_status_type " +
                "FROM car c JOIN car_status cs ON c.car_id = cs.car_id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new CarWithStatus(
                rs.getInt("car_id"),
                rs.getString("frame_number"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("colour"),
                rs.getString("car_status_type")
        ));
    }

}
