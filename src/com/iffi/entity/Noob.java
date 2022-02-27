package com.iffi.entity;

import java.util.List;

public class Noob extends Account {

    public Noob(String number, Person owner, Person manager, Person beneficiary, List<Asset> assets) {
        super(number, owner, manager, beneficiary, assets);
    }
}
