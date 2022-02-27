package com.iffi.entity;

import java.time.LocalDate;

public class Call extends Option {

    public Call(Stock stock, LocalDate purchaseDate, double strikePricePerShare, double shareLimit, double premiumPerShare, LocalDate strikeDate) {
        super(stock, purchaseDate, strikePricePerShare, shareLimit, premiumPerShare, strikeDate);
    }
}
