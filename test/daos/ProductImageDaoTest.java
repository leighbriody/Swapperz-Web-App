package daos;


import daos.ProductImageDao;
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
public class ProductImageDaoTest {

    ProductImageDao productImgDao = new ProductImageDao("swapperz_testdb");

    //Here we will test a valid getProductImageLocation method
    @Test
    public void testGetProductImageLocation() {

        String location = productImgDao.getProdcutImageLocation(3);
        String expectedResult = "images/loginGuy.jpg";

        assertEquals(location, expectedResult);
    }

    //Here we will test an invalid id passed
    @Test
    public void testGetProductImageLocation2() {

        String location = productImgDao.getProdcutImageLocation(99);

        assertEquals(location, null);
    }
    
    //Here we will test the insert image location method
    @Test
    public void insertImageLocation(){
        
       int rowsAffected = productImgDao.insertImageLocation("testLocation");
       
       int expectedRowsEffected = 1;
       
       assertEquals(rowsAffected ,expectedRowsEffected );
       
       
       
       //now we need to remove the inserted image
      productImgDao.deleteRecentRecord();
    }
    
    
}
