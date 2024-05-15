package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
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

    /*public RentalAgreement createRentalAgreement(RentalAgreement rentalAgreement) {
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
    public RentalAgreement getRentalAgreement(int rentalAgreementId) {
        String sql = "SELECT c.*, car.* FROM contract c JOIN car ON c.car_id = car.car_id WHERE c.contract_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{rentalAgreementId}, new BeanPropertyRowMapper<>(RentalAgreement.class));
    }

    public List<RentalAgreement> getAllRentalAgreements() {
        String sql = "SELECT c.*, car.* FROM contract c JOIN car ON c.car_id = car.car_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RentalAgreement.class));
    }*/
    public RentalAgreement createRentalAgreement(RentalAgreement rentalAgreement) {
        String sql = "INSERT INTO contract (employee_id, car_id, contract_number, pickup_location, contract_start_date, contract_end_date, contract_type, contract_monthly_fee) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, rentalAgreement.getEmployeeId(), rentalAgreement.getCar().getCarId(), rentalAgreement.getContractNumber(), rentalAgreement.getPickupLocation(), rentalAgreement.getContractStartDate(), rentalAgreement.getContractEndDate(), rentalAgreement.getContractType(), rentalAgreement.getContractMonthlyFee());
        return rentalAgreement;
    }

    public RentalAgreement getRentalAgreement(int rentalAgreementId) {
        String sql = "SELECT c.*, car.* FROM contract c JOIN car ON c.car_id = car.car_id WHERE c.contract_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{rentalAgreementId}, (rs, rowNum) -> {
            RentalAgreement rentalAgreement = new RentalAgreement();
            rentalAgreement.setContractId(rs.getInt("contract_id"));
            rentalAgreement.setContractNumber(rs.getString("contract_number"));
            rentalAgreement.setPickupLocation(rs.getString("pickup_location"));
            rentalAgreement.setContractStartDate(rs.getDate("contract_start_date").toLocalDate());
            rentalAgreement.setContractEndDate(rs.getDate("contract_end_date").toLocalDate());
            rentalAgreement.setContractType(rs.getString("contract_type"));
            rentalAgreement.setContractMonthlyFee(rs.getDouble("contract_monthly_fee"));

            Car car = new Car();
            car.setCarId(rs.getInt("car_id"));
            car.setBrand(rs.getString("brand"));
            car.setModel(rs.getString("model"));

            if (car != null) {
                rentalAgreement.setCar(car);
            }
            return rentalAgreement;
        });
    }
    public List<RentalAgreement> getAllRentalAgreements() {
        String sql = "SELECT c.*, car.* FROM contract c JOIN car ON c.car_id = car.car_id";
        List<RentalAgreement> rentalAgreements = jdbcTemplate.query(sql, (rs, rowNum) -> {
            RentalAgreement rentalAgreement = new RentalAgreement();
            rentalAgreement.setContractId(rs.getInt("contract_id"));
            rentalAgreement.setContractNumber(rs.getString("contract_number"));
            rentalAgreement.setPickupLocation(rs.getString("pickup_location"));
            rentalAgreement.setContractStartDate(rs.getDate("contract_start_date").toLocalDate());
            rentalAgreement.setContractEndDate(rs.getDate("contract_end_date").toLocalDate());
            rentalAgreement.setContractType(rs.getString("contract_type"));
            rentalAgreement.setContractMonthlyFee(rs.getDouble("contract_monthly_fee"));

            Car car = new Car();
            car.setCarId(rs.getInt("car_id"));
            car.setBrand(rs.getString("brand"));
            car.setModel(rs.getString("model"));

            if (car != null) {
                rentalAgreement.setCar(car);
            }
            return rentalAgreement;
        });
        return rentalAgreements;
    }
    public RentalAgreement updateRentalAgreement(RentalAgreement rentalAgreement) {
        if (rentalAgreement.getContractNumber() == null) {
            throw new IllegalArgumentException("Kontrakt nummer kan ikke være null");
        }
        String sql = "UPDATE contract SET employee_id = ?, car_id = ?, contract_number = ?, pickup_location = ?, contract_start_date = ?, contract_end_date = ?, contract_type = ?, contract_monthly_fee = ? WHERE contract_id = ?";
        jdbcTemplate.update(sql, rentalAgreement.getEmployeeId(), rentalAgreement.getCar().getCarId(), rentalAgreement.getContractNumber(), rentalAgreement.getPickupLocation(), rentalAgreement.getContractStartDate(), rentalAgreement.getContractEndDate(), rentalAgreement.getContractType(), rentalAgreement.getContractMonthlyFee(), rentalAgreement.getContractId());
        return rentalAgreement;
    }

    public void deleteRentalAgreement(int rentalAgreementId) {
        String sql = "DELETE FROM contract WHERE contract_id = ?";
        jdbcTemplate.update(sql, rentalAgreementId);
    }


    public List<RentalAgreement> getAllRentedCars() {
        String sql = "SELECT c.*, car.*, cs.* FROM contract c JOIN car ON c.car_id = car.car_id JOIN car_status cs ON car.car_id = cs.car_id WHERE cs.car_status_type = 'Udlejet'";
        List<RentalAgreement> rentalAgreements = jdbcTemplate.query(sql, (rs, rowNum) -> {
            RentalAgreement rentalAgreement = new RentalAgreement();
            rentalAgreement.setContractId(rs.getInt("contract_id"));
            rentalAgreement.setContractNumber(rs.getString("contract_number"));
            rentalAgreement.setPickupLocation(rs.getString("pickup_location"));
            rentalAgreement.setContractStartDate(rs.getDate("contract_start_date").toLocalDate());
            rentalAgreement.setContractEndDate(rs.getDate("contract_end_date").toLocalDate());
            rentalAgreement.setContractType(rs.getString("contract_type"));
            rentalAgreement.setContractMonthlyFee(rs.getDouble("contract_monthly_fee"));

            Car car = new Car();
            car.setCarId(rs.getInt("car_id"));
            car.setBrand(rs.getString("brand"));
            car.setModel(rs.getString("model"));
            car.setColour(rs.getString("colour"));

            if (car != null) {
                rentalAgreement.setCar(car);
            }
            return rentalAgreement;
        });
        return rentalAgreements;
    }
}
