/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Colour;
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
public class ColourDaoTest {
    
    private static ColourDao cDao;
    
    public ColourDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        cDao = new ColourDao("swapperz_testdb");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllColours method, of class ColourDao.
     * We will go to phpMyAdmin to get the size of the list of colours and work off that
     */
    @Test
    public void testGetAllColours() {
        System.out.println("getAllColours");
        int expResult = 9;
        ArrayList<Colour> result = cDao.getAllColours();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getColour method, of class ColourDao.
     * we will test for a valid colour id
     */
    @Test
    public void testGetColour() {
        System.out.println("getColour() - valid id");
        int id = 1;
        String expResult = "red";
        Colour result = cDao.getColour(id);
        assertEquals(expResult, result.getColour());
    }
    
}
