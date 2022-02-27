package com.iffi;

import com.iffi.entity.Asset;
import com.iffi.entity.Person;
import com.iffi.io.ReadFile;
import com.iffi.io.WriteFile;

import java.util.List;

public class DataConverter {

    public static void main(String[] args) {
        List<Person> persons = ReadFile.readPersonsCSV();
        List<Asset> assets = ReadFile.readAssetsCSV();

        WriteFile.convertPersonsToXML(persons);
        WriteFile.convertAssetsToXML(assets);

        WriteFile.convertPersonsToJSON(persons);
        WriteFile.convertAssetsToJSON(assets);
    }
}
