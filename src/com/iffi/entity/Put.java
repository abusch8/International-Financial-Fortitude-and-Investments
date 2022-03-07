package com.iffi.entity;

import java.time.LocalDate;

public class Put extends Option {

    public Put(Stock stock, LocalDate purchaseDate, double strikePricePerShare, double shareLimit, double premiumPerShare, LocalDate strikeDate) {
        super(stock, purchaseDate, strikePricePerShare, shareLimit, premiumPerShare, strikeDate);
    }

    public double getValue() {
        return (this.isExecutable()) ? shareLimit * (sharePrice - strikePricePerShare) + this.getPremium() : 0.0;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %s Option %s (Put)\n", code, label, symbol));
        sb.append(String.format("  Sell upto %.3f shares @ $%.2f til %s\n", shareLimit, strikePricePerShare, strikeDate.toString()));
        sb.append(String.format("  Premium of $%.2f/share ($%.2f total)\n", premiumPerShare, this.getPremium()));
        sb.append(String.format("  Share Price: $%.2f\n", sharePrice));
        if (this.isExecutable()) {
            sb.append(String.format("  Short Put Value: %.3f shares @\n\t($%.2f - $%.2f - $%.2f = $%.2f)\n", shareLimit, sharePrice, strikePricePerShare, premiumPerShare, this.getPurchaseValue()));
        } else {
            sb.append(String.format("  Long Put Value: %.3f shares @ $%.2f = $%.2f\n", shareLimit, sharePrice, this.getPurchaseValue()));
        }
        sb.append(String.format("%70.3f%%    $%15.2f\n", this.getGainPercentage(), this.getValue()));
        return sb.toString();
    }
}
