package com.example.projektbilabonnementgruppe4.model;

import java.time.LocalDate;

public class CarSale {
    private int saleId;
    private LocalDate purchaseDate;
    private LocalDate transportDate;
    private String transportType;
    private int buyerId;

    public CarSale() {
    }

    public CarSale(int saleId, LocalDate purchaseDate, LocalDate transportDate, String transportType, int buyerId) {
        this.saleId = saleId;
        this.purchaseDate = purchaseDate;
        this.transportDate = transportDate;
        this.transportType = transportType;
        this.buyerId = buyerId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getTransportDate() {
        return transportDate;
    }

    public void setTransportDate(LocalDate transportDate) {
        this.transportDate = transportDate;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "CarSale{" +
                "saleId=" + saleId +
                ", purchaseDate=" + purchaseDate +
                ", transportDate=" + transportDate +
                ", transportType='" + transportType + '\'' +
                ", buyerId=" + buyerId +
                '}';
    }
}
