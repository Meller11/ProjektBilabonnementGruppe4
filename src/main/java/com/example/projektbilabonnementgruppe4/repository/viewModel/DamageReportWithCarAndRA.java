package com.example.projektbilabonnementgruppe4.repository.viewModel;

import java.time.LocalDate;

public class DamageReportWithCarAndRA {

    private Integer carId;
    private String frameNumber;
    private String brand;
    private String model;
    private String colour;
    private double price;
    private int employeeId;
    private String firstName;
    private String lastName;
    private String username;
    private int contractId;
    private String contractNumber;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private String contractType;
    private int damageReportId;
    private boolean paintDamage;
    private boolean rimDamage;
    private boolean windshieldDamage;
    private int mileage;
    private LocalDate reportDate;
    private boolean damageReportDone;


    public DamageReportWithCarAndRA() {
    }

    public DamageReportWithCarAndRA(Integer carId, String frameNumber, String brand, String model, String colour, double price, int employeeId, String firstName, String lastName, String username, int contractId, String contractNumber, LocalDate contractStartDate, LocalDate contractEndDate, String contractType, int damageReportId, boolean paintDamage, boolean rimDamage, boolean windshieldDamage, int mileage, LocalDate reportDate, boolean damageReportDone) {
        this.carId = carId;
        this.frameNumber = frameNumber;
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.price = price;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.contractId = contractId;
        this.contractNumber = contractNumber;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractType = contractType;
        this.damageReportId = damageReportId;
        this.paintDamage = paintDamage;
        this.rimDamage = rimDamage;
        this.windshieldDamage = windshieldDamage;
        this.mileage = mileage;
        this.reportDate = reportDate;
        this.damageReportDone = damageReportDone;
    }

    public boolean isDamageReportDone() {
        return damageReportDone;
    }

    public void setDamageReportDone(boolean damageReportDone) {
        this.damageReportDone = damageReportDone;
    }

    public Integer getCarId() {
        return carId;
    }


    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(LocalDate contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public LocalDate getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(LocalDate contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    public int getDamageReportId() {
        return damageReportId;
    }

    public void setDamageReportId(int damageReportId) {
        this.damageReportId = damageReportId;
    }

    public boolean isPaintDamage() {
        return paintDamage;
    }

    public void setPaintDamage(boolean paintDamage) {
        this.paintDamage = paintDamage;
    }

    public boolean isRimDamage() {
        return rimDamage;
    }

    public void setRimDamage(boolean rimDamage) {
        this.rimDamage = rimDamage;
    }

    public boolean isWindshieldDamage() {
        return windshieldDamage;
    }

    public void setWindshieldDamage(boolean windshieldDamage) {
        this.windshieldDamage = windshieldDamage;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "DamageReportInformation{" +
                ", carId=" + carId +
                ", frameNumber='" + frameNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                ", employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", contractId=" + contractId +
                ", contractNumber='" + contractNumber + '\'' +
                ", contractStartDate=" + contractStartDate +
                ", contractEndDate=" + contractEndDate +
                ", contractType='" + contractType + '\'' +
                ", damageReportId=" + damageReportId +
                ", paintDamage=" + paintDamage +
                ", rimDamage=" + rimDamage +
                ", windshieldDamage=" + windshieldDamage +
                ", mileage=" + mileage +
                ", reportDate=" + reportDate +
                ", damageReportDone=" + damageReportDone +
                '}';
    }
}
