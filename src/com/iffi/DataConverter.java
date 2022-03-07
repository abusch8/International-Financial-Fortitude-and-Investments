package com.iffi;

import com.iffi.entity.Asset;
import com.iffi.entity.Person;
import com.iffi.io.FileParser;
import com.iffi.io.FileWriter;

import java.util.List;

public class DataConverter {

    public static void main(String[] args) {
        List<Person> persons = FileParser.readPersonsCSV();
        List<Asset> assets = FileParser.readAssetsCSV();

        FileWriter.convertPersonsToXML(persons);
        FileWriter.convertAssetsToXML(assets);

        FileWriter.convertPersonsToJSON(persons);
        FileWriter.convertAssetsToJSON(assets);
    }
}
