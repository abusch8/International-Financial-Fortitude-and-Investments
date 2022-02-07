package com.iffi.entity;

public class Property extends Asset {

    private final double appraisedValue;

    public Property(String code, String label, double appraisedValue) {
        super(code, label);
        this.appraisedValue = appraisedValue;
    }

    public double getAppraisedValue() {
        return appraisedValue;
    }
}
