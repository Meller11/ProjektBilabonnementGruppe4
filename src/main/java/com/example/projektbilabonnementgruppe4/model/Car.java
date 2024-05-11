package com.example.projektbilabonnementgruppe4.model;
import java.time.LocalDate;

public class Car {
    private int carId;
    private int carStatusId;
    private String frameNumber;
    private String brand;
    private String model;
    private String colour;
    private String gearType;
    private double price;
    private double registrationFee;
    private double emission;
    private LocalDate acquisitionDate;
    private Integer saleId;

    public Car() {
    }

    public Car(int carId, int carStatusId, String frameNumber, String brand, String model, String colour,
               String gearType, double price, double registrationFee, double emission,
               LocalDate acquisitionDate, Integer saleId) {
        this.carId = carId;
        this.carStatusId = carStatusId;
        this.frameNumber = frameNumber;
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.gearType = gearType;
        this.price = price;
        this.registrationFee = registrationFee;
        this.emission = emission;
        this.acquisitionDate = acquisitionDate;
        this.saleId = saleId;
    }

    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public int getCarStatusId() { return carStatusId; }
    public void setCarStatusId(int carStatusId) { this.carStatusId = carStatusId; }

    public String getFrameNumber() { return frameNumber; }
    public void setFrameNumber(String frameNumber) { this.frameNumber = frameNumber; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getColour() { return colour; }
    public void setColour(String colour) { this.colour = colour; }

    public String getGearType() { return gearType; }
    public void setGearType(String gearType) { this.gearType = gearType; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getRegistrationFee() { return registrationFee; }
    public void setRegistrationFee(double registrationFee) { this.registrationFee = registrationFee; }

    public double getEmission() { return emission; }
    public void setEmission(double emission) { this.emission = emission; }

    public LocalDate getAcquisitionDate() { return acquisitionDate; }
    public void setAcquisitionDate(LocalDate acquisitionDate) { this.acquisitionDate = acquisitionDate; }

    public Integer getSaleId() { return saleId; }
    public void setSaleId(Integer saleId) { this.saleId = saleId; }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carStatusId=" + carStatusId +
                ", frameNumber='" + frameNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", gearType='" + gearType + '\'' +
                ", price=" + price +
                ", registrationFee=" + registrationFee +
                ", emission=" + emission +
                ", acquisitionDate=" + acquisitionDate +
                ", saleId=" + saleId +
                '}';
    }
}
