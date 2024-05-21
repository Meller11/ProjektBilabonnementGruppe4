package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.Employee;
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
  public Employee validateUser(String username, String password) {
    return employeeRepository.findByUsernameAndPassword(username, password);
  }

  public void saveNewEmployee(Employee employee) {
    employeeRepository.saveNewEmployee(employee);
  }

  // Finder en medarbejder i databasen baseret på brugernavn
  public Employee getEmployeeByUsername(String username) {
    return employeeRepository.getEmployeeByUsername(username);
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.getAllEmployees();
  }

  public void editEmployee(Employee employee) {
    employeeRepository.editEmployee(employee);
  }

  public void deleteEmployee(String username) {
    employeeRepository.deleteEmployee(username);
  }

  public Employee getEmployeeByID(int employeeId){
    return employeeRepository.getEmployeeById(employeeId);
  }

}

