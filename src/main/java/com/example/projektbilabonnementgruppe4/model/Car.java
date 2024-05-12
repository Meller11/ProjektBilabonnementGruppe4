package com.example.projektbilabonnementgruppe4.model;
import java.time.LocalDate;

public class Car {
    private Integer carId;
    private String frameNumber;
    private String brand;
    private String model;
    private String colour;
    private String gearType;
    private String fuelType;
    private double price;
    private double registrationFee;
    private double emission;
    private LocalDate acquisitionDate;

    public Car() {
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

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public double getEmission() {
        return emission;
    }

    public void setEmission(double emission) {
        this.emission = emission;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", frameNumber='" + frameNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", gearType='" + gearType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", price=" + price +
                ", registrationFee=" + registrationFee +
                ", emission=" + emission +
                ", acquisitionDate=" + acquisitionDate +
                '}';
    }
}
