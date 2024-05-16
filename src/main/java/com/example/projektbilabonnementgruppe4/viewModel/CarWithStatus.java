package com.example.projektbilabonnementgruppe4.viewModel;

public class CarWithStatus {
    private Integer carId;
    private String frameNumber;
    private String brand;
    private String model;
    private String colour;
    private String statusType; // Status fra CarStatus

    // Constructors, getters, and setters
    public CarWithStatus() {
    }

    public CarWithStatus(Integer carId, String frameNumber, String brand, String model, String colour, String statusType) {
        this.carId = carId;
        this.frameNumber = frameNumber;
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.statusType = statusType;
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

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
}
