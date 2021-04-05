/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Product;
import business.ProductImage;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Leigh Briody
 */
public class ProductImageDao extends Dao implements ProductImageDaoInterface {

    public ProductImageDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public String getProdcutImageLocation(int productImageId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String location = null;

        //we will be retruning a product image object so lets set one to null
        ProductImage p = null;

        try {
            con = getConnection();

            String query = "SELECT id , imageLocation from productImage where id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, productImageId);
            rs = ps.executeQuery();

            while (rs.next()) {

                //fields
                location = rs.getString("imageLocation");

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getProdcutImageLocation() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getProdcutImageLocation() method: " + e.getMessage());
            }
        }

        return location;

    }

    //This method will insert the product image location reference into the database
    @Override
    public int insertImageLocation(String imageLocation) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String query = "INSERT INTO productImage  (id , imageLocation) VALUES (null , ?)";

            ps = con.prepareStatement(query);
            ps.setString(1, imageLocation);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception has occured in the insertImageLocation() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the insertImageLocation() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }

    //This method will get the maxId
    @Override
    public int getLastInsertedId() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int lastId = -1;

        try {
            con = getConnection();

            String query = "select max(id) from productImage;";

            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            if (rs.next()) {
                lastId = rs.getInt("max(id)");
            }

        } catch (SQLException e) {
            System.out.println("An exception has occured in the                 System.out.println(\"Exception occured in the finally section of the getLastInsertedId() method: \" + e.getMessage());\n"
                    + "getLastInsertedId() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getLastInsertedId() method: " + e.getMessage());
                e.getMessage();
            }

        }

        return lastId;

    }
    
     //This method will get the maxId
    @Override
    public int deleteRecentRecord() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsEffected = -1;

        try {
            con = getConnection();

            String query = "delete from productImage where id = (select max(id) from productImage);";

            ps = con.prepareStatement(query);

            rowsEffected = ps.executeUpdate();

            

        } catch (SQLException e) {
            System.out.println("An exception has occured in the                 System.out.println(\"Exception occured in the finally section of the deleteRecentRecord() method: \" + e.getMessage());\n"
                    + "getLastInsertedId() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the                 System.out.println(\"Exception occured in the finally section of the deleteRecentRecord() method: \" + e.getMessage());\n" +
"() method: " + e.getMessage());
                e.getMessage();
            }

        }

        return rowsEffected;

    }
    
    
}
