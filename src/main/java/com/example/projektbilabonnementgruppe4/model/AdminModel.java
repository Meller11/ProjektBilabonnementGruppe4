package com.example.projektbilabonnementgruppe4.model;


public class AdminModel {

  private int employeeID;
  private String firstName;
  private String lastName;
  private String userType;

  private String userName;

  private String password;


  public AdminModel(){

  }

  public AdminModel(int employeeID, String firstName, String lastName, String userType, String userName, String password) {
    this.employeeID = employeeID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userType = userType;
    this.userName = userName;
    this.password = password;
  }

  public int getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(int employeeID) {
    this.employeeID = employeeID;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
