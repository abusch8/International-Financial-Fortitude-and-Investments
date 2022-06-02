package com.iffi.sql;

import com.iffi.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static List<Person> readPersonDB() {
        List<Person> persons = new ArrayList<>();
        Connection conn = Database.connect();

        String query1 = "select Person.id as id, code, lastName, firstName, street, city, state, zip, country from Person " +
                "join Address on Person.addressId = Address.id " +
                "join State on State.id = Address.stateId " +
                "join Country on Country.id = Address.countryId;";
        String query2 = "select email from Email where personId = ?;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query1);
            rs = ps.executeQuery();
            while (rs.next()) {
                int personId = rs.getInt("id");
                String code = rs.getString("code");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String country = rs.getString("country");
                Address address = new Address(street, city, state, zip, country);
                List<String> emails = new ArrayList<>();
                ps = conn.prepareStatement(query2);
                ps.setInt(1, personId);
                ResultSet prev = rs;
                rs = ps.executeQuery();
                while (rs.next()) {
                    emails.add(rs.getString("email"));
                }
                rs = prev;
                persons.add(new Person(code, lastName, firstName, address, emails));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            Database.disconnect(rs, ps, conn);
        }
        return persons;
    }

    public static List<Asset> readAssetDB() {
        List<Asset> assets = new ArrayList<>();
        Connection conn = Database.connect();

        String query = "select type, code, label, appraisedValue, exchangeRate, exchangeFeeRate, symbol, sharePrice from Asset";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                String code = rs.getString("code");
                String label = rs.getString("label");
                switch (type) {
                    case "P" -> {
                        double appraisedValue = rs.getDouble("appraisedValue");
                        assets.add(new Property(code, label, appraisedValue));
                    }
                    case "C" -> {
                        double exchangeRate = rs.getDouble("exchangeRate");
                        double exchangeFeeRate = rs.getDouble("exchangeFeeRate");
                        assets.add(new Cryptocurrency(code, label, exchangeRate, exchangeFeeRate));
                    }
                    case "S" -> {
                        String symbol = rs.getString("symbol");
                        double sharePrice = rs.getDouble("sharePrice");
                        assets.add(new Stock(code, label, symbol, sharePrice));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.disconnect(rs, ps, conn);
        }
        return assets;
    }

    public static List<Account> readAccountDB(List<Person> persons, List<Asset> assets) {
        List<Account> accounts = new ArrayList<>();
        Connection conn = Database.connect();

        String query1 = "select Account.id as id, type, number, owner.code as ownerCode, manager.code as managerCode, beneficiary.code as beneficiaryCode from Account " +
                "join Person owner on Account.ownerId = owner.id " +
                "join Person manager on Account.managerId = manager.id " +
                "join Person beneficiary on Account.beneficiaryId = beneficiary.id;";
        String query2 = "select code, purchaseDate, purchasePrice, purchaseExchangeRate, numberOfCoins, purchaseSharePrice, " +
                "numberOfShares, dividendTotal, optionType, strikePricePerShare, shareLimit, premiumPerShare, strikeDate " +
                "from OwnedAsset join Asset on OwnedAsset.assetId = Asset.id where accountId = ?;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query1);
            rs = ps.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt("id");
                String type = rs.getString("type");
                String number = rs.getString("number");
                String ownerCode = rs.getString("ownerCode");
                String managerCode = rs.getString("managerCode");
                String beneficiaryCode = rs.getString("beneficiaryCode");
                Person owner = null, manager = null, beneficiary = null;
                for (Person existingPerson : persons) {
                    if (existingPerson.getCode().equals(ownerCode)) owner = existingPerson;
                    if (existingPerson.getCode().equals(managerCode)) manager = existingPerson;
                    if (existingPerson.getCode().equals(beneficiaryCode)) beneficiary = existingPerson;
                }
                List<Asset> associatedAssets = new ArrayList<>();
                ps = conn.prepareStatement(query2);
                ps.setInt(1, accountId);
                ResultSet prev = rs;
                rs = ps.executeQuery();
                while (rs.next()) {
                    String code = rs.getString("code");
                    LocalDate purchaseDate = rs.getDate("purchaseDate").toLocalDate();
                    for (Asset existingAsset : assets) {
                        if (existingAsset.getCode().equals(code)) {
                            switch (existingAsset.getClass().getSimpleName()) {
                                case "Property" -> {
                                    double purchasePrice = rs.getDouble("purchasePrice");
                                    associatedAssets.add(new Property((Property) existingAsset, purchaseDate, purchasePrice));
                                }
                                case "Cryptocurrency" -> {
                                    double purchaseExchangeRate = rs.getDouble("purchaseExchangeRate");
                                    double numberOfCoins = rs.getDouble("numberOfCoins");
                                    associatedAssets.add(new Cryptocurrency((Cryptocurrency) existingAsset, purchaseDate, purchaseExchangeRate, numberOfCoins));
                                }
                                case "Stock" -> {
                                    double purchaseSharePrice = rs.getDouble("purchaseSharePrice");
                                    double numberOfShares = rs.getDouble("numberOfShares");
                                    String optionType = rs.getString("optionType");
                                    switch (optionType) {
                                        case "S" -> {
                                            double dividendTotal = rs.getDouble("dividendTotal");
                                            associatedAssets.add(new Stock((Stock) existingAsset, purchaseDate, purchaseSharePrice, numberOfShares, dividendTotal));
                                        }
                                        case "P" -> {
                                            double strikePricePerShare = rs.getDouble("strikePricePerShare");
                                            double shareLimit = rs.getDouble("shareLimit");
                                            double premiumPerShare = rs.getDouble("premiumPerShare");
                                            LocalDate strikeDate = rs.getDate("strikeDate").toLocalDate();
                                            associatedAssets.add(new Put((Stock) existingAsset, purchaseDate, strikePricePerShare, shareLimit, premiumPerShare, strikeDate));
                                        }
                                        case "C" -> {
                                            double strikePricePerShare = rs.getDouble("strikePricePerShare");
                                            double shareLimit = rs.getDouble("shareLimit");
                                            double premiumPerShare = rs.getDouble("premiumPerShare");
                                            LocalDate strikeDate = rs.getDate("strikeDate").toLocalDate();
                                            associatedAssets.add(new Call((Stock) existingAsset, purchaseDate, strikePricePerShare, shareLimit, premiumPerShare, strikeDate));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                rs = prev;
                Account account = null;
                switch (type) {
                    case "P" -> account = new Pro(number, owner, manager, beneficiary, associatedAssets);
                    case "N" -> account = new Noob(number, owner, manager, beneficiary, associatedAssets);
                }
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.disconnect(rs, ps, conn);
        }
        return accounts;
    }
}
