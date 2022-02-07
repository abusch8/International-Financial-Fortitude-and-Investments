package com.iffi.entity;

public class Cryptocurrencies extends Asset {

    final private double exchangeRate;
    final private double exchangeFeeRate;

    public Cryptocurrencies(String code, String label, double exchangeRate, double exchangeFeeRate) {
        super(code, label);
        this.exchangeRate = exchangeRate;
        this.exchangeFeeRate = exchangeFeeRate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public double getExchangeFeeRate() {
        return exchangeFeeRate;
    }
}
