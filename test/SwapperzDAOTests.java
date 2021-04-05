/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import business.User;
import daos.AddressDao;
import daos.ProductDao;
import daos.UserDao;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ME
 */
public class SwapperzDAOTests {

    public SwapperzDAOTests() {
    }

    @BeforeClass
    public static void setUpClass() {
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

    //ADDRESS DAO TESTS
    AddressDao aDao = new AddressDao("swapperz_testdb");

    /**
     * In this test we will test a case where the insert of a new address should
     * be successful.
     */
//    @Test
//    public void newAddressTestSuccess(){
//        int expResult = 1;
//        int actualResult = aDao.newAddress(1, "123", "High St.", "Towney", "Louth", "Ireland");
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This test will test a failure with an int in the string field
     */
//    @Test
//    public void newAddressTestFail_1(){
//        int expResult = 1;
//        int actualResult = aDao.newAddress(1, 0, "High St.", "Towney", "Louth", "Ireland");
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This will test with a string in the int field
     */
//    @Test
//    public void newAddressTestFail_2(){
//        int expResult = 1;
//        int actualResult = aDao.newAddress("1", "123", "High St.", "Towney", "Louth", "Ireland");
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This test will obtain the an address id based off an user id
     */
//    @Test
//    public void getAddressIdTestSuccess(){
//        //the address id
//        int expResult = 1;
//        int userIdTest = 2;
//        int actualResult = aDao.getAddressId(userIdTest);
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This will test a valid userId but expect the wrong address id
     */
//        @Test
//    public void getAddressIdTestFail_1(){
//        //the address id
//        int expResult = 2;
//        int userIdTest = 2;
//        int actualResult = aDao.getAddressId(userIdTest);
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This will test a string as method input
     */
//            @Test
//    public void getAddressIdTestFail_2(){
//        //the address id
//        int expResult = 1;
//        int actualResult = aDao.getAddressId("2");
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This will test the expected result as a string
     */
//    @Test
//    public void getAddressIdTestFail_3() {
//        //the address id
//        String expResult = "1";
//        int userIdTest = 2;
//        int actualResult = aDao.getAddressId(userIdTest);
//        assertEquals(expResult, actualResult);
//    }
    //PRODUCT DAO TESTS
    ProductDao pDao = new ProductDao("swapperz_testdb");

    /**
     * This test will retrieve an arrayList from the db of a particular size for
     * a product type id
     */
//    @Test
//    public void getAllProductsOfTypeSuccess(){
//        //call to get the size
//        int actualResult = pDao.getAllProductsOfType(1).size();
//        //there is only 1 product in db at time of testing
//        int expResult = 1;
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This test will retrieve an arrayList from the db of a particular size for
     * a product type id This will fail as sizes will differ
     */
//    @Test
//    public void getAllProductsOfTypeFail_1(){
//        //call to get the size
//        int actualResult = pDao.getAllProductsOfType(1).size();
//        //there is only 1 product in db at time of testing
//        int expResult = 2;
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This test will retrieve an arraylist from the db of a particular size for
     * a product type id. This will be expected to succeed as we will use a
     * product type id that isn't in the product table. And expect an array list
     * that is 0 in size
     */
//    @Test
//    public void getAllProductsOfTypeFail_2() {
//        //call to get the size
//        int actualResult = pDao.getAllProductsOfType(0).size();
//        //there is only 1 product in db at time of testing
//        int expResult = 0;
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This test will retrieve an arraylist from the db of a particular size for
     * a product type id. This will be expected to fail as we will use a product
     * type id that isn't in the product table. And expect an array list that is
     * 1 in size
     */
//    @Test
//    public void getAllProductsOfTypeFail_3() {
//        //call to get the size
//        int actualResult = pDao.getAllProductsOfType(0).size();
//        //there is only 1 product in db at time of testing
//        int expResult = 1;
//        assertEquals(expResult, actualResult);
//    }
    //USER DAO TESTS
    UserDao uDao = new UserDao("swapperz_testdb");

    /**
     * This test will successfully add a user to the db
     */
//    @Test
//    public void registerSuccess(){
//        int expResult = 1;
//        int actualResult = uDao.register("osama", "Kheireddine", "osama123@gmail.com", "osama123", "xyz", "0987654321");
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This test will fail due to invalid parameter type for registering a user
     */
//    @Test
//    public void registerFail_1(){
//        int expResult = 1;
//        int actualResult = uDao.register(1, "Kheireddine", "osama123@gmail.com", "osama123", "xyz", "0987654321");
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This test will fail due to an extra for registering a user
     */
//    @Test
//    public void registerFail_2() {
//        int expResult = 1;
//        int actualResult = uDao.register("osama", "Kheireddine", "osama123@gmail.com", "osama123", "xyz", "0987654321", "extraParam");
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This test will fail due to a missing parameter for registering a user
     */
//    @Test
//    public void registerFail_3() {
//        int expResult = 1;
//        int actualResult = uDao.register("Kheireddine", "osama123@gmail.com", "osama123", "xyz", "0987654321");
//        assertEquals(expResult, actualResult);
//    }
    /**
     * This test will retrieve a user based off their username
     */
//    @Test
//    public void getUserByUsernameSuccess() {
//        String expResult = "osama123";
//        String actualResult = uDao.getUserByUsername("osama123").getUsername();
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * Will try with a username that does not exist
     */
//    @Test
//    public void getUserByUsernameFail_1() {
//        String expResult = "osama123doesnotexist";
//        String actualResult = uDao.getUserByUsername("osama123").getUsername();
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * This will test a blank username supplied
     */
//    @Test
//    public void getUserByUsernameFail_2() {
//        String expResult = "osama123";
//        String actualResult = uDao.getUserByUsername("").getUsername();
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * This will test null
     */
//    @Test
//    public void getUserByUsernameFail_3() {
//        String expResult = "osama123";
//        String actualResult = uDao.getUserByUsername(null).getUsername();
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * This will test an invalid parameter type
     */
//    @Test
//    public void getUserByUsernameFail_4() {
//        String expResult = "osama123";
//        String actualResult = uDao.getUserByUsername(2).getUsername();
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * This will test a misspelling
     */
//    @Test
//    public void getUserByUsernameFail_5() {
//        String expResult = "osama123";
//        String actualResult = uDao.getUserByUsername("soama123").getUsername();
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * This will test case sensitivity - expect success
     */
//    @Test
//    public void getUserByUsernameCaseSensitivity() {
//        String expResult = "osama123";
//        String actualResult = uDao.getUserByUsername("Osama123").getUsername();
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * This will successfully get userId based off valid username
     */
//    @Test
//    public void getUseridSuccess() {
//        int expResult = 5;
//        int actualResult = uDao.getUserId("osama123");
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * Will fail in getting userid as an id will be a parameter
     */
//    @Test
//    public void getUseridFail_1() {
//        int expResult = 5;
//        int actualResult = uDao.getUserId(1);
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * An invalid username
     */
//    @Test
//    public void getUseridFail_2() {
//        int expResult = 5;
//        int actualResult = uDao.getUserId("soama123");
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * Will test adding an address to a given userId - success
     */
//    @Test
//    public void addAddressSuccess() {
//        boolean expResult = true;
//        boolean actualResult = uDao.addAddress(5, 5);
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * This will try adding based off a username
     */
//    @Test
//    public void addAddressFail_1() {
//        boolean expResult = true;
//        boolean actualResult = uDao.addAddress("osama123", 5);
//        assertEquals(expResult, actualResult);
//    }
    
    /**
     * This will test off a non existent user
     */
//     @Test
//    public void addAddressFail_2() {
//        boolean expResult = true;
//        boolean actualResult = uDao.addAddress(33, 5);
//        assertEquals(expResult, actualResult);
//    }
}
