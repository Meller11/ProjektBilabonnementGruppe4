package com.example.projektbilabonnementgruppe4.repository;

import com.example.projektbilabonnementgruppe4.model.AdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

@Autowired
  private JdbcTemplate jdbcTemplate;

public void saveNewEmployee(AdminModel adminModel){
  String sql ="INSERT INTO employee (FirstName, LastName, UserType, UserName, Password) VALUES (?, ?, ?, ?, ?)";
  jdbcTemplate.update(sql, adminModel.getFirstName(), adminModel.getLastName(), adminModel.getUserType(), adminModel.getUserName(), adminModel.getPassword());

}



}
