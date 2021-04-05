package mocking;


import business.Product;
import daos.ProductDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leigh Briody
 */
public class ProductDaoMockTest {

    @BeforeClass
    public static void setUpClass() {

    }
 @Test
    public void testGetAllProductsOfCategoryMensHoodies() throws SQLException {

        //Here we will test the get all products of a category method with the paramater 'menshoodies'
        //Create Expected result
        ///int id , int userId , String productName , String description , int colour , int brand , double originalPrice , double listedPrice , String status , int size  , String productCondition , int productImage , String productGender , String productCategory){
        Product p1 = new Product(0, 0, "name", "description", 0, 0, 0, 0, "status", 0, "condition", 8, "gender", "category");
        Product p2 = new Product(0, 0, "name", "description", 0, 0, 0, 0, "status", 0, "condition", 8, "gender", "category");
        Product p3 = new Product(0, 0, "name", "description", 0, 0, 0, 0, "status", 0, "condition", 8, "gender", "category");

        ArrayList<Product> expectedResults = new ArrayList();
        expectedResults.add(p1);
        expectedResults.add(p2);
        expectedResults.add(p3);

        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //fill mock objects with dummy data
        when(dbConn.prepareStatement("Select * from Products where productCategory = 'menshoodies' ")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset

        when(rs.getInt("id")).thenReturn(p1.getId(), p2.getId(), p3.getId());
        when(rs.getInt("userId")).thenReturn(p1.getUserId(), p2.getUserId(), p3.getUserId());
        when(rs.getString("productName")).thenReturn(p1.getProductName(), p2.getProductName(), p3.getProductName());
        when(rs.getString("description")).thenReturn(p1.getDescription(), p2.getDescription(), p3.getDescription());
        when(rs.getInt("colour")).thenReturn(p1.getColour(), p2.getColour(), p3.getColour());
        when(rs.getInt("productName")).thenReturn(p1.getBrand(), p2.getBrand(), p3.getBrand());
        when(rs.getDouble("orignalPrice")).thenReturn(p1.getOriginalPrice(), p2.getOriginalPrice(), p3.getOriginalPrice());
        when(rs.getDouble("listenPirce")).thenReturn(p1.getListedPrice(), p2.getListedPrice(), p3.getListedPrice());
        when(rs.getString("status")).thenReturn(p1.getStatus(), p2.getStatus(), p3.getStatus());
        when(rs.getInt("sizeId")).thenReturn(p1.getSize(), p2.getSize(), p3.getSize());
        when(rs.getString("productCondition")).thenReturn(p1.getProductCondition(), p2.getProductCondition(), p3.getProductCondition());
        when(rs.getInt("productImage")).thenReturn(p1.getProductImage(), p2.getProductImage(), p3.getProductImage());
        when(rs.getString("productGender")).thenReturn(p1.getProductGender(), p2.getProductGender(), p3.getProductGender());
        when(rs.getString("productCategory")).thenReturn(p1.getProductCategory(), p2.getProductCategory(), p3.getProductCategory());
        when(rs.getInt("purchaseId")).thenReturn(p1.getPurchaseId(), p2.getPurchaseId(), p3.getPurchaseId());

        int numProductsInTable = 3;

        ProductDao prodDao = new ProductDao("swapperz_testdb");
        List<Product> result = prodDao.allProductsOfCategoryChosen("menshoodies");

        assertEquals(numProductsInTable, result.size());

        System.out.println(result);
    }

    @Test
    public void testAllProductsOfStatus() throws SQLException {

        //Here we will test the allProductsOfStatus with the paramater of forSale
        //Here we will use the user with an id of 3 and status sold which accordinf to the test database should give us 3 reulsts
        //Create the expected result
        Product p1 = new Product(0, 0, "name", "description", 0, 0, 0, 0, "status", 0, "condition", 8, "gender", "category");
        Product p2 = new Product(0, 0, "name", "description", 0, 0, 0, 0, "status", 0, "condition", 8, "gender", "category");
        Product p3 = new Product(0, 0, "name", "description", 0, 0, 0, 0, "status", 0, "condition", 8, "gender", "category");
        Product p4 = new Product(0, 0, "name", "description", 0, 0, 0, 0, "status", 0, "condition", 8, "gender", "category");
        Product p5 = new Product(0, 0, "name", "description", 0, 0, 0, 0, "status", 0, "condition", 8, "gender", "category");

        ArrayList<Product> expectedResults = new ArrayList();
        expectedResults.add(p1);
        expectedResults.add(p2);
        expectedResults.add(p3);
        expectedResults.add(p4);
        expectedResults.add(p5);

        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //fill mock objects with dummy data
        when(dbConn.prepareStatement("Select * from Products where STATUS = 'forSale' ")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 5 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, true, true, false);
        // Fill in the resultset

        // Fill in the resultset
        when(rs.getInt("id")).thenReturn(p1.getId(), p2.getId(), p3.getId());
        when(rs.getInt("userId")).thenReturn(p1.getUserId(), p2.getUserId(), p3.getUserId());
        when(rs.getString("productName")).thenReturn(p1.getProductName(), p2.getProductName(), p3.getProductName());
        when(rs.getString("description")).thenReturn(p1.getDescription(), p2.getDescription(), p3.getDescription());
        when(rs.getInt("colour")).thenReturn(p1.getColour(), p2.getColour(), p3.getColour());
        when(rs.getInt("productName")).thenReturn(p1.getBrand(), p2.getBrand(), p3.getBrand());
        when(rs.getDouble("orignalPrice")).thenReturn(p1.getOriginalPrice(), p2.getOriginalPrice(), p3.getOriginalPrice());
        when(rs.getDouble("listenPirce")).thenReturn(p1.getListedPrice(), p2.getListedPrice(), p3.getListedPrice());
        when(rs.getString("status")).thenReturn(p1.getStatus(), p2.getStatus(), p3.getStatus());
        when(rs.getInt("sizeId")).thenReturn(p1.getSize(), p2.getSize(), p3.getSize());
        when(rs.getString("productCondition")).thenReturn(p1.getProductCondition(), p2.getProductCondition(), p3.getProductCondition());
        when(rs.getInt("productImage")).thenReturn(p1.getProductImage(), p2.getProductImage(), p3.getProductImage());
        when(rs.getString("productGender")).thenReturn(p1.getProductGender(), p2.getProductGender(), p3.getProductGender());
        when(rs.getString("productCategory")).thenReturn(p1.getProductCategory(), p2.getProductCategory(), p3.getProductCategory());
        when(rs.getInt("purchaseId")).thenReturn(p1.getPurchaseId(), p2.getPurchaseId(), p3.getPurchaseId());

        int numProductsInTable = 24;

        ProductDao prodDao = new ProductDao("swapperz_testdb");
        List<Product> result = prodDao.allUsersProductsOfStatus(3, "forSale");

        assertEquals(numProductsInTable, result.size());

        System.out.println(result);

    }

    @Test
    public void testGetProductById() throws SQLException {

        //create the expected result
        Product p1 = new Product(3, 2, "Nike Tech Hoodie", "old hoodie selling cheap", 1, 1, 60, 50, "forSale", 4, "brandnew", 1, "male", "menshoodies", 16);

        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //fill mock objects with dummy data
        when(dbConn.prepareStatement("Select * from Products where id = 3 ")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 1 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);
        // Fill in the resultset
        when(rs.getInt("id")).thenReturn(p1.getId());
        when(rs.getInt("userId")).thenReturn(p1.getUserId());
        when(rs.getString("productName")).thenReturn(p1.getProductName());
        when(rs.getString("description")).thenReturn(p1.getDescription());
        when(rs.getInt("colour")).thenReturn(p1.getColour());
        when(rs.getInt("productName")).thenReturn(p1.getBrand());
        when(rs.getDouble("orignalPrice")).thenReturn(p1.getOriginalPrice());
        when(rs.getDouble("listenPirce")).thenReturn(p1.getListedPrice());
        when(rs.getString("status")).thenReturn(p1.getStatus());
        when(rs.getInt("sizeId")).thenReturn(p1.getSize());
        when(rs.getString("productCondition")).thenReturn(p1.getProductCondition());
        when(rs.getInt("productImage")).thenReturn(p1.getProductImage());
        when(rs.getString("productGender")).thenReturn(p1.getProductGender());
        when(rs.getString("productCategory")).thenReturn(p1.getProductCategory());
        when(rs.getInt("purchaseId")).thenReturn(p1.getPurchaseId());

        int numProductsInTable = 1;
        ProductDao prodDao = new ProductDao("swapperz_testdb");
        Product result = prodDao.getProductById(3);

        //Check if the two objects are equal
        assertEquals(p1, result);

    }

    @Test
    public void testWithdrawAdd() throws SQLException {

        //In this test we will test the withdraw add method
        //for this test we will withdraw the product of id 3
        //Create the expected result
        Product p1 = new Product(3, 2, "Nike Tech Hoodie", "old hoodie selling cheap", 1, 1, 60, 50, "withdrawn", 4, "brandnew", 1, "male", "menshoodies", 16);

        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //fill mock objects with dummy data
        when(dbConn.prepareStatement("UPDATE products set status = 'withdrawn' where id = 3 ")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 1 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);
        // Fill in the resultset
        when(rs.getInt("id")).thenReturn(p1.getId());
        when(rs.getInt("userId")).thenReturn(p1.getUserId());
        when(rs.getString("productName")).thenReturn(p1.getProductName());
        when(rs.getString("description")).thenReturn(p1.getDescription());
        when(rs.getInt("colour")).thenReturn(p1.getColour());
        when(rs.getInt("productName")).thenReturn(p1.getBrand());
        when(rs.getDouble("orignalPrice")).thenReturn(p1.getOriginalPrice());
        when(rs.getDouble("listenPirce")).thenReturn(p1.getListedPrice());
        when(rs.getString("status")).thenReturn(p1.getStatus());
        when(rs.getInt("sizeId")).thenReturn(p1.getSize());
        when(rs.getString("productCondition")).thenReturn(p1.getProductCondition());
        when(rs.getInt("productImage")).thenReturn(p1.getProductImage());
        when(rs.getString("productGender")).thenReturn(p1.getProductGender());
        when(rs.getString("productCategory")).thenReturn(p1.getProductCategory());
        when(rs.getInt("purchaseId")).thenReturn(p1.getPurchaseId());

        int numProductsInTable = 1;
        ProductDao prodDao = new ProductDao("swapperz_testdb");
        prodDao.withdrawAdd(3);

        //Now we will get that product and check it has been withdrawn
        Product result = prodDao.getProductById(3);

        //Check if the two objects are equal
        assertEquals(p1, result);

    }

    @Test
    public void testAddProduct() throws SQLException {

        //Create the product we want to insert
        //int id , int userId , String productName , String description , int colour , int brand , double originalPrice , double listedPrice , String status , int size  , String productCondition , int productImage , String productGender , String productCategory){
        Product p1 = new Product(12, 3, "Nike Tech Hoodie", "old hoodie selling cheap", 1, 1, 60, 50, "forSale", 4, "brandnew", 1, "male", "menshoodies", 16);

        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //fill mock objects with dummy data
        ProductDao pDao = new ProductDao("swapperz_testdb");

        //(int userId, String productName, String description, int colour, int brand, double price, String size, String productCondition, int productImage, String productGender, String productCategory) 
        int rowsEffected = pDao.addProduct(p1.getUserId(), p1.getProductName(), p1.getDescription(), p1.getColour(), p1.getBrand(), p1.getOriginalPrice(), Integer.toString(p1.getSize()), p1.getProductCondition(), p1.getProductImage(), p1.getProductGender(), p1.getProductCategory());

        //If rows effected is not = 0 then it means the insert worked
        //Assert that the insert method worked
        assertEquals(rowsEffected, 1);

        //we now must remove the last insert
        int insertRemoved = pDao.deleteLastInsert();
        assertEquals(insertRemoved, 1);

    }

    @Test
    public void testChangeSoldStatus() throws SQLException {

        //Here we will test the change sold status method
        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        ProductDao pDao = new ProductDao("swapperz_testdb");

        //Get a product from the database
        Product productToTest = pDao.getProductById(19);

        //when we first retrieve the product its status should be sold
        assertEquals("sold", productToTest.getStatus());

        //we want to relist this add
        pDao.relistAdd(19);
        Product testForSale = pDao.getProductById(19);
        assertEquals("forSale", testForSale.getStatus());

        //then change it back to sold and check that our method works
        pDao.changeSoldStatus(19, "sold");
        Product testSold = pDao.getProductById(19);
        assertEquals("sold", testSold.getStatus());

    }

    @Test
    public void getNumOfUsersProductsOfStatus() throws SQLException {

        //This method will test the getNumOfUsersProductsOfStatus method
        //this method takes a user id and a product status and returns the ammount of products a user has of that status
        //for example if we pick user id 3 = osama and status of forSale we should get a result of 3
        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        ProductDao pDao = new ProductDao("swapperz_testdb");

        //we get the result
        int result = pDao.getNumOfUsersProductsOfStatus(3, "forSale");

        //asser it
        assertEquals(24, result);

    }

    //We must also test this method for the sold and withdrawn paramaters
    @Test
    public void getNumOfUsersProductsOfStatus2() throws SQLException {

        //This method will test the getNumOfUsersProductsOfStatus method
        //this method takes a user id and a product status and returns the ammount of products a user has of that status
        //for example if we pick user id 3 = osama and status of forSale we should get a result of 3
        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        ProductDao pDao = new ProductDao("swapperz_testdb");

        //we get the result
        int result = pDao.getNumOfUsersProductsOfStatus(3, "sold");

        //asser it
        assertEquals(1, result);

    }

    @Test
    public void testRelistAdd() throws SQLException {
        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //Create the dao method
        ProductDao pDao = new ProductDao("swapperz_testdb");

        //get a product that is sold
        Product soldProduct = pDao.getProductById(19);

        //assert that it is sold
        assertEquals("sold", soldProduct.getStatus());

        //now call the relist method on that product and assert that it worked
        pDao.relistAdd(19);
        Product ReList = pDao.getProductById(19);
        assertEquals("forSale", ReList.getStatus());

        //Now put it back to sold
        pDao.changeSoldStatus(19, "sold");

    }
    
    
    @Test 
    public void testRemoveAdd() throws SQLException {
        
        //Here we will test the remove add method
        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
    }

    @Test
    public void getNumOfUsersProductsOfStatus3() throws SQLException {

        //This method will test the getNumOfUsersProductsOfStatus method
        //this method takes a user id and a product status and returns the ammount of products a user has of that status
        //for example if we pick user id 3 = osama and status of forSale we should get a result of 3
        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        ProductDao pDao = new ProductDao("swapperz_testdb");

        //we get the result
        int result = pDao.getNumOfUsersProductsOfStatus(3, "withdrawn");

        //asser it
        assertEquals(1, result);

    }

    @Test
    public void testSuccessfullPurchase() throws SQLException {

        //Here we will test the successfull purchase dao method
        //It takes a purchase id and a product id
        //it will update that product id to have thge purchase id field contained in it
        //(int id , int userId , String productName , String description , int colour , int brand , double originalPrice , double listedPrice , String status , int size  , String productCondition , int productImage , String productGender , String productCategory , int purchaseId)
        //Create an object without a purchase id
        Product p1 = new Product(12, 3, "Nike Tech Hoodie", "old hoodie selling cheap", 1, 1, 60, 50, "forSale", 4, "brandnew", 1, "male", "menshoodies");

        //Create mock objecs
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        ProductDao pDao = new ProductDao("swapperz_testdb");

        int rowsEffected = pDao.addProduct(p1.getUserId(), p1.getProductName(), p1.getDescription(), p1.getColour(), p1.getBrand(), p1.getOriginalPrice(), Integer.toString(p1.getSize()), p1.getProductCondition(), p1.getProductImage(), p1.getProductGender(), p1.getProductCategory());

        pDao.deleteLastInsert();

        //Assert that the purchase id is 0 as it has not been set yet
    }


}
