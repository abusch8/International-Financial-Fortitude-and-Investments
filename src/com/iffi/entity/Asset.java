package com.iffi.entity;

public abstract class Asset {

    protected final String code;
    protected final String label;

    protected Asset(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public abstract double getValue();

    public abstract double getPurchaseValue();

    public abstract double getFee();

    public double getGain() {
        return this.getValue() - this.getPurchaseValue();
    }

    public double getGainPercentage() {
        return (this.getGain() / this.getPurchaseValue()) * 100;
    }

    public abstract String toString();
}
