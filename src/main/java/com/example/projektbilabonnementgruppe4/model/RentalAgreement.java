package com.example.projektbilabonnementgruppe4.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RentalAgreement {
    private int contractId;
    private int employeeId;
    private int carId;
    private String contractNumber;
    private String pickupLocation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
    private String contractType;
    private double contractMonthlyFee;
    private double mileagePerMonth;

    public RentalAgreement() {
    }

    public RentalAgreement(int contractId, int employeeId, int carId, String contractNumber, String pickupLocation, LocalDate contractStartDate, LocalDate contractEndDate, String contractType, double contractMonthlyFee, double mileagePerMonth) {
        this.contractId = contractId;
        this.employeeId = employeeId;
        this.carId = carId;
        this.contractNumber = contractNumber;
        this.pickupLocation = pickupLocation;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractType = contractType;
        this.contractMonthlyFee = contractMonthlyFee;
        this.mileagePerMonth = mileagePerMonth;
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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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

    public double getMileagePerMonth() {
        return mileagePerMonth;
    }

    public void setMileagePerMonth(double mileagePerMonth) {
        this.mileagePerMonth = mileagePerMonth;
    }

    @Override
    public String toString() {
        return "RentalAgreement{" +
                "contractId=" + contractId +
                ", employeeId=" + employeeId +
                ", carId=" + carId +
                ", contractNumber='" + contractNumber + '\'' +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", contractStartDate=" + contractStartDate +
                ", contractEndDate=" + contractEndDate +
                ", contractType='" + contractType + '\'' +
                ", contractMonthlyFee=" + contractMonthlyFee +
                '}';
    }
}



