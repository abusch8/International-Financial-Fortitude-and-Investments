package com.iffi.entity;

import java.time.LocalDate;

public class Put extends Option {

    public Put(Stock stock, LocalDate purchaseDate, double strikePricePerShare, double shareLimit, double premiumPerShare, LocalDate strikeDate) {
        super(stock, purchaseDate, strikePricePerShare, shareLimit, premiumPerShare, strikeDate);
    }
}
