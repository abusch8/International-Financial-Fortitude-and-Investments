package com.iffi.io;

import com.google.gson.GsonBuilder;
import com.iffi.entity.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class WriteFile {

    public static void convertPersonsToXML(List<Person> personList) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("persons", List.class);
        xstream.alias("person", Person.class);

        ClassAliasingMapper mapper = new ClassAliasingMapper(xstream.getMapper());
        mapper.addClassAlias("email", String.class);
        xstream.registerLocalConverter(Person.class, "emails", new CollectionConverter(mapper));

        try {
            BufferedWriter xml = new BufferedWriter(new FileWriter("data/Persons.xml"));
            xml.write("<?xml version=\"1.0\"?>\n");
            xml.write(xstream.toXML(personList));
            xml.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertAssetsToXML(List<Asset> assetList) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("assets", List.class);
        xstream.alias("property", Property.class);
        xstream.alias("stock", Stock.class);
        xstream.alias("crypto", Cryptocurrencies.class);

        try {
            BufferedWriter xml = new BufferedWriter(new FileWriter("data/Asset.xml"));
            xml.write("<?xml version=\"1.0\"?>\n");
            xml.write(xstream.toXML(assetList));
            xml.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertPersonsToJSON(List<Person> personList) {
        try {
            BufferedWriter json = new BufferedWriter(new FileWriter("data/Persons.json"));
            json.write(new GsonBuilder().setPrettyPrinting().create().toJson(personList));
            json.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertAssetsToJSON(List<Asset> assetList) {
        try {
            BufferedWriter json = new BufferedWriter(new FileWriter("data/Assets.json"));
            json.write(new GsonBuilder().setPrettyPrinting().create().toJson(assetList));
            json.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
