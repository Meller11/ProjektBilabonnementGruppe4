package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

  private final JdbcTemplate jdbcTemplate;
  @Autowired
  public EmployeeRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // Finder brugeren i databasen efter brugernavn og password
  public EmployeeModel findByUsernameAndPassword(String username, String password) {
    String sql = "SELECT * FROM employee WHERE UserName = ? AND Password = ?";
    try {
      return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new BeanPropertyRowMapper<>(EmployeeModel.class));
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  // Gemmer en ny medarbejder i databasen
public void saveNewEmployee(EmployeeModel employeeModel){
  String sql ="INSERT INTO employee (FirstName, LastName, UserType, UserName, Password) VALUES (?, ?, ?, ?, ?)";
  jdbcTemplate.update(sql, employeeModel.getFirstName(), employeeModel.getLastName(), employeeModel.getUserType(), employeeModel.getUserName(), employeeModel.getPassword());
  }


}