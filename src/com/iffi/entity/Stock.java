package com.iffi.entity;

import java.time.LocalDate;

public class Stock extends Asset {

    private final String symbol;
    private final double sharePrice;
    private LocalDate purchaseDate;
    private Double purchaseSharePrice;
    private Double numberOfShares;
    private Double dividendTotal;

    public Stock(String code, String label, String symbol, double sharePrice) {
        super(code, label);
        this.symbol = symbol;
        this.sharePrice = sharePrice;
    }

    public Stock(Stock stock, LocalDate purchaseDate, Double purchaseSharePrice, Double numberOfShares, Double dividendTotal) {
        this(stock.getCode(), stock.getLabel(), stock.getSymbol(), stock.getSharePrice());
        this.purchaseDate = purchaseDate;
        this.purchaseSharePrice = purchaseSharePrice;
        this.numberOfShares = numberOfShares;
        this.dividendTotal = dividendTotal;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Double getPurchaseSharePrice() {
        return purchaseSharePrice;
    }

    public Double getNumberOfShares() {
        return numberOfShares;
    }

    public Double getDividendTotal() {
        return dividendTotal;
    }
}
