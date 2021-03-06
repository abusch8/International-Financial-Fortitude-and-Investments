package com.iffi.entity;

import java.util.List;

public class Person {

    private final String code;
    private final String lastName;
    private final String firstName;
    private final Address address;
    private final List<String> emails;

    public Person(String personCode, String lastName, String firstName, Address address, List<String> emails) {
        this.code = personCode;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.emails = emails;
    }

    public String getCode() {
        return code;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Address getAddress() {
        return address;
    }

    public List<String> getEmails() {
        return emails;
    }

    public String getName() {
        return lastName + ", " + firstName;
    }

    public String toString() {
        return String.format("%s\n%s\n%s", this.getName(), emails.toString(), address.toString());
    }
}
