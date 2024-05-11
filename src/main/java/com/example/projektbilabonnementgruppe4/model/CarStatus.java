package com.example.projektbilabonnementgruppe4.model;

import java.time.LocalDate;

public class CarStatus {
    private int carStatusId;
    private String status;
    private LocalDate statusDate;
    private int carId;

    public CarStatus() {
    }

    public CarStatus(int carStatusId, String status, LocalDate statusDate, int carId) {
        this.carStatusId = carStatusId;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", status='" + status + '\'' +
                ", statusDate=" + statusDate +
                ", carId=" + carId +
                '}';
    }
}
