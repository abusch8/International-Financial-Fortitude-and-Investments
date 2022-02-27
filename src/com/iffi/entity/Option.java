package com.iffi.entity;

import java.time.LocalDate;

public abstract class Option extends Stock {

    private LocalDate purchaseDate;
    protected double strikePricePerShare;
    protected double shareLimit;
    protected double premiumPerShare;
    private LocalDate strikeDate;

    protected Option(Stock stock, LocalDate purchaseDate, double strikePricePerShare, double shareLimit, double premiumPerShare, LocalDate strikeDate) {
        super(stock.getCode(), stock.getLabel(), stock.getSymbol(), stock.getSharePrice());
        this.purchaseDate = purchaseDate;
        this.strikePricePerShare = strikePricePerShare;
        this.shareLimit = shareLimit;
        this.premiumPerShare = premiumPerShare;
        this.strikeDate = strikeDate;
    }
}
