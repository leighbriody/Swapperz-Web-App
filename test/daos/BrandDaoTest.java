/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Brand;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Osama Kheireddine
 */
public class BrandDaoTest {
    
    private static BrandDao bDao;
    
    public BrandDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        bDao = new BrandDao("swapperz_testdb");
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
     * Test of getAllBrands method, of class BrandDao.
     * We will just test for the size.
     * No need for an invalid test as it is fixed.
     * We will get the size by going to our database in phpMyAdmin and running a sql command to get the number of
     * current brands in our database
     */
    @Test
    public void givenGetAllBrands_whenMethodIsCalled_thenReturnTheSizeOfTheBrandsList() {
        System.out.println("getAllBrands");
        int expResult = 13;
        ArrayList<Brand> result = bDao.getAllBrands();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getBrand method, of class BrandDao.
     * We will test a valid id and invalid id
     * We will expect one brand only at a time.
     * We will look for the name of the brand and have them converted to lower case
     */
    @Test
    public void givenGetBrand_whenValidIdIsSupplied_thenReturn1() {
        System.out.println("getBrand() - Valid Id");
        int id = 1;
        String expResult = "nike";
        Brand result = bDao.getBrand(id);
        assertEquals(expResult.toLowerCase(), result.getName().toLowerCase());
    }
    
    /**
     * Test of getBrand method, of class BrandDao.
     * We will test a valid id and invalid id
     * We will expect one brand only at a time.
     * We will look for the name of the brand and have them converted to lower case
     */
    @Test
    public void givenGetBrand_whenInvalidIdIsSupplied_thenReturn0() {
        System.out.println("getBrand() - Invalid Id");
        int id = -100;
        int expResult = 0;
        Brand result = bDao.getBrand(id);
        assertEquals(expResult, result.getId());
    }
    
}
