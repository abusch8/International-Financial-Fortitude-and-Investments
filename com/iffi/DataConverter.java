package com.iffi;

import com.iffi.entity.Asset;
import com.iffi.entity.Person;
import com.iffi.io.ReadFile;
import com.iffi.io.WriteFile;

import java.util.List;

public class DataConverter {

    public static void main(String[] args) {
        List<Person> personList = ReadFile.readPersonsCSV();
        List<Asset> assetList = ReadFile.readAssetsCSV();

        WriteFile.convertPersonsToXML(personList);
        WriteFile.convertAssetsToXML(assetList);

        WriteFile.convertPersonsToJSON(personList);
        WriteFile.convertAssetsToJSON(assetList);
    }
}
