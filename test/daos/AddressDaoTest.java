/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Address;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ME
 */
public class AddressDaoTest {
    
    private static AddressDao aDao;
    
    public AddressDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        aDao = new AddressDao("swapperz_testdb");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    //common code
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of newAddress method, of class AddressDao.
     * Adding in all valid parameter then one of each for string and in will be tested
     */
    @Test
    public void givenNewAddress_whenValidInputs_thenReturn1() {
        System.out.println("newAddress() - successful");
        int userId = 12;
        String addrLine1 = "testline1";
        String addrLine2 = "testline2";
        String town = "testtown";
        String county = "testcounty";
        String country = "testcountry";
        int expResult = 1;
        int result = aDao.newAddress(userId, addrLine1, addrLine2, town, county, country);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of newAddress method, of class AddressDao.
     * Adding in all valid parameter then one of each for string and in will be tested
     */
    @Test
    public void givenNewAddress_whenInvalidUserId_thenReturn0() {
        System.out.println("newAddress() - fail");
        int userId = -1;
        String addrLine1 = "testline1";
        String addrLine2 = "testline2";
        String town = "testtown";
        String county = "testcounty";
        String country = "testcountry";
        int expResult = 0;
        int result = aDao.newAddress(userId, addrLine1, addrLine2, town, county, country);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddressId method, of class AddressDao.
     * We will test on the most recent test id added (the test one from above)
     * Then we will test an invalid user ID
     */
    @Test
    public void givenGetAddressId_whenInvaliduserId_thenReturn20() {
        System.out.println("getAddressId() - success");
        int userId = 12;
        int expResult = 20;
        int result = aDao.getAddressId(userId);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAddressId method, of class AddressDao.
     * We will test on the most recent test id added (the test one from above)
     * Then we will test an invalid user ID
     */
    @Test
    public void givenGetAddressId_whenInvaliduserId_thenReturn0() {
        System.out.println("getAddressId() - invalid userId");
        int userId = -1;
        int expResult = -1;
        int result = aDao.getAddressId(userId);
        assertEquals(expResult, result);
    }
}
