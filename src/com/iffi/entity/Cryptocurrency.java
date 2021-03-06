package com.iffi.entity;

import java.time.LocalDate;

public class Cryptocurrency extends Asset {

    private final double exchangeRate;
    private final double exchangeFeeRate;
    private LocalDate purchaseDate = null;
    private double purchaseExchangeRate = 0;
    private double numberOfCoins = 0;

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

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseExchangeRate(Double purchaseExchangeRate) {
        this.purchaseExchangeRate = purchaseExchangeRate;
    }

    public void setNumberOfCoins(Double numberOfCoins) {
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

    public double getPurchaseExchangeRate() {
        return purchaseExchangeRate;
    }

    public double getNumberOfCoins() {
        return numberOfCoins;
    }

    public double getValue() {
        return numberOfCoins * exchangeRate - (1 - exchangeFeeRate);
    }

    public double getPurchaseValue() {
        return numberOfCoins * purchaseExchangeRate;
    }

    public double getFee() {
        return 10;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-16s (Crypto)\n", code, label));
        sb.append(String.format("  Cost Basis:  %.3f coins @ $%.2f on %s\n", numberOfCoins, purchaseExchangeRate, purchaseDate.toString()));
        String line = String.format("  Value Basis: %.3f coins @ $%.2f less %.2f%%", numberOfCoins, exchangeRate, exchangeFeeRate * 100);
        sb.append(line).append(" ".repeat(59 - line.length()));
        sb.append(String.format("%10.3f%%    $%15.2f\n", this.getGainPercentage(), this.getValue()));
        return sb.toString();
    }
}
