package daos;


import business.Size;
import daos.SizeDao;
import java.util.ArrayList;
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
public class SizeDaoTest {
    
    SizeDao sDao = new SizeDao("swapperz_testdb");
    
    
    //Here we will test the get all sizes method
    @Test
    public void testGetAllSizes(){
        
        //There are a total of  25 different sizes
        ArrayList<Size> allSizes = sDao.getAllSizes();
        
        int expectedSize = 26;
        int actualSize = allSizes.size();
        
        assertEquals(expectedSize , actualSize);
       
    }
    
    
    
}
