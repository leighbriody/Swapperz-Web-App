/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Osama Kheireddine
 */
public class AddressDao extends Dao implements AddressDaoInterface {
    
    public AddressDao(Connection conn){
        super(conn);
    }

    public AddressDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method will be used once a user is created that is how the userId
     * will be added. The purpose for this is to have a user's address stored in
     * an address table
     *
     * @param userId
     * @param addrLine1
     * @param addrLine2
     * @param town
     * @param county
     * @param country
     * @return
     */
    @Override
    public int newAddress(int userId, String addrLine1, String addrLine2, String town, String county, String country) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String query = "INSERT INTO address (userId, line1, line2, town, county, country) VALUES (?,?,?,?,?,?)";

            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, addrLine1);
            ps.setString(3, addrLine2);
            ps.setString(4, town);
            ps.setString(5, county);
            ps.setString(6, country);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception has occured in the newAddress() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the newAddress() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }

    /**
     * Will mainly be used to obtain the address id by using the userId supplied
     *
     * @param userId
     * @return address id
     */
    @Override
    public int getAddressId(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int addressId = -1;

        try {
            con = getConnection();

            String query = "SELECT id FROM address WHERE userId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                addressId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserId() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserId() method: " + e.getMessage());
            }
        }
        return addressId;
    }

    //this will get the address given the users id
    /**
     * This will return the most recent entry of a user's address
     * @param userId
     * @return 
     */
    @Override
    public Address getUserAddress(int userId) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Address usersAddress = null;

        try {
            con = getConnection();

            String query = "SELECT * from address where userId = ? ORDER BY id DESC";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {

                //Get all the data fields
//                int id = rs.getInt("id");
                int user = rs.getInt("userId");
                String line1 = rs.getString("line1");
                String line2 = rs.getString("line2");
                String town = rs.getString("town");
                String county = rs.getString("county");
                String country = rs.getString("country");

                //public Address(int userId, String addrLine1, String addrLine2, String town, String county, String country) {
                //create the object
                usersAddress = new Address(user, line1, line2, town, county, country);
                //Add it to the array list.

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserAddress() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserAddress() method: " + e.getMessage());
            }
        }

        return usersAddress;

    }

}
