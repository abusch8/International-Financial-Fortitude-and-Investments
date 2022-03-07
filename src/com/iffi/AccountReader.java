package com.iffi;

import com.iffi.entity.Account;
import com.iffi.entity.Asset;
import com.iffi.entity.Person;
import com.iffi.io.FileParser;

import java.util.List;

public class AccountReader {

    public static void main(String[] args) {
        List<Person> persons = FileParser.readPersonsCSV();
        List<Asset> assets = FileParser.readAssetsCSV();
        List<Account> accounts = FileParser.readAccountCSV(persons, assets);

        double totalFees = 0.0, totalGain = 0.0, totalValue = 0.0;
        System.out.println("Account Summary Report By Owner");
        System.out.println("==============================================================================================================");
        System.out.println("Account    Owner                Manager                       Fees          Return         Ret%          Value");
        for (Account account : accounts) {
            System.out.printf("%-10s %-20s %-20s $%12.2f   $%12.2f %12.2f  $%12.2f\n", account.getNumber(), account.getOwner().getName(), account.getManager().getName(), account.getTotalFees(), account.getTotalGain(), account.getTotalGainPercentage(), account.getTotalValue());
            totalFees += account.getTotalFees();
            totalGain += account.getTotalGain();
            totalValue += account.getTotalValue();
        }
        System.out.println("                                                     ---------------------------------------------------------");
        System.out.printf("                                              Totals $%12.2f   $%12.2f               $%12.2f\n\n\n\n\n", totalFees, totalGain, totalValue);
        System.out.println("Account Details");
        System.out.println("================================================================================================================");
        for (Account account : accounts) {
            System.out.println(account.toString());
        }
    }
}
