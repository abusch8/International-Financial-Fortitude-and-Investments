package com.iffi.entity;

import java.util.List;

public class Pro extends Account {

    public Pro(String number, Person owner, Person manager, Person beneficiary, List<Asset> assets) {
        super(number, owner, manager, beneficiary, assets);
    }

    public double getFeeDiscount() {
        return 0.25;
    }
}
