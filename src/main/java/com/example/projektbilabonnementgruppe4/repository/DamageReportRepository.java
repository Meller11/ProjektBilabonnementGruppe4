package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.DamageReport;
import com.example.projektbilabonnementgruppe4.viewModel.DamageReportWithCarAndRA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createNewDamageReport(DamageReport damageReport) {
        String sql = "INSERT INTO damage_report (contract_id, paint_damage, rim_damage, windshield_damage, mileage, report_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, damageReport.getContractId(),
                damageReport.isPaintDamage(), damageReport.isRimDamage(), damageReport.isWindshieldDamage(),
                damageReport.getDamageReportId(), damageReport.getReportDate());
    }

    public void deleteDamageReport(int contract_id){
        String sql = "DELETE FROM damage_report WHERE contract_id = ?";
        jdbcTemplate.update(sql, contract_id);
    }

    public List<DamageReportWithCarAndRA> damageReportOverview (){
        String sql = "SELECT *" +
                "FROM rental_agreement\n" +
                "INNER JOIN car\n" +
                "ON car.car_id = rental_agreement.car_id\n" +
                "INNER JOIN employee\n" +
                "ON employee.employee_id = rental_agreement.employee_id\n" +
                "INNER JOIN damage_report\n" +
                "ON rental_agreement.contract_id = damage_report.contract_id;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DamageReportWithCarAndRA.class));
    }

    public DamageReportWithCarAndRA damageReportByID(int contract_id){
        String sql = "SELECT *" +
                "FROM rental_agreement\n" +
                "INNER JOIN car\n" +
                "ON car.car_id = rental_agreement.car_id\n" +
                "INNER JOIN employee\n" +
                "ON employee.employee_id = rental_agreement.employee_id\n" +
                "INNER JOIN damage_report\n" +
                "ON rental_agreement.contract_id = damage_report.contract_id\n" +
                "WHERE rental_agreement.contract_id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{contract_id}, new BeanPropertyRowMapper<>(DamageReportWithCarAndRA.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public DamageReport updateDamageReport(DamageReport damageReport, int contract_id) {
        String sql = "UPDATE damage_report SET paint_damage = ?, rim_damage = ?, windshield_damage = ?, mileage = ?, damage_report_done = 1 WHERE contract_id = ?";
        jdbcTemplate.update(sql, damageReport.isPaintDamage(), damageReport.isRimDamage(), damageReport.isWindshieldDamage(), damageReport.getMileage(), contract_id);
        return damageReport;
    }

    public List<DamageReport> listOfAllDamageReports(){
        String sql = "SELECT * FROM damage_report";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DamageReport.class));
    }





}
