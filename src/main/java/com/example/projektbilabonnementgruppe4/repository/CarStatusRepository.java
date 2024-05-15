package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CarStatusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createCarStatus(Integer carId, String status, LocalDate statusDate) {
        String sql = "INSERT INTO car_status (car_id, car_status_type, car_status_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, carId, status, java.sql.Date.valueOf(statusDate));
    }

    public List<CarStatus> getAllCarStatuses() {
        String sql = "SELECT * FROM car_status";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CarStatus.class));
    }

    public void updateCarStatus(Integer carId, String newStatus, LocalDate statusDate) {
        String sql = "UPDATE car_status SET car_status_type = ?, car_status_date = ? WHERE car_id = ?";
        jdbcTemplate.update(sql, newStatus, Date.valueOf(statusDate), carId);
    }
}
