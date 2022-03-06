package com.iffi.entity;

import java.time.LocalDate;

public class Put extends Option {

    public Put(Stock stock, LocalDate purchaseDate, double strikePricePerShare, double shareLimit, double premiumPerShare, LocalDate strikeDate) {
        super(stock, purchaseDate, strikePricePerShare, shareLimit, premiumPerShare, strikeDate);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %s Option %s (Put)\n", code, label, symbol));
        sb.append(String.format("  Sell upto %.3f shares @ $.2%f til %s\n", shareLimit, strikePricePerShare, purchaseDate.toString()));
//        sb.append(String.format("  Premium of $.2f/share ($%.2f total)\n", premiumPerShare, ));
//        sb.append(String.format("  Share Price: $%.2f\n", ));
//        if (isShort) {
//            sb.append()
//        } else {
//            sb.append()
//        }

        return sb.toString();
    }
}
