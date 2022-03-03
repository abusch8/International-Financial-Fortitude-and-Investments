package com.iffi.entity;

import java.util.List;

public abstract class Account {

    private final String number;
    private final Person owner;
    private final Person manager;
    private final Person beneficiary;
    private final List<Asset> assets;

    protected Account(String number, Person owner, Person manager, Person beneficiary, List<Asset> assets) {
        this.number = number;
        this.owner = owner;
        this.manager = manager;
        this.beneficiary = beneficiary;
        this.assets = assets;
    }

    public String getNumber() {
        return number;
    }

    public Person getOwner() {
        return owner;
    }

    public Person getManager() {
        return manager;
    }

    public Person getBeneficiary() {
        return beneficiary;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public String toString() {
        return String.format("=======================================\n||%6s Account %-20s||\n=======================================\n", this.getClass().getSimpleName(), number) +
                String.format("+---------+\n|  Owner  |\n+---------+\n%s\n", owner.toString()) +
                String.format("+-----------+\n|  Manager  |\n+-----------+\n%s\n", manager.toString()) +
                String.format("+---------------+\n|  Beneficiary  |\n+---------------+\n%s\n", beneficiary.toString());

//                assets.forEach(asset -> toString());
    }
}