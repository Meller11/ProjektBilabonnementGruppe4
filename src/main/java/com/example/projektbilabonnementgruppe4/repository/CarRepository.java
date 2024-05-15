package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.CarStatus;
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

    public void addCar(Car car) {
        String sql = "INSERT INTO car (frame_number, brand, model, colour, gear_type, fuel_type, price, registration_fee, emission, acquisition_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getFrameNumber(), car.getBrand(), car.getModel(),
                car.getColour(), car.getGearType(), car.getFuelType(), car.getPrice(), car.getRegistrationFee(),
                car.getEmission(), car.getAcquisitionDate());
    }
    public void addCarStatus(Integer carId, String status, LocalDate statusDate) {
        String checkSql = "SELECT COUNT(*) FROM car_status WHERE car_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, new Object[]{carId}, Integer.class);
        if (count != null && count > 0) {
            String updateSql = "UPDATE car_status SET car_status_type = ?, car_status_date = ? WHERE car_id = ?";
            jdbcTemplate.update(updateSql, status, java.sql.Date.valueOf(statusDate), carId);
        } else {
            String insertSql = "INSERT INTO car_status (car_id, car_status_type, car_status_date) VALUES (?, ?, ?)";
            jdbcTemplate.update(insertSql, carId, status, java.sql.Date.valueOf(statusDate));
        }
    }
    /*public void addCarStatus(Integer carId, String status, LocalDate statusDate) {
        String sql = "INSERT INTO car_status (car_id, car_status_type, car_status_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, carId, status, java.sql.Date.valueOf(statusDate));
    }*/

    public Car getCarByFrameNumber(String frameNumber) {
        String sql = "SELECT * FROM car WHERE frame_number = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{frameNumber}, new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getAllCars() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class));
    }
    /*public Car getCarById(int carId) {
        String sql = "SELECT * FROM car WHERE car_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{carId}, new BeanPropertyRowMapper<>(Car.class));
    }*/
    public void updateCarStatus(CarStatus carStatus) {
        String sql = "UPDATE car_status SET car_status_type = ?, car_status_date = ? WHERE car_id = ?";
        jdbcTemplate.update(sql, carStatus.getCarStatusType(), carStatus.getStatusDate(), carStatus.getCarId());
    }
    public Car getCarById(int carId) {
        String sql = "SELECT c.*, cs.* FROM car c JOIN car_status cs ON c.car_id = cs.car_id WHERE c.car_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{carId}, (rs, rowNum) -> {
            Car car = new Car();
            car.setCarId(rs.getInt("car_id"));
            car.setBrand(rs.getString("brand"));
            car.setModel(rs.getString("model"));

            CarStatus carStatus = new CarStatus();
            carStatus.setCarStatusId(rs.getInt("car_status_id"));
            carStatus.setCarStatusType(rs.getString("car_status_type"));
            carStatus.setStatusDate(rs.getDate("car_status_date").toLocalDate());
            carStatus.setCarId(rs.getInt("car_id"));

            car.setCarStatus(carStatus);

            return car;
        });
    }
}
