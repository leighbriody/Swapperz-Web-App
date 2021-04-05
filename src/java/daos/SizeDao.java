/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public class SizeDao extends Dao implements SizeDaoInterface {

    public SizeDao(String databaseName) {
        super(databaseName);
    }

    public static void main(String[] args) {
        SizeDao sDao = new SizeDao("swapperz");

        System.out.println(sDao.getAllSizes());
    }

    /**
     * This method is used to get everything from the brands table, will be used
     * for listings and searching by brand
     *
     * @return ArrayList of Brand type
     */
    @Override
    public ArrayList<Size> getAllSizes() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Size> sizes = new ArrayList();

        try {
            con = getConnection();

            String query = "SELECT * from clothingSize";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                //Get all the data fields
                int id = rs.getInt("id");
                String productSize = rs.getString("size");

                //create the object
                Size size = new Size(id, productSize);
                //Add it to the array list.
                sizes.add(size);
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

        return sizes;
    }

    public Size getSize(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Size s = null;

        try {
            conn = getConnection();

            String query = "SELECT * FROM clothingsize WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {

                //Get all the data fields
                int colourId = rs.getInt("id");
                String size = rs.getString("size");

                //create the object
                s = new Size(colourId, size);

            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific user in the getSize() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getSize() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getSize() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return s;
    }

}
