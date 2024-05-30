package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.viewModel.CarWithStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/* Klassen er primært skrevet af Lasse Fosgaard, med tilføjelser af Mads Rosenmeyer og Mads Eller relateret til deres kode.
   Klassen indeholder metoder til at interagere med databasen i forhold til bilerne i systemet. */

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Opretter en bil i databasen
    public void createCar(Car car) {
        String sql = "INSERT INTO car (frame_number, brand, model, colour, gear_type, fuel_type, price, registration_fee, emission, acquisition_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getFrameNumber(), car.getBrand(), car.getModel(),
                car.getColour(), car.getGearType(), car.getFuelType(), car.getPrice(), car.getRegistrationFee(),
                car.getEmission(), car.getAcquisitionDate());
    }

    // Opdaterer en bil i databasen.
    public void updateCar(Car car) {
        String sql = "UPDATE car SET frame_number = ?, brand = ?, model = ?, colour = ?, gear_type = ?, fuel_type = ?, price = ?, registration_fee = ?, emission = ?, acquisition_date = ? WHERE car_id = ?";
        jdbcTemplate.update(sql, car.getFrameNumber(), car.getBrand(), car.getModel(), car.getColour(), car.getGearType(), car.getFuelType(), car.getPrice(), car.getRegistrationFee(), car.getEmission(), car.getAcquisitionDate(), car.getCarId());
    }

    // Sletter en bil i databasen
    public void deleteCarById(Integer carId) {
        String sql = "DELETE FROM car WHERE car_id = ?";
        jdbcTemplate.update(sql, carId);
    }

    // Returnerer en bil fra databasen ud fra bilens stelnummer (frameNumber)
    public Car getCarByFrameNumber(String frameNumber) {
        String sql = "SELECT * FROM car WHERE frame_number = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{frameNumber}, new BeanPropertyRowMapper<>(Car.class));
    }

    // Returnerer en liste af alle biler i databasen
    public List<Car> getAllCars() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class));
    }

    // Returnerer en liste af CarWithStatus objekter, som indeholder data fra både car og car_status tabellerne
    public List<CarWithStatus> getAllCarsWithStatus() {
        String sql = "SELECT c.car_id, c.frame_number, c.brand, c.model, c.colour, cs.car_status_type, cs.car_status_date " +
                "FROM car c JOIN car_status cs ON c.car_id = cs.car_id " +
                "ORDER BY cs.car_status_date DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CarWithStatus.class));
    }

    //Returnerer en liste af CarWithStatus objekter, hvor frame_number, brand, model, colour eller car_status_type matcher query
    public List<CarWithStatus> searchCarsWithStatus(String query) {
        String sql = "SELECT c.car_id, c.frame_number, c.brand, c.model, c.colour, cs.car_status_type, cs.car_status_date " +
                "FROM car c JOIN car_status cs ON c.car_id = cs.car_id " +
                "WHERE c.frame_number LIKE ? OR c.brand LIKE ? OR c.model LIKE ? OR c.colour LIKE ? OR cs.car_status_type LIKE ?";
        String searchQuery = "%" + query + "%";
        return jdbcTemplate.query(sql, new Object[]{searchQuery, searchQuery, searchQuery, searchQuery, searchQuery},
                new BeanPropertyRowMapper<>(CarWithStatus.class));
    }

    //Returnerer en double med gennemsnits anskaffelsesprisen, for alle biler i databasen
    public double getAveragePriceOfAllCars(){
        String sql = "SELECT avg(price) FROM car";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

}