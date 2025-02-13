package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class DataBaseUnit {
    private String Data;
    public DataBaseUnit(String data) {
        Data = data;
    }
    public ArrayList<String> ConnectAndGetCatData() {
        String jdbcUrl = "jdbc:postgresql://localhost:6543/postgres";
        String username = "postgres";
        String password = "x";
        String DB_Driver = "org.postgresql.Driver";
        ArrayList<String> allData = new ArrayList<>();
        try {
            Class.forName(DB_Driver);
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM public.\"cat\"\n");
            while (result1.next()) {
                allData.add(result1.getInt("Id")
                        + " " + result1.getString("Name")
                        + " " + result1.getInt("Date")
                        + " " + result1.getString("Breed")
                        + " " + result1.getInt("OwnerId")
                        + " " + result1.getString("FriendCats"));
                /*System.out.println(
                        result1.getInt("Id")
                                + "\t" + result1.getString("Name")
                                + "\t" + result1.getInt("Date")
                                + "\t" + result1.getString("Breed")
                                + "\t" + result1.getInt("OwnerId")
                                + "\t" + result1.getString("FriendCats"));*/
            }
            allData.sort(Collections.reverseOrder().reversed());

            //statement.executeUpdate("INSERT INTO public.\"Passport\"\n(id, country, information) values(5, 'Peru', '---')");
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return allData;
    }

    public ArrayList<String> ConnectAndGetUserData() {
        String jdbcUrl = "jdbc:postgresql://localhost:6543/postgres";
        String username = "postgres";
        String password = "x";
        String DB_Driver = "org.postgresql.Driver";
        ArrayList<String> allData = new ArrayList<>();
        try {
            Class.forName(DB_Driver);
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM public.\"owners\"\n");
            while (result1.next()) {
                allData.add(result1.getInt("Id")
                        + " " + result1.getString("Name")
                        + " " + result1.getString("Cats"));
                /*System.out.println(
                        result1.getInt("Id")
                                + "\t" + result1.getString("Name")
                                + "\t" + result1.getString("Cats"));*/
            }
            allData.sort(Collections.reverseOrder().reversed());

            //statement.executeUpdate("INSERT INTO public.\"Passport\"\n(id, country, information) values(5, 'Peru', '---')");
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return allData;
    }
}
