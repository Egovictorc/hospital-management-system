package com.helen.hms.dao;

import com.helen.hms.service.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract class DB<T> {
    // Overview: manages database connectivity

    // static String URL = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_4611fbb40488d8a?useSSL=false", USERNAME = "b88107cc611a86",
    //  PASSWORD = "c6d51887";

    // static String URL = "jdbc:mysql://us-cdbr-east-04.cleardb.com/heroku_4f410fd977d9b7c?reconnect=true", USERNAME = "b343647cd267f5",
    //  PASSWORD = "bdbb6d95";

    static String DATABASE = "hms", URL = "jdbc:mysql://localhost:3306/" + DATABASE, USERNAME = "root", PASSWORD = "root";

    static Connection conn;

    abstract int registerPerson(T t);

    abstract int removePerson(T t);

    abstract T getByUsername(String username);

    abstract T getById(int id);

    abstract int updatePerson(T updatedPerson);

    public static Connection connect() {
        // EEFECTS: establishes connection to database, returns Connection object
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            // remote connection
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("conn " + conn);
        } catch (SQLException e) {
            UtilityClass.showAlert("Error connecting to database", e.getMessage());
        }
        return conn;
    }

    public ResultSet getAll(String s) {
        // EEFECTS: returns resultset from database

        String sql = "SELECT * FROM " + s;
        ResultSet rs = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }
}
