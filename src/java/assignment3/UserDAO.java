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
import java.text.*;
import java.util.*;
import java.sql.*;

public class UserDAO {

    static Connection currentCon = null;
    static ResultSet rs = null;

    public static UserBean signup(UserBean bean) throws SQLException {
        
        //preparing some objects for connection 
        String firstName = bean.getFirstName();
        String lastName = bean.getLastName();
        String username = bean.getUsername();
        String password = bean.getPassword();

        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO USERS"
                + "(FIRSTNAME, LASTNAME, USERNAME, PASSWORD) VALUES"
                + "(?,?,?,?)";

        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            preparedStatement = currentCon.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            bean.setValid(true);

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (currentCon != null) {
                currentCon.close();
            }

        }

        return bean;
    }

    public static UserBean login(UserBean bean) {

        //preparing some objects for connection 
        Statement stmt = null;

        String username = bean.getUsername();
        String password = bean.getPassword();

        String searchQuery
                = "select * from users where username='"
                + username
                + "' AND password='"
                + password
                + "'";

        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more) {
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                bean.setValid(false);
            } //if user exists set the isValid variable to true
            else if (more) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                System.out.println("Welcome " + firstName);
                bean.setFirstName(firstName);
                bean.setLastName(lastName);
                bean.setValid(true);
            }
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }

        return bean;

    }
}
