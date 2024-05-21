package com.example.projektbilabonnementgruppe4.model;


public class Employee {
  private int employeeId;
  private String firstname;
  private String lastname;
  private String userType;
  private String username;
  private String password;
  public Employee(){
  }
  public Employee(int employeeId, String firstname, String lastname, String userType, String username, String password) {
    this.employeeId = employeeId;
    this.firstname = firstname;
    this.lastname = lastname;
    this.userType = userType;
    this.username = username;
    this.password = password;
  }
  public int getEmployeeId() {
    return employeeId;
  }
  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }
  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  public String getUserType() {
    return userType;
  }
  public void setUserType(String userType) {
    this.userType = userType;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}


