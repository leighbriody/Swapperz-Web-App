/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Purchase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public class PurchaseDao extends Dao implements PurchaseDaoInterface {

    public PurchaseDao(String databaseName) {
        super(databaseName);
    }

    public static void main(String[] args) {
        PurchaseDao pDao = new PurchaseDao("swapperz");

        System.out.println(pDao.addPurchase(3, 3));
    }

    /**
     * This method will be called when a user has completed their purchase.
     * Returns the num rows affected
     *
     * @param userId
     * @param productId
     * @return Number of rows affected
     */
    @Override
    public int addPurchase(int userId, int productId) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String query = "INSERT INTO purchases (userId, productId) VALUES (?,?)";

            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception has occured in the addPurchase() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addPurchase() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }

    @Override
    public int getPurchaseId(int userId, int productId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int purchaseId = -1;

        try {
            con = getConnection();

            String query = "SELECT id FROM purchases WHERE userId = ? AND productId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            rs = ps.executeQuery();

            if (rs.next()) {
                purchaseId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getPurchaseId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getPurchaseId() method: " + e.getMessage());
            }
        }
        return purchaseId;
    }
}
