package com.niit.jukebox.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SongsConnection {
    public static Connection getSongsConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Ayush@8279");
        return conn;
    }
}