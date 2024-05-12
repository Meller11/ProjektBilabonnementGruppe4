package com.example.projektbilabonnementgruppe4.model;

import java.time.LocalDate;

public class CarStatus {
    private Integer carStatusId;
    private String carStatusType;
    private LocalDate statusDate;
    private Integer carId;

    public CarStatus() {
    }

    public CarStatus(int carStatusId, String carStatusType, LocalDate statusDate, int carId) {
        this.carStatusId = carStatusId;
        this.carStatusType = carStatusType;
        this.statusDate = statusDate;
        this.carId = carId;
    }

    // Getters og setters
    public int getCarStatusId() {
        return carStatusId;
    }

    public void setCarStatusId(int carStatusId) {
        this.carStatusId = carStatusId;
    }

    public String getCarStatusType() {
        return carStatusType;
    }

    public void setCarStatusType(String carStatusType) {
        this.carStatusType = carStatusType;
    }

    public LocalDate getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "CarStatus{" +
                "carStatusId=" + carStatusId +
                ", carStatusType='" + carStatusType + '\'' +
                ", statusDate=" + statusDate +
                ", carId=" + carId +
                '}';
    }
}
