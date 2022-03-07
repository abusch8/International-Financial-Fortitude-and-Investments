package com.iffi.entity;

import java.time.LocalDate;

public abstract class Option extends Stock {

    protected LocalDate purchaseDate = null;
    protected double strikePricePerShare = 0.0;
    protected double shareLimit = 0.0;
    protected double premiumPerShare = 0.0;
    protected LocalDate strikeDate = null;

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

    public double getValue() {
        return (this.isExecutable()) ? shareLimit * (sharePrice - strikePricePerShare) : 0.0;
    }

    public double getPremium() {
        return premiumPerShare * shareLimit;
    }

    public double getPurchaseValue() {
        return (this.isExecutable()) ? shareLimit * (sharePrice - strikePricePerShare - premiumPerShare) : this.getPremium();
    }

    public double getGain() {
        return this.getValue() - this.getPremium();
    }

    public double getGainPercentage() {
        return (this.isExecutable()) ? Math.round(((this.getGain() / this.getPremium()) * 100) * 100.0) / 100.0 : -100.0;
    }
}
