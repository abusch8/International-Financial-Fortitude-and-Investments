package com.iffi.io;

import com.google.gson.GsonBuilder;
import com.iffi.entity.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class WriteFile {

    public static void convertPersonsToXML(List<Person> persons) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("persons", List.class);
        xstream.alias("person", Person.class);
        xstream.alias("email", String.class);
        try {
            BufferedWriter xml = new BufferedWriter(new FileWriter("data/Persons.xml"));
            xml.write("<?xml version=\"1.0\"?>\n" + xstream.toXML(persons));
            xml.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertAssetsToXML(List<Asset> assets) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("assets", List.class);
        xstream.alias("property", Property.class);
        xstream.alias("stock", Stock.class);
        xstream.alias("crypto", Cryptocurrency.class);
        try {
            BufferedWriter xml = new BufferedWriter(new FileWriter("data/Assets.xml"));
            xml.write("<?xml version=\"1.0\"?>\n" + xstream.toXML(assets));
            xml.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertPersonsToJSON(List<Person> persons) {
        try {
            BufferedWriter json = new BufferedWriter(new FileWriter("data/Persons.json"));
            json.write("{\n\"persons\": " + new GsonBuilder().setPrettyPrinting().create().toJson(persons) + "}");
            json.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertAssetsToJSON(List<Asset> assets) {
        try {
            BufferedWriter json = new BufferedWriter(new FileWriter("data/Assets.json"));
            json.write("{\n\"assets\": " + new GsonBuilder().setPrettyPrinting().create().toJson(assets) + "}"); // TODO Fix
            json.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
