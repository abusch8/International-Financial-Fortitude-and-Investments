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

    public double getTotalValue() {
        double total = 0;
        for (Asset asset : assets) {
            total += asset.getValue();
        }
        return total;
    }

    public double getCostBasis() {
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
        if (this instanceof Pro) {
            total *= 0.75;
        }
        return total;
    }

    public double getTotalGain() {
        double total = 0;
        for (Asset asset : assets) {
            total += asset.getGain();
        }
        return total;
    }

    public double getTotalGainPercentage() {
        double total = 0;
        for (Asset asset : assets) {
            total += asset.getGainPercentage();
        }
        return total;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("=======================================\n||%6s Account %-20s||\n=======================================\n", this.getClass().getSimpleName(), number));
        sb.append(String.format("+---------+\n|  Owner  |\n+---------+\n%s\n", owner.toString()));
        sb.append(String.format("+-----------+\n|  Manager  |\n+-----------+\n%s\n", manager.toString()));
        sb.append(String.format("+---------------+\n|  Beneficiary  |\n+---------------+\n%s\n", beneficiary.toString()));
        sb.append("+----------------+\n|  Assets        |\n+----------------+\n");
        for (Asset asset : assets) {
            sb.append(asset.toString());
        }
        sb.append("+----------------+\n|  Totals        |\n+----------------+\n");
        sb.append(String.format("Total Value:         $%12.2f\n", this.getTotalValue()));
        sb.append(String.format("Cost Basis:          $%12.2f\n", this.getCostBasis()));
        sb.append(String.format("Total Account Fees:  $%12.2f\n", this.getTotalFees()));
        sb.append(String.format("Total Return:        $%12.2f\n", this.getTotalGain()));
        sb.append(String.format("Total Return %%:      $%12.2f\n", this.getTotalGainPercentage()));
        return sb.toString();
    }
}