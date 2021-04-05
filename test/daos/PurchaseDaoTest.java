package daos;

import daos.PurchaseDao;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leigh Briody
 */
public class PurchaseDaoTest {

    PurchaseDao pDao = new PurchaseDao("swapperz_testdb");

    //Here we will unit test the successfull a successfull add purchase
    @Test
    public void testAddPurchase1() {

        //we will add a purchase for user 11 kyle 
        //for product 11 lacoste hoodie
        int rowsAffected = pDao.addPurchase(11, 11);

        //if success rows effected should be 1 
        int expectedRowsAffected = 1;

        assertEquals(rowsAffected, expectedRowsAffected);

    }

    // we will now test an invalid  purchase method where the user id is wrong
    @Test
    public void testAddPurcahse2() {

        //valid product id but invalid user id
        int rowsAffected = pDao.addPurchase(99, 11);

        //expected result should therefore be 0 as it should not add purchase
        int expectedRowsAffected = 0;

        assertEquals(rowsAffected, expectedRowsAffected);

    }

    //Here we will test a valid get purchase if method 
    @Test
    public void testGetPurchaseId() {

        //Here we will est the record 7 and all which has an id of 16
        int purchaseId = pDao.getPurchaseId(7, 11);

        int expResult = 16;

        assertEquals(expResult, purchaseId);
    }

    //Here we will test a invalid get purchase if method 
    //here we will pass an invalid userId
    @Test
    public void testGetPurchaseId2() {

        //Here we will est the record 7 and all which has an id of 16
        int purchaseId = pDao.getPurchaseId(99, 11);

        int expResult = -1;

        assertEquals(expResult, purchaseId);
    }

    //Here we will test a invalid get purchase if method 
    //here we will pass an invalid productId
    @Test
    public void testGetPurchaseId3() {

        //Here we will est the record 7 and all which has an id of 16
        int purchaseId = pDao.getPurchaseId(11, 1111);

        int expResult = -1;

        assertEquals(expResult, purchaseId);
    }

}
