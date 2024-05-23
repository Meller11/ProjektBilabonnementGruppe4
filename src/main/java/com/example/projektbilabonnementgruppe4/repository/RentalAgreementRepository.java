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

    // Opretter en ny udlejningsaftale i databasen
    public void createRentalAgreement(RentalAgreement rentalAgreement) {
        String sql = "INSERT INTO rental_agreement (employee_id, car_id, contract_number, pickup_location, contract_start_date, contract_end_date, contract_type, contract_monthly_fee, mileage_per_month) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, rentalAgreement.getEmployeeId(), rentalAgreement.getCarId(), rentalAgreement.getContractNumber(), rentalAgreement.getPickupLocation(), rentalAgreement.getContractStartDate(), rentalAgreement.getContractEndDate(), rentalAgreement.getContractType(), rentalAgreement.getContractMonthlyFee(),rentalAgreement.getMileagePerMonth());
    }

    // Henter en udlejningsaftale fra databasen baseret på contract_id
    public RentalAgreement getRentalAgreement(int rentalAgreementId) {
        String sql = "SELECT * FROM rental_agreement WHERE contract_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{rentalAgreementId}, new BeanPropertyRowMapper<>(RentalAgreement.class));
    }

    // Henter alle udlejningsaftaler fra databasen
    public List<RentalAgreement> getAllRentalAgreements() {
        String sql = "SELECT * FROM rental_agreement";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RentalAgreement.class));
    }

    // Opdaterer en udlejningsaftale i databasen baseret på contract_id
    public void updateRentalAgreement(RentalAgreement rentalAgreement) {
        String sql = "UPDATE rental_agreement SET employee_id = ?, contract_number = ?, pickup_location = ?, contract_start_date = ?, contract_end_date = ?, contract_type = ?, contract_monthly_fee = ?, mileage_per_month = ? WHERE contract_id = ?";
        jdbcTemplate.update(sql, rentalAgreement.getEmployeeId(), rentalAgreement.getContractNumber(), rentalAgreement.getPickupLocation(), rentalAgreement.getContractStartDate(), rentalAgreement.getContractEndDate(), rentalAgreement.getContractType(),rentalAgreement.getContractMonthlyFee(), rentalAgreement.getMileagePerMonth(), rentalAgreement.getContractId());
    }

    // Sletter en udlejningsaftale fra databasen baseret på contract_id
    public void deleteRentalAgreement(int rentalAgreementId) {
        String sql = "DELETE FROM rental_agreement WHERE contract_id = ?";
        jdbcTemplate.update(sql, rentalAgreementId);
    }

    // Henter alle udlejede biler fra databasen ved at joine car og rental_agreement tabellerne og returnerer dem som en liste af RentedCar objekter
    public List<RentedCar> getAllRentedCars() {
        String sql = "SELECT c.car_id, c.frame_number, c.brand, c.model, ra.contract_id, ra.contract_number, ra.pickup_location, ra.contract_start_date, ra.contract_end_date " +
                "FROM rental_agreement ra " +
                "JOIN car c ON ra.car_id = c.car_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RentedCar.class));
    }

    public int getAverageMonthlyFee(){
        String sql = "SELECT AVG(contract_monthly_fee) FROM rental_agreement;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public double getTotalMileageOfContracts(){
        String sql = "SELECT SUM(mileage_per_month) FROM rental_agreement;";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public double getMileageOfContract(int contractID){
        String sql = "SELECT mileage_per_month FROM rental_agreement WHERE contract_id = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, contractID);
    }

}
