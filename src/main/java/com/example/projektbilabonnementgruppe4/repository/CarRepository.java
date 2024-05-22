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
        String sql = "SELECT c.car_id, c.frame_number, c.brand, c.model, c.colour, cs.car_status_type, cs.car_status_date " +
                "FROM car c JOIN car_status cs ON c.car_id = cs.car_id " +
                "ORDER BY cs.car_status_date DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CarWithStatus.class));
    }

    public List<CarWithStatus> searchCarsWithStatus(String query) {
        String sql = "SELECT c.car_id, c.frame_number, c.brand, c.model, c.colour, cs.car_status_type, cs.car_status_date " +
                "FROM car c JOIN car_status cs ON c.car_id = cs.car_id " +
                "WHERE c.frame_number LIKE ? OR c.brand LIKE ? OR c.model LIKE ? OR c.colour LIKE ? OR cs.car_status_type LIKE ?";
        String searchQuery = "%" + query + "%";
        return jdbcTemplate.query(sql, new Object[]{searchQuery, searchQuery, searchQuery, searchQuery, searchQuery},
                new BeanPropertyRowMapper<>(CarWithStatus.class));
    }

    public double getAveragePriceOfAllCars(){
        String sql = "SELECT avg(price) FROM car";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

}