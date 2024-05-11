package com.example.projektbilabonnementgruppe4.model;

public class CarBuyer {
    private int buyerId;
    private String companyName;
    private String companyNumber;
    private String companyEmail;

    public CarBuyer() {
    }

    public CarBuyer(int buyerId, String companyName, String companyNumber, String companyEmail) {
        this.buyerId = buyerId;

        this.companyName = companyName;
        this.companyNumber = companyNumber;
        this.companyEmail = companyEmail;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Override
    public String toString() {
        return "CarBuyer{" +
                "buyerId=" + buyerId +
                ", companyName='" + companyName + '\'' +
                ", companyNumber='" + companyNumber + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                '}';
    }
}
