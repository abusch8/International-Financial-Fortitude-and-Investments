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

    public abstract double getFee();

    public abstract double getPurchaseValue();

    public double getGain() {
        return this.getValue() - this.getPurchaseValue();
    }

    public double getGainPercentage() {
        return Math.round(((this.getGain() / this.getPurchaseValue()) * 100) * 1000.0) / 1000.0;
    }

    public abstract String toString();
}
