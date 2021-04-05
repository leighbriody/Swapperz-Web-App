/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Product;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ProductDaoTest {

    private static ProductDao pDao;

    public ProductDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        pDao = new ProductDao("swapperz_testdb");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    //for common code
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of allProductsOfCategoryChosen method, of class ProductDao. We will
     * test 1 valid parameter and 1 invalid parameter
     */
    @Test
    public void givenAllProductsOfCategoryChosen_whenValidIsEntered_ReturnSize() {
        System.out.println("allProductsOfCategoryChosen() - Valid Test");
        String productCategory = "menshoodies";
        int expResult = 3;
        ArrayList<Product> result = pDao.allProductsOfCategoryChosen(productCategory);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of allProductsOfCategoryChosen method, of class ProductDao. We will
     * test 1 valid parameter and 1 invalid parameter
     */
    @Test
    public void givenAllProductsOfCategoryChosen_whenInvalidIsEnteredString_Return0() {
        System.out.println("allProductsOfCategoryChosen() - Invalid Test");
        String productCategory = "dogs";
        int expResult = 0;
        ArrayList<Product> result = pDao.allProductsOfCategoryChosen(productCategory);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of addProduct method, of class ProductDao. We will test the string
     * and int inputs
     */
    @Test
    public void givenAddProduct_whenValidIsEntered_Return1() {
        System.out.println("addProduct() - success");
        int userId = 3;
        String productName = "testProduct";
        String description = "This is a test";
        int colour = 1;
        int brand = 2;
        double price = 0.0;
        String size = "4";
        String productCondition = "brandnew";
        int productImage = 3;
        String productGender = "male";
        String productCategory = "womenstrainers";
        int expResult = 1;
        int result = pDao.addProduct(userId, productName, description, colour, brand, price, size, productCondition, productImage, productGender, productCategory);
        assertEquals(expResult, result);
    }

    /**
     * Test of addProduct method, of class ProductDao. We will test the string
     * and int inputs
     */
    @Test
    public void givenAddProduct_whenInvalidStringIsEntered_thenReturn0() {
        System.out.println("addProduct() - fail on string");
        int userId = 3;
        String productName = "testProduct";
        String description = "This is a test";
        int colour = 1;
        int brand = 2;
        double price = 0.0;
        String size = "4";
        String productCondition = "brandnew";
        int productImage = 3;
        String productGender = "running";
        String productCategory = "womenstrainers";
        int expResult = 0;
        int result = pDao.addProduct(userId, productName, description, colour, brand, price, size, productCondition, productImage, productGender, productCategory);
        assertEquals(expResult, result);
    }

    /**
     * Test of addProduct method, of class ProductDao. We will test the string
     * and int inputs
     */
    @Test
    public void givenAddProduct_whenInvalidIntIsEntered_thenReturn0() {
        System.out.println("addProduct() - fail on Integer");
        int userId = 3;
        String productName = "testProduct";
        String description = "This is a test";
        int colour = -1;
        int brand = 2;
        double price = 0.0;
        String size = "4";
        String productCondition = "brandnew";
        int productImage = 3;
        String productGender = "running";
        String productCategory = "womenstrainers";
        int expResult = 0;
        int result = pDao.addProduct(userId, productName, description, colour, brand, price, size, productCondition, productImage, productGender, productCategory);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeSoldStatus method, of class ProductDao. We will test
     * in/valid product Id and status
     */
    @Test
    public void givenChangSoldStatus_whenValidIsEntered_thenReturn1() {
        System.out.println("changeSoldStatus() - Success");
        int productId = 4;
        String status = "sold";
        int expResult = 1;
        int result = pDao.changeSoldStatus(productId, status);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeSoldStatus method, of class ProductDao. We will test
     * in/valid product Id and status
     */
    @Test
    public void givenChangSoldStatus_whenInvalidProductIdIsEntered_thenReturn0() {
        System.out.println("changeSoldStatus() - invalid productId");
        int productId = -1;
        String status = "sold";
        int expResult = 0;
        int result = pDao.changeSoldStatus(productId, status);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeSoldStatus method, of class ProductDao. We will test
     * in/valid product Id and status
     */
    @Test
    public void givenChangSoldStatus_whenInvalidStatusIsEntered_thenReturn0() {
        System.out.println("changeSoldStatus() - invalid Status");
        int productId = 4;
        String status = "test";
        int expResult = 0;
        int result = pDao.changeSoldStatus(productId, status);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchedProducts method, of class ProductDao. Valid & invalid
     * searches for both integer and strings will be used in the tests
     */
    @Test
    public void givenSearchedProducts_whenValidIntTermsIsEntered_thenReturnValid() {
        System.out.println("searchedProducts() - valid int terms");
        HashMap<String, String> stringSearchTerms = new HashMap<String, String>();
        HashMap<String, Integer> intSearchTerms = new HashMap<String, Integer>();
        intSearchTerms.put("colour", 3);
        int expResult = 2;
        ArrayList<Product> result = pDao.searchedProducts(stringSearchTerms, intSearchTerms);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchedProducts method, of class ProductDao. Valid & invalid
     * searches for both integer and strings will be used in the tests
     */
    @Test
    public void givenSearchedProducts_whenInvalidIntTermsIsEntered_thenReturnInvalid() {
        System.out.println("searchedProducts() - invalid int terms");
        HashMap<String, String> stringSearchTerms = new HashMap<String, String>();
        HashMap<String, Integer> intSearchTerms = new HashMap<String, Integer>();
        intSearchTerms.put("colour", -1);
        int expResult = 0;
        ArrayList<Product> result = pDao.searchedProducts(stringSearchTerms, intSearchTerms);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchedProducts method, of class ProductDao. Valid & invalid
     * searches for both integer and strings will be used in the tests
     */
    @Test
    public void givenSearchedProducts_whenValidStringTermsIsEntered_thenReturnValid() {
        System.out.println("searchedProducts() - valid string terms");
        HashMap<String, String> stringSearchTerms = new HashMap<String, String>();
        HashMap<String, Integer> intSearchTerms = new HashMap<String, Integer>();
        stringSearchTerms.put("productCondition", "worn");
        int expResult = 1;
        ArrayList<Product> result = pDao.searchedProducts(stringSearchTerms, intSearchTerms);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchedProducts method, of class ProductDao. Valid & invalid
     * searches for both integer and strings will be used in the tests
     */
    @Test
    public void givenSearchedProducts_whenInvalidStringTermsIsEntered_thenReturnInvalid() {
        System.out.println("searchedProducts() - valid string terms");
        HashMap<String, String> stringSearchTerms = new HashMap<String, String>();
        HashMap<String, Integer> intSearchTerms = new HashMap<String, Integer>();
        stringSearchTerms.put("status", "test");
        int expResult = 0;
        ArrayList<Product> result = pDao.searchedProducts(stringSearchTerms, intSearchTerms);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchedProducts method, of class ProductDao. Valid & invalid
     * searches for both integer and strings will be used in the tests
     */
    @Test
    public void givenSearchedProducts_whenNothingIsEntered_thenReturn0() {
        System.out.println("searchedProducts() - no search terms");
        HashMap<String, String> stringSearchTerms = new HashMap<String, String>();
        HashMap<String, Integer> intSearchTerms = new HashMap<String, Integer>();
        int expResult = 0;
        ArrayList<Product> result = pDao.searchedProducts(stringSearchTerms, intSearchTerms);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getNumOfUsersProductsOfStatus method, of class ProductDao.
     * In/Valid user Id,status and also nothing will be tested for this case
     */
    @Test
    public void givenGetNumOfUsersProductsOfStatus_whenAllValidEntered_thenReturn1() {
        System.out.println("getNumOfUsersProductsOfStatus() - all valid");
        int userId = 2;
        String status = "forsale";
        int expResult = 1;
        int result = pDao.getNumOfUsersProductsOfStatus(userId, status);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumOfUsersProductsOfStatus method, of class ProductDao.
     * In/Valid user Id,status and also nothing will be tested for this case
     */
    @Test
    public void givenGetNumOfUsersProductsOfStatus_whenOnlyValidIntEntered_thenReturn0() {
        System.out.println("getNumOfUsersProductsOfStatus() - ont int valid");
        int userId = 2;
        String status = "test";
        int expResult = 0;
        int result = pDao.getNumOfUsersProductsOfStatus(userId, status);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumOfUsersProductsOfStatus method, of class ProductDao.
     * In/Valid user Id,status and also nothing will be tested for this case
     */
    @Test
    public void givenGetNumOfUsersProductsOfStatus_whenOnlyValidStringEntered_thenReturn0() {
        System.out.println("getNumOfUsersProductsOfStatus() - only string valid");
        int userId = -1;
        String status = "worn";
        int expResult = 0;
        int result = pDao.getNumOfUsersProductsOfStatus(userId, status);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumOfUsersProductsOfStatus method, of class ProductDao.
     * In/Valid user Id,status and also nothing will be tested for this case
     */
    @Test
    public void givenGetNumOfUsersProductsOfStatus_whenNothingIsEntered_thenReturn0() {
        System.out.println("getNumOfUsersProductsOfStatus() - nothing valid");
        int userId = -1;
        String status = "test";
        int expResult = 0;
        int result = pDao.getNumOfUsersProductsOfStatus(userId, status);
        assertEquals(expResult, result);
    }

    /**
     * Test of allUsersProductsOfStatus method, of class ProductDao.
     * Valid/invalid userId, status and nothing will be tested
     */
    @Test
    public void givenAllUsersProductsOfStatus_whenAllValidIsEnterd_thenReturnValid() {
        System.out.println("allUsersProductsOfStatus() - All Valid");
        int theUserId = 11;
        String theStatus = "forsale";
        int expResult = 1;
        ArrayList<Product> result = pDao.allUsersProductsOfStatus(theUserId, theStatus);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of allUsersProductsOfStatus method, of class ProductDao.
     * Valid/invalid userId, status and nothing will be tested
     */
    @Test
    public void givenAllUsersProductsOfStatus_whenValidIntIsEnterd_thenReturnInvalid() {
        System.out.println("allUsersProductsOfStatus() - Valid int");
        int theUserId = 11;
        String theStatus = "test";
        int expResult = 0;
        ArrayList<Product> result = pDao.allUsersProductsOfStatus(theUserId, theStatus);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of allUsersProductsOfStatus method, of class ProductDao.
     * Valid/invalid userId, status and nothing will be tested
     */
    @Test
    public void givenAllUsersProductsOfStatus_whenValidStringIsEnterd_thenReturnInvalid() {
        System.out.println("allUsersProductsOfStatus() - Valid string");
        int theUserId = -1;
        String theStatus = "forsale";
        int expResult = 0;
        ArrayList<Product> result = pDao.allUsersProductsOfStatus(theUserId, theStatus);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of allUsersProductsOfStatus method, of class ProductDao.
     * Valid/invalid userId, status and nothing will be tested
     */
    @Test
    public void givenAllUsersProductsOfStatus_whenNothingValidIsEnterd_thenReturnInvalid() {
        System.out.println("allUsersProductsOfStatus() - no valid items");
        int theUserId = -1;
        String theStatus = "test";
        int expResult = 0;
        ArrayList<Product> result = pDao.allUsersProductsOfStatus(theUserId, theStatus);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of withdrawAdd method, of class ProductDao. A valid and invalid
     * productId will be supplied
     */
    @Test
    public void givenWithdrawAdd_whenValidIdSupplied_Return1() {
        System.out.println("withdrawAdd() - valid product id");
        int productId = 12;
        int expResult = 1;
        int result = pDao.withdrawAdd(productId);
        assertEquals(expResult, result);
        //we need it to be an independednt run
        pDao.relistAdd(productId);
    }

    /**
     * Test of withdrawAdd method, of class ProductDao. A valid and invalid
     * productId will be supplied
     */
    @Test
    public void givenWithdrawAdd_whenInvalidIdSupplied_Return0() {
        System.out.println("withdrawAdd() - invalid product id");
        int productId = -1;
        int expResult = 0;
        int result = pDao.withdrawAdd(productId);
        assertEquals(expResult, result);
    }

    /**
     * Test of relistAdd method, of class ProductDao. We will need to withdraw
     * an ad, and as the tests above successfully ran we can use them to have a
     * withdrawn ad to re-list. We will test a valid and invalid productId.
     */
    @Test
    public void givenRelistAdd_whenValidIdSupplied_thenReturn1() {
        int productId = 12;
        pDao.withdrawAdd(productId);
        System.out.println("relistAdd() - valid id supplied");
        int expResult = 1;
        int result = pDao.relistAdd(productId);
        assertEquals(expResult, result);
    }

    /**
     * Test of relistAdd method, of class ProductDao. We will need to withdraw
     * an ad, and as the tests above successfully ran we can use them to have a
     * withdrawn ad to re-list. We will test a valid and invalid productId.
     */
    @Test
    public void givenRelistAdd_whenInvalidIdSupplied_thenReturn0() {
        int productId = -1;
        pDao.withdrawAdd(productId);
        System.out.println("relistAdd() - invalid id supplied");
        int expResult = 0;
        int result = pDao.relistAdd(productId);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAdd method, of class ProductDao. We will test a valid and
     * invalid product to remove. We will then re-list it as we want it to be an
     * independently ran test.
     */
    @Test
    public void givenRemoveAdd_whenValidIdIsSupplied_thenReturn1() {
        System.out.println("removeAdd() - valid productId supplied");
        int productId = 12;
        int expResult = 1;
        int result = pDao.removeAdd(productId);
        assertEquals(expResult, result);
        pDao.relistAdd(productId);
    }

    /**
     * Test of removeAdd method, of class ProductDao. We will test a valid and
     * invalid product to remove.
     */
    @Test
    public void givenRemoveAdd_whenInvalidIdIsSupplied_thenReturn0() {
        System.out.println("removeAdd() - invalid productId supplied");
        int productId = -1;
        int expResult = 0;
        int result = pDao.removeAdd(productId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductById method, of class ProductDao. We will test a valid
     * and invalid product id We will test to see if the seller id is the same
     */
    @Test
    public void givenGetProductById_whenIdIsValid_thenReturn1() {
        System.out.println("getProductById() - valid id supplied");
        int productId = 4;
        int expResult = 6;
        Product result = pDao.getProductById(productId);
        assertEquals(expResult, result.getUserId());
    }

    /**
     * Test of getProductById method, of class ProductDao. We will test a valid
     * and invalid product id We will test to see if the seller id is the same
     */
    @Test
    public void givenGetProductById_whenIdIsInvalid_thenReturn0() {
        System.out.println("getProductById() - invalid id supplied");
        int productId = -1;
        //as the user id won't exist it will be null so we will assert for null
        Product result = pDao.getProductById(productId);
        assertNull(result);
    }

    /**
     * Test of editAdPrice method, of class ProductDao.
     * We will have a test to check valid & invalid input while also testing no valid input
     */
    @Test
    public void givenEditAdPrice_whenAllValidSupplied_thenReturnTrue() {
        System.out.println("editAdPrice() - All Valid");
        int productId = 4;
        double newPrice = 120.0;
        boolean expResult = true;
        boolean result = pDao.editAdPrice(productId, newPrice);
        assertEquals(expResult, result);
        //then to make the test run independently we must reset the price
        pDao.editAdPrice(productId, pDao.getProductById(productId).getOriginalPrice());
    }
    
    /**
     * Test of editAdPrice method, of class ProductDao.
     * We will have a test to check valid & invalid input while also testing no valid input
     */
    @Test
    public void givenEditAdPrice_whenInvalidIdSupplied_thenReturnFalse() {
        System.out.println("editAdPrice() - invalid id");
        int productId = -1;
        double newPrice = 120.0;
        boolean expResult = false;
        boolean result = pDao.editAdPrice(productId, newPrice);
        assertEquals(expResult, result);
    }
    
}
