package com.iffi.sql;

import com.iffi.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static Address retrieveAddress(int addressId) {
        Address address = null;
        Connection conn = Database.connect();

        String query = "select street, city, state, zip, country from Address where id = ?;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, addressId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = String.valueOf(rs.getInt("zip"));
                String country = rs.getString("country");

                address = new Address(street, city, state, zip, country);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        } finally {
            Database.disconnect(rs, ps, conn);
        }
        return address;
    }

    public static String retrieveEmail(int emailId) {
        String email = null;
        Connection conn = Database.connect();

        String query = "select email from Email where id = ?;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, emailId);
            rs = ps.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        } finally {
            Database.disconnect(rs, ps, conn);
        }
        return email;
    }

    public static Person retrievePerson(int personId) {
        Person person = null;
        Connection conn = Database.connect();

        String query1 = "select code, lastName, firstName, addressId from Person where id = ?;";
        String query2 = "select id from Email where personId = ?;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query1);
            ps.setInt(1, personId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String personCode = rs.getString("code");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                Address address = retrieveAddress(rs.getInt("addressId"));

                ps = conn.prepareStatement(query2);
                ps.setInt(1, personId);
                rs = ps.executeQuery();

                List<String> emails = new ArrayList<>();
                while (rs.next()) {
                    emails.add(retrieveEmail(rs.getInt("id")));
                }
                person = new Person(personCode, lastName, firstName, address, emails);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.disconnect(rs, ps, conn);
        }
        return person;
    }

    public static Asset retrieveAsset(int assetId) {
        Asset asset = null;
        Connection conn = Database.connect();

        String query = "select type, code, label, appraisedValue, exchangeRate, exchangeFeeRate, symbol, sharePrice from Asset where id = ?;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, assetId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String code = rs.getString("code");
                String label = rs.getString("label");
                switch (rs.getString("type")) {
                    case "P" -> asset = new Property(code, label, rs.getDouble("appraisedValue"));
                    case "C" -> asset = new Cryptocurrency(code, label, rs.getDouble("exchangeRate"), rs.getDouble("exchangeFeeRate"));
                    case "S" -> asset = new Stock(code, label, rs.getString("symbol"), rs.getDouble("sharePrice"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.disconnect(rs, ps, conn);
        }
        return asset;
    }

//    public static Asset retrieveOwnedAsset(int assetId) {
//        Asset asset = null;
//        Connection conn = Database.connect();
//
//        String query = "select assetId, purchaseDate, purchasPrice, purchasExchangeRate, numberOfCoins, purchaseSharePrice, numberOfShares, dividendTotal, optionType, strikePricePerShare, shareLimit, premiumPerShare, strikeDate from OwnedAsset where assetId = ?;";
//
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setInt(1, assetId);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                asset = retrieveAsset(rs.getInt(assetId));
//                switch (asset.getClass().getSimpleName()) {
//                    case "Property" -> asset = new Property((Property) asset, )
//                    case "Cryptocurrency" -> asset = new Cryptocurrency((Cryptocurrency) asset, )
//                    case "Stock" -> {
//
//                    }
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            Database.disconnect(rs, ps, conn);
//        }
//        return existingAsset;
//    }

//    public static Account retrieveAccount(int accountId) {
//        Account account = null;
//        Connection conn = Database.connect();
//
//        String query1 = "select type, number, ownerId, managerId, beneficiaryId from Account where id = ?;";
//        String query2 = "select id from OwnedAsset where accountId = ?;";
//
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = conn.prepareStatement(query1);
//            ps.setInt(1, accountId);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String type = rs.getString("type");
//                String number = rs.getString("number");
//                Person owner = retrievePerson(rs.getInt("ownerId"));
//                Person manager = retrievePerson(rs.getInt("managerId"));
//                Person beneficiary = retrievePerson(rs.getInt("beneficiaryId"));
//
//                ps = conn.prepareStatement(query2);
//                ps.setInt(1, accountId);
//                rs = ps.executeQuery();
//
//                List<Asset> ownedAssets = new ArrayList<>();
//                while (rs.next()) {
////                    asset.add((rs.getInt("")))
//                }
//
//                switch (type) {
//                    case "P" -> account = new Pro(number, owner, manager, beneficiary);
//                    case "N" -> account = new Noob(number, owner, manager, beneficiary);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            Database.disconnect(rs, ps, conn);
//        }
//        return account;
//    }
}
