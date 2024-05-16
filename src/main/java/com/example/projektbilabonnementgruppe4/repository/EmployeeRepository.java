package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.EmployeeModel;
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

  // Finder brugeren i databasen efter username og password
  public EmployeeModel findByUsernameAndPassword(String username, String password) {
    String sql = "SELECT * FROM employee WHERE username = ? AND password = ?";
    try {
      return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new BeanPropertyRowMapper<>(EmployeeModel.class));
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  // Gemmer en ny medarbejder i databasen
public void saveNewEmployee(EmployeeModel employeeModel){
  String sql = "INSERT INTO employee (firstname, lastname, user_type, username, password) VALUES (?, ?, ?, ?, ?)";
  jdbcTemplate.update(sql, employeeModel.getFirstname(), employeeModel.getLastname(), employeeModel.getUserType(), employeeModel.getUsername(), employeeModel.getPassword());
  }

  public EmployeeModel getEmployeeByUsername(String username) {
    String sql = "SELECT * FROM employee WHERE username = ?";
    try {
      return jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(EmployeeModel.class));
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  public List<EmployeeModel> getAllEmployees() {
    String sql = "SELECT * FROM employee";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmployeeModel.class));
  }

  public void editEmployee(EmployeeModel employeeModel) {
    String sql = "UPDATE employee SET firstname = ?, lastname = ?, user_type = ?, username = ?, password = ? WHERE username = ?";
    jdbcTemplate.update(sql, employeeModel.getFirstname(), employeeModel.getLastname(), employeeModel.getUserType(), employeeModel.getUsername(), employeeModel.getPassword(), employeeModel.getUsername());
  }

  public void deleteEmployee(String username) {
    String sql = "DELETE FROM employee WHERE username = ?";
    jdbcTemplate.update(sql, username);
  }

  public EmployeeModel getEmployeeById(int employeeId){
    String sql = "SELECT * FROM employee WHERE employee_id = ?";
    try {
      return jdbcTemplate.queryForObject(sql, new Object[]{employeeId}, new BeanPropertyRowMapper<>(EmployeeModel.class));
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
}
