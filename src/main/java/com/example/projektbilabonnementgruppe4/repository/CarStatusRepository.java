package com.example.projektbilabonnementgruppe4.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.time.LocalDate;

/* Klassen er skrevet af Lasse Fosgaard.
   Klassen indeholder metoder til at interagere med databasen i forhold til bilstatus. */

@Repository
public class CarStatusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Opretter en bilstatus i databasen
    public void createCarStatus(Integer carId, String status, LocalDate statusDate) {
        String sql = "INSERT INTO car_status (car_id, car_status_type, car_status_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, carId, status, java.sql.Date.valueOf(statusDate));
    }

    //Opdaterer en bilstatus i databasen. Sætter en ny status og statusdate til datoen for opdateringen.
    public void updateCarStatus(Integer carId, String newStatus, LocalDate statusDate) {
        String sql = "UPDATE car_status SET car_status_type = ?, car_status_date = ? WHERE car_id = ?";
        jdbcTemplate.update(sql, newStatus, Date.valueOf(statusDate), carId);
    }
}
