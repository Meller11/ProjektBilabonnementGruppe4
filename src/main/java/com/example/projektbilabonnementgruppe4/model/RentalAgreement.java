package com.example.projektbilabonnementgruppe4.model;

import java.time.LocalDate;

public class RentalAgreement {
    private int contractId;
    private int employeeId;
    private Car car;
    private String contractNumber;
    private String pickupLocation;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private String contractType;
    private double contractMonthlyFee;

    public RentalAgreement() {
    }

    public RentalAgreement(int contractId, int employeeId, Car car, String contractNumber, String pickupLocation, LocalDate contractStartDate, LocalDate contractEndDate, String contractType, double contractMonthlyFee) {
        this.contractId = contractId;
        this.employeeId = employeeId;
        this.car = car;
        this.contractNumber = contractNumber;
        this.pickupLocation = pickupLocation;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractType = contractType;
        this.contractMonthlyFee = contractMonthlyFee;
    }

    public double getContractMonthlyFee() {
        return contractMonthlyFee;
    }

    public void setContractMonthlyFee(double contractMonthlyFee) {
        this.contractMonthlyFee = contractMonthlyFee;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

   public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
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
}



