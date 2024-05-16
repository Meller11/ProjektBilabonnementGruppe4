package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.viewModel.RentedCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalAgreementRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RentalAgreementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RentalAgreement createRentalAgreement(RentalAgreement rentalAgreement) {
        String sql = "INSERT INTO contract (employee_id, car_id, contract_number, pickup_location, contract_start_date, contract_end_date, contract_type, contract_monthly_fee) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, rentalAgreement.getEmployeeId(), rentalAgreement.getCarId(), rentalAgreement.getContractNumber(), rentalAgreement.getPickupLocation(), rentalAgreement.getContractStartDate(), rentalAgreement.getContractEndDate(), rentalAgreement.getContractType(), rentalAgreement.getContractMonthlyFee());
        return rentalAgreement;
    }

    public RentalAgreement getRentalAgreement(int rentalAgreementId) {
        String sql = "SELECT * FROM contract WHERE contract_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{rentalAgreementId}, new BeanPropertyRowMapper<>(RentalAgreement.class));
    }

    public List<RentalAgreement> getAllRentalAgreements() {
        String sql = "SELECT * FROM contract";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RentalAgreement.class));
    }

    public RentalAgreement updateRentalAgreement(RentalAgreement rentalAgreement) {
        String sql = "UPDATE contract SET employee_id = ?, car_id = ?, contract_number = ?, pickup_location = ?, contract_start_date = ?, contract_end_date = ?, contract_type = ?, contract_monthly_fee = ? WHERE contract_id = ?";
        jdbcTemplate.update(sql, rentalAgreement.getEmployeeId(), rentalAgreement.getCarId(), rentalAgreement.getContractNumber(), rentalAgreement.getPickupLocation(), rentalAgreement.getContractStartDate(), rentalAgreement.getContractEndDate(), rentalAgreement.getContractType(), rentalAgreement.getContractId());
        return rentalAgreement;
    }

    public void deleteRentalAgreement(int rentalAgreementId) {
        String sql = "DELETE FROM contract WHERE contract_id = ?";
        jdbcTemplate.update(sql, rentalAgreementId);
    }


    public List<RentedCar> getAllRentedCars() {
        String sql = "SELECT c.car_id, c.frame_number, c.brand, c.model, co.contract_number, co.pickup_location, co.contract_start_date, co.contract_end_date " +
                "FROM contract co " +
                "JOIN car c ON co.car_id = c.car_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RentedCar.class));
    }
}
