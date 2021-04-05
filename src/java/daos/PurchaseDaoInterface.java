/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Purchase;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public interface PurchaseDaoInterface {
    public int getPurchaseId(int userId, int productId);
    public int addPurchase(int userId, int productId);
}
