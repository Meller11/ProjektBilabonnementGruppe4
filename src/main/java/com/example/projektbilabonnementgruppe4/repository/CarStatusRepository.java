package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarStatusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CarStatus> getAllCarStatuses() {
        String sql = "SELECT * FROM car_status";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CarStatus.class));
    }
}
