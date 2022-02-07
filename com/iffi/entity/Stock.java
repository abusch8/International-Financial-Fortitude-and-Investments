package com.iffi.entity;

public class Stock extends Asset {

    final private String symbol;
    final private double sharePrice;

    public Stock(String code, String label, String symbol, double sharePrice) {
        super(code, label);
        this.symbol = symbol;
        this.sharePrice = sharePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getSharePrice() {
        return sharePrice;
    }
}
