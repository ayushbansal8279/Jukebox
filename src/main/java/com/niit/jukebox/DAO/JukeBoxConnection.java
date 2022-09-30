package com.niit.jukebox.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class JukeBoxConnection {
    public static Connection getJukeBoxConnection() throws Exception {
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Ayush@8279");
        return conn;
    }
}