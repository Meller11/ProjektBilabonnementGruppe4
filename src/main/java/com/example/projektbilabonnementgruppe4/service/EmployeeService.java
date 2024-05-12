package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.EmployeeModel;
import com.example.projektbilabonnementgruppe4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  // Finder en medarbejder i databasen baseret på brugernavn
  public EmployeeModel getEmployeeByUsername(String username) {
    return employeeRepository.getEmployeeByUsername(username);
  }

  public List<EmployeeModel> getAllEmployees() {
    return employeeRepository.getAllEmployees();
  }

  public void editEmployee(EmployeeModel employeeModel) {
    employeeRepository.editEmployee(employeeModel);
  }

  public void deleteEmployee(String username) {
    employeeRepository.deleteEmployee(username);
  }

}

