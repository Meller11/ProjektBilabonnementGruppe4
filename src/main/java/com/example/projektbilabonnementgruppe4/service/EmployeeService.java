package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.EmployeeModel;
import com.example.projektbilabonnementgruppe4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  //Tjek medarb. login-oplysninger.
  public EmployeeModel validateUser(String username, String password) {
    return employeeRepository.findByUsernameAndPassword(username, password);
  }

  public void saveNewEmployee(EmployeeModel employeeModel) {
    employeeRepository.saveNewEmployee(employeeModel);
  }

}
