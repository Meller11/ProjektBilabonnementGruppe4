package com.example.projektbilabonnementgruppe4.model;

import java.time.LocalDate;

public class DamageReport {

    private int damageReportId;
    private int contractId;
    private boolean paintDamage;
    private boolean rimDamage;
    private boolean windshieldDamage;
    private int mileage;
    private LocalDate reportDate;
    private boolean damageReportDone;

    public DamageReport() {
    }

    public DamageReport(int damageReportId, int contractId, boolean paintDamage, boolean rimDamage, boolean windshieldDamage, int mileage, LocalDate reportDate, boolean damageReportDone) {
        this.damageReportId = damageReportId;
        this.contractId = contractId;
        this.paintDamage = paintDamage;
        this.rimDamage = rimDamage;
        this.windshieldDamage = windshieldDamage;
        this.mileage = mileage;
        this.reportDate = reportDate;
        this.damageReportDone = damageReportDone;
    }

    public DamageReport(int contractId, boolean paintDamage, boolean rimDamage, boolean windshieldDamage, int mileage, LocalDate reportDate, boolean damageReportDone) {
        this.contractId = contractId;
        this.paintDamage = paintDamage;
        this.rimDamage = rimDamage;
        this.windshieldDamage = windshieldDamage;
        this.mileage = mileage;
        this.reportDate = reportDate;
        this.damageReportDone = damageReportDone;
    }
    public int getDamageReportId(){
        return damageReportId;
    }

    public void setDamageReportId(int damageReportId){
        this.damageReportId = damageReportId;
    }

    public int getContractId(){
        return contractId;
    }

    public void setContractId(int contractId){
        this.contractId = contractId;
    }

    public boolean isDamageReportDone() {
        return damageReportDone;
    }

    public void setDamageReportDone(boolean damageReportDone) {
        this.damageReportDone = damageReportDone;
    }

    public boolean isPaintDamage() {
        return paintDamage;
    }

    public void setPaintDamage(boolean paintDamage) {
        this.paintDamage = paintDamage;
    }

    public boolean isRimDamage() {
        return rimDamage;
    }

    public void setRimDamage(boolean rimDamage) {
        this.rimDamage = rimDamage;
    }

    public boolean isWindshieldDamage() {
        return windshieldDamage;
    }

    public void setWindshieldDamage(boolean windshieldDamage) {
        this.windshieldDamage = windshieldDamage;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "DamageReport{" +
                "damageReportId=" + damageReportId +
                ", contractId=" + contractId +
                ", paintDamage=" + paintDamage +
                ", rimDamage=" + rimDamage +
                ", windshieldDamage=" + windshieldDamage +
                ", mileage=" + mileage +
                ", reportDate=" + reportDate +
                ", damageReportDone=" + damageReportDone +
                '}';
    }
}
