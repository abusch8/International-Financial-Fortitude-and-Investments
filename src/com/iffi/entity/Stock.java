package com.iffi.entity;

import java.time.LocalDate;

public class Stock extends Asset {

    protected final String symbol;
    protected final double sharePrice;
    private LocalDate purchaseDate = null;
    private double purchaseSharePrice = 0;
    private double numberOfShares = 0;
    private double dividendTotal = 0;

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

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseSharePrice(Double purchaseSharePrice) {
        this.purchaseSharePrice = purchaseSharePrice;
    }

    public void setNumberOfShares(Double numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public void setDividendTotal(Double dividendTotal) {
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

    public double getPurchaseSharePrice() {
        return purchaseSharePrice;
    }

    public double getNumberOfShares() {
        return numberOfShares;
    }

    public double getDividendTotal() {
        return dividendTotal;
    }

    public double getValue() {
        return sharePrice * numberOfShares + dividendTotal;
    }

    public double getPurchaseValue() {
        return purchaseSharePrice * numberOfShares;
    }

    public double getFee() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-15s %s (Stock)\n", code, label, symbol));
        sb.append(String.format("  Cost Basis:  %.3f shares @ $%.2f on %s\n", numberOfShares, purchaseSharePrice, purchaseDate));
        String line = String.format("  Value Basis: %.3f shares @ $%.2f", numberOfShares, sharePrice);
        sb.append(line).append(" ".repeat(59 - line.length()));
        sb.append(String.format("%10.3f%%    $%15.2f\n", this.getGainPercentage(), this.getValue()));
        return sb.toString();
    }
}
