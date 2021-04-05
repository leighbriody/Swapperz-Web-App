/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Colour;
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
public class ColourDao extends Dao implements ColourDaoInterface {

    public ColourDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public ArrayList<Colour> getAllColours() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Colour> colours = new ArrayList();

        try {
            con = getConnection();

            String query = "CALL getAllColours() ";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                //Get all the data fields
                int id = rs.getInt("id");
                String c = rs.getString("colour");

                //create the object
                Colour colour = new Colour(id, c);
                //Add it to the array list.
                colours.add(colour);
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

        return colours;
    }

    @Override
    public Colour getColour(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Colour c = null;

        try {
            conn = getConnection();

            String query = "CALL getColour(?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {

                //Get all the data fields
                int colourId = rs.getInt("id");
                String colour = rs.getString("colour");

                //create the object
                c = new Colour(colourId, colour);

            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific user in the getColour() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getColour() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getColour() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return c;
    }

}
