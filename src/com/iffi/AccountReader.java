package com.iffi;

import com.iffi.entity.Account;
import com.iffi.entity.Asset;
import com.iffi.entity.Person;
import com.iffi.io.ReadFile;

import java.util.List;

public class AccountReader {

    public static void main(String[] args) {
        List<Person> persons = ReadFile.readPersonsCSV();
        List<Asset> assets = ReadFile.readAssetsCSV();
        List<Account> accounts = ReadFile.readAccountCSV(persons, assets);
        for (Account account : accounts) {
//            System.out.println(account.toString());
            for (Asset asset : account.getAssets()) {
                System.out.println(asset.toString());
                System.out.println();
            }
        }


    }
}
