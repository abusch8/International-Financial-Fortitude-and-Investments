package com.iffi.entity;

import java.time.LocalDate;

public abstract class Option extends Stock {

    protected LocalDate purchaseDate;
    protected double strikePricePerShare;
    protected double shareLimit;
    protected double premiumPerShare;
    protected LocalDate strikeDate;

    protected Option(Stock stock, LocalDate purchaseDate, double strikePricePerShare, double shareLimit, double premiumPerShare, LocalDate strikeDate) {
        super(stock.getCode(), stock.getLabel(), stock.getSymbol(), stock.getSharePrice());
        this.purchaseDate = purchaseDate;
        this.strikePricePerShare = strikePricePerShare;
        this.shareLimit = shareLimit;
        this.premiumPerShare = premiumPerShare;
        this.strikeDate = strikeDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setStrikePricePerShare(double strikePricePerShare) {
        this.strikePricePerShare = strikePricePerShare;
    }

    public void setShareLimit(double shareLimit) {
        this.shareLimit = shareLimit;
    }

    public void setPremiumPerShare(double premiumPerShare) {
        this.premiumPerShare = premiumPerShare;
    }

    public void setStrikeDate(LocalDate strikeDate) {
        this.strikeDate = strikeDate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public double getStrikePricePerShare() {
        return strikePricePerShare;
    }

    public double getShareLimit() {
        return shareLimit;
    }

    public double getPremiumPerShare() {
        return premiumPerShare;
    }

    public LocalDate getStrikeDate() {
        return strikeDate;
    }

    public boolean isExecutable() {
        return (sharePrice > strikePricePerShare);
    }

    public double getPremium() {
        return premiumPerShare * shareLimit;
    }

    public double getPurchaseValue() {
        return this.premiumPerShare * this.shareLimit;
    }

    public double getFee() {
        return 0;
    }
}
