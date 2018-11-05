/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author chaoguo
 */
import java.sql.*;
import java.util.*;

public class ConnectionManager {

    static Connection con;
    static String url;

    public static Connection getConnection() {

        String url = "jdbc:derby://localhost:1527/Chao";
        
        try {
            con = DriverManager.getConnection(url, "IS2560", "IS2560");

            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return con;
    }
}
