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

    public abstract double getFeeDiscount();

    public double getTotalValue() {
        double total = 0;
        for (Asset asset : assets) {
            total += asset.getValue();
        }
        return total;
    }

    public double getTotalPurchaseValue() {
        double total = 0;
        for (Asset asset : assets) {
            total += asset.getPurchaseValue();
        }
        return total;
    }

    public double getTotalFees() {
        double total = 0;
        for (Asset asset : assets) {
            total += asset.getFee();
        }
        total *= (1 - this.getFeeDiscount());
        if (this.getTotalValue() > 500000) {
            total *= 0.75;
        }
        return total;
    }

    public double getTotalGain() {
        return this.getTotalValue() - this.getTotalPurchaseValue();
    }

    public double getTotalGainPercentage() {
        return (this.getTotalGain() / this.getTotalPurchaseValue()) * 100;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================\n");
        sb.append(String.format("||%6s Account %-20s||\n", this.getClass().getSimpleName(), number));
        sb.append("=======================================\n");
        sb.append("+---------+\n");
        sb.append("|  Owner  |\n");
        sb.append("+---------+\n");
        sb.append(String.format("%s\n", owner.toString()));
        sb.append("+-----------+\n");
        sb.append("|  Manager  |\n");
        sb.append("+-----------+\n");
        sb.append(String.format("%s\n", manager.toString()));
        sb.append("+---------------+\n");
        sb.append("|  Beneficiary  |\n");
        sb.append("+---------------+\n");
        sb.append(String.format("%s\n", beneficiary.toString()));
        sb.append("+----------------+\n");
        sb.append("|  Assets        |\n");
        sb.append("+----------------+\n");
        for (Asset asset : assets) {
            sb.append(asset.toString());
        }
        sb.append("+----------------+\n");
        sb.append("|  Totals        |\n");
        sb.append("+----------------+\n");
        sb.append(String.format("Total Value:         $%12.2f\n", this.getTotalValue()));
        sb.append(String.format("Cost Basis:          $%12.2f\n", this.getTotalPurchaseValue()));
        sb.append(String.format("Total Account Fees:  $%12.2f\n", this.getTotalFees()));
        sb.append(String.format("Total Return:        $%12.2f\n", this.getTotalGain()));
        sb.append(String.format("Total Return %%:      $%12.2f\n", this.getTotalGainPercentage()));
        return sb.toString();
    }
}