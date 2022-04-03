package com.iffi.io;

import com.iffi.entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {

    public static List<Person> readPersonsCSV() {
        List<Person> persons = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/Persons.csv"));
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                String[] tokens = reader.readLine().split(",");
                Address address = new Address(tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
                List<String> emails = new ArrayList<>(Arrays.asList(tokens).subList(8, tokens.length));
                Person person = new Person(tokens[0], tokens[1], tokens[2], address, emails);
                persons.add(person);
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public static List<Asset> readAssetsCSV() {
        List<Asset> assets = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/Assets.csv"));
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                Asset asset = null;
                String[] tokens = reader.readLine().split(",");
                switch (tokens[1]) {
                    case "P" -> asset = new Property(tokens[0], tokens[2], Double.parseDouble(tokens[3]));
                    case "S" -> asset = new Stock(tokens[0], tokens[2], tokens[3], Double.parseDouble(tokens[4]));
                    case "C" -> asset = new Cryptocurrency(tokens[0], tokens[2], Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]) / 100);
                }
                assets.add(asset);
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return assets;
    }

    public static List<Account> readAccountsCSV(List<Person> persons, List<Asset> assets) {
        List<Account> accounts = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/Accounts.csv"));
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                Account account = null;
                Person owner = null, manager = null, beneficiary = null;
                String[] tokens = reader.readLine().split(",");
                for (Person existingPerson : persons) {
                    if (existingPerson.getPersonCode().equals(tokens[2])) owner = existingPerson;
                    if (existingPerson.getPersonCode().equals(tokens[3])) manager = existingPerson;
                    if (existingPerson.getPersonCode().equals(tokens[4])) beneficiary = existingPerson;
                }
                List<Asset> associatedAssets = new ArrayList<>();
                int j = 5;
                while (j < tokens.length) {
                    for (Asset existingAsset : assets) {
                        if (existingAsset.getCode().equals(tokens[j])) {
                            switch (existingAsset.getClass().getSimpleName()) {
                                case "Property" -> associatedAssets.add(new Property((Property) existingAsset, LocalDate.parse(tokens[++j]), Double.parseDouble(tokens[++j])));
                                case "Cryptocurrency" -> associatedAssets.add(new Cryptocurrency((Cryptocurrency) existingAsset, LocalDate.parse(tokens[++j]), Double.parseDouble(tokens[++j]), Double.parseDouble(tokens[++j])));
                                case "Stock" -> {
                                    switch (tokens[++j]) {
                                        case "S" -> associatedAssets.add(new Stock((Stock) existingAsset, LocalDate.parse(tokens[++j]), Double.parseDouble(tokens[++j]), Double.parseDouble(tokens[++j]), Double.parseDouble(tokens[++j])));
                                        case "P" -> associatedAssets.add(new Put((Stock) existingAsset, LocalDate.parse(tokens[++j]), Double.parseDouble(tokens[++j]), Double.parseDouble(tokens[++j]), Double.parseDouble(tokens[++j]), LocalDate.parse(tokens[++j])));
                                        case "C" -> associatedAssets.add(new Call((Stock) existingAsset, LocalDate.parse(tokens[++j]), Double.parseDouble(tokens[++j]), Double.parseDouble(tokens[++j]), Double.parseDouble(tokens[++j]), LocalDate.parse(tokens[++j])));
                                    }
                                }
                            }
                            j++;
                            break;
                        }
                    }
                }
                switch (tokens[1]) {
                    case "P" -> account = new Pro(tokens[0], owner, manager, beneficiary, associatedAssets);
                    case "N" -> account = new Noob(tokens[0], owner, manager, beneficiary, associatedAssets);
                }
                accounts.add(account);
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }
}
