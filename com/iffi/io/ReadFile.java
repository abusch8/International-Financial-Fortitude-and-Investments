package com.iffi.io;

import com.iffi.entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFile {

    public static List<Person> readPersonsCSV() {
        List<Person> persons = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/Persons.csv"));
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                String[] content = reader.readLine().split(",");
                Address address = new Address(content[3], content[4], content[5], content[6], content[7]);
                List<String> emails = new ArrayList<>(Arrays.asList(content).subList(8, content.length));
                Person person = new Person(content[0], content[1], content[2], address, emails);
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
                String[] content = reader.readLine().split(",");
                switch (content[1]) {
                    case "P" -> asset = new Property(content[0], content[2], Double.parseDouble(content[3]));
                    case "S" -> asset = new Stock(content[0], content[2], content[3], Double.parseDouble(content[4]));
                    case "C" -> asset = new Cryptocurrencies(content[0], content[2], Double.parseDouble(content[3]), Double.parseDouble(content[4]));
                }
                assets.add(asset);
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return assets;
    }
}
