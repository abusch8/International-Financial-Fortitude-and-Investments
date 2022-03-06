package com.iffi.entity;

import java.time.LocalDate;

public class Property extends Asset {

    private final double appraisedValue;
    private LocalDate purchaseDate;
    private Double purchasePrice;

    public Property(String code, String label, double appraisedValue) {
        super(code, label);
        this.appraisedValue = appraisedValue;
    }

    public Property(Property property, LocalDate purchaseDate, Double purchasePrice) {
        this(property.getCode(), property.getLabel(), property.getAppraisedValue());
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getAppraisedValue() {
        return appraisedValue;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public double getValue() {
        return appraisedValue;
    }

    public double getFee() {
        return 100;
    }

    public double getPurchaseValue() {
        return purchasePrice;
    }

    public double getGain() {
        return this.getValue() - this.getPurchaseValue();
    }

    public double getGainPercentage() {
        return (double) Math.round(((this.getGain() / this.getPurchaseValue()) * 100) * 1000) / 1000;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %s (Property)\n", code, label));
        sb.append(String.format("  Cost Basis:  purchased @ $%.2f on %s\n", purchasePrice, purchaseDate.toString()));
        String line = String.format("  Value Basis: appraised @ $%.2f", appraisedValue);
        sb.append(line).append(" ".repeat(59 - line.length()));
        sb.append(String.format("%10.3f%%    $%15.2f\n", this.getGainPercentage(), this.getValue()));
        return sb.toString();
    }
}
