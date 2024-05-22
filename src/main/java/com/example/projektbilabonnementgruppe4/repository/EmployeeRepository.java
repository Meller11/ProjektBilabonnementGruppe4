package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Finder en medarbejder i databasen baseret på brugernavn og adgangskode.
    public Employee findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM employee WHERE username = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new BeanPropertyRowMapper<>(Employee.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Gemmer en ny medarbejder i databasen
    public void saveNewEmployee(Employee employee) {
        String sql = "INSERT INTO employee (firstname, lastname, user_type, username, password) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, employee.getFirstname(), employee.getLastname(), employee.getUserType(), employee.getUsername(), employee.getPassword());
    }

    //Henter en medarbejder fra databasen baseret på brugernavn.
    public Employee getEmployeeByUsername(String username) {
        String sql = "SELECT * FROM employee WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(Employee.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Henter alle medarbejdere fra databasen.
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    // Redigerer oplysninger om en medarbejder i databasen baseret på brugernavn.
    public void editEmployee(Employee employee) {
        String sql = "UPDATE employee SET firstname = ?, lastname = ?, user_type = ?, username = ?, password = ? WHERE employee_id = ?";
        jdbcTemplate.update(sql, employee.getFirstname(), employee.getLastname(), employee.getUserType(), employee.getUsername(), employee.getPassword(), employee.getEmployeeId());
    }


    //Sletter en medarbejder fra databasen baseret på brugernavn.
    public void deleteEmployee(String username) {
        String sql = "DELETE FROM employee WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }

    //Henter en medarbejder fra databasen baseret på medarbejderens ID.
    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{employeeId}, new BeanPropertyRowMapper<>(Employee.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
