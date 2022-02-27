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

    public double getAppraisedValue() {
        return appraisedValue;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }
}
