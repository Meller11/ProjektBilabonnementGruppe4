package com.example.projektbilabonnementgruppe4.viewModel;

import java.time.LocalDate;

public class RentedCar {
    private Integer carId;
    private Integer contractId;
    private String frameNumber;
    private String brand;
    private String model;
    private String contractNumber;
    private String pickupLocation;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;


    public RentedCar() {
    }

    public RentedCar(Integer carId,Integer contractId,String frameNumber, String brand, String model, String contractNumber, String pickupLocation, LocalDate contractStartDate, LocalDate contractEndDate) {
        this.carId = carId;
        this.contractId = contractId;
        this.frameNumber = frameNumber;
        this.brand = brand;
        this.model = model;
        this.contractNumber = contractNumber;
        this.pickupLocation = pickupLocation;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
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

    public void setModel(String model) {
        this.model = model;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
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

}
