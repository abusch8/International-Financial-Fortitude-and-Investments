package com.iffi.entity;

import java.time.LocalDate;

public class Cryptocurrency extends Asset {

    private final double exchangeRate;
    private final double exchangeFeeRate;
    private LocalDate purchaseDate;
    private Double purchaseExchangeRate;
    private Double numberOfCoins;

    public Cryptocurrency(String code, String label, double exchangeRate, double exchangeFeeRate) {
        super(code, label);
        this.exchangeRate = exchangeRate;
        this.exchangeFeeRate = exchangeFeeRate;
    }

    public Cryptocurrency(Cryptocurrency cryptocurrency, LocalDate purchaseDate, Double purchaseExchangeRate, Double numberOfCoins) {
        this(cryptocurrency.getCode(), cryptocurrency.getLabel(), cryptocurrency.getExchangeRate(), cryptocurrency.getExchangeFeeRate());
        this.purchaseDate = purchaseDate;
        this.purchaseExchangeRate = purchaseExchangeRate;
        this.numberOfCoins = numberOfCoins;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public double getExchangeFeeRate() {
        return exchangeFeeRate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Double getPurchaseExchangeRate() {
        return purchaseExchangeRate;
    }

    public Double getNumberOfCoins() {
        return numberOfCoins;
    }
}
