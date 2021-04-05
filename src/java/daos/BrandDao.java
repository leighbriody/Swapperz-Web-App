/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public class BrandDao extends Dao implements BrandDaoInterface {

    public BrandDao(String databaseName) {
        super(databaseName);
    }
    
    /**
     * This method is used to get everything from the brands table, will be used for listings and searching by brand
     * @return ArrayList of Brand type
     */
    @Override
    public ArrayList<Brand> getAllBrands() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Brand> brands = new ArrayList();

        try {
            con = getConnection();

            String query = "CALL getAllBrands()";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                //Get all the data fields
                int id = rs.getInt("id");
                String name = rs.getString("name");

                //create the object
                Brand brand = new Brand(id, name);
                //Add it to the array list.
                brands.add(brand);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllSizes() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllSizes() method: " + e.getMessage());
            }
        }

        return brands;
    }
    
     @Override
    public Brand getBrand(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Brand b = null;

        try {
            conn = getConnection();

            String query = "CALL getBrand(?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {

                //Get all the data fields
                int brandId = rs.getInt("id");
                String brandName = rs.getString("name");

                //create the object
                b = new Brand(brandId, brandName);

            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific user in the getBrand() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getBrand() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getBrand() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return b;
    }
    
       @Override
    public int addNewBrand(String brandName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsEffected = -1;

        try {
            conn = getConnection();

            String query = "INSERT into brands values (null , ? )";
            ps = conn.prepareStatement(query);
            ps.setString(1, brandName);
             rowsEffected = ps.executeUpdate();

           
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific brand in the addNewBrand() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getBrand() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getBrand() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return rowsEffected;
    }

}
