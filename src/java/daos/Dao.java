/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Osama Kheireddine
 */
public class Dao {

    private Connection conn;

    // By providing a constructor that takes the connection as a parameter
    // we provide a way to take in a mock object and replace our REAL
    // connection with a mock
    public Dao(Connection conn) {
        this.conn = conn;
    }

    private String databaseName;

    public Dao(String databaseName) {
        this.databaseName = databaseName;
    }

    //getConnection will be used to establish a connection
    public Connection getConnection() {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        String username = "root";
        String password = "";
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException ex1) {
            System.out.println("Failed to find the driver class " + ex1.getMessage());
        }
        return con;

    }

    //we also need a method to free the connection 
    public void freeConnection(Connection con) {

        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection " + e.getMessage());
            System.exit(1);
        }
    }
}
