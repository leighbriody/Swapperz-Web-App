/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Leigh Briody
 */
public class ProductDao extends Dao implements ProductDaoInterface {

    public ProductDao(String databaseName) {
        super(databaseName);
    }
    
    
   
    //Method to get all products  of a category chosen
    @Override
    public ArrayList<Product> allProductsOfCategoryChosen(String productCategory) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Product> products = new ArrayList();

        try {
            con = getConnection();

            String query = "CALL allProductsOfCategoryChosen(?)";
            ps = con.prepareStatement(query);
            ps.setString(1, productCategory);
            rs = ps.executeQuery();

            while (rs.next()) {

                //Get all the data fields
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int colour = rs.getInt("colour");
                int brand = rs.getInt("brandId");
                double originalPrice = rs.getDouble("originalPrice");
                double listedPrice = rs.getDouble("listedPrice");
                String status = rs.getString("status");
                int sizeId = rs.getInt("sizeId");
                String productCondition = rs.getString("productCondition");
                int productImage = rs.getInt("productImage");
                String productGender = rs.getString("productImage");
                String category = rs.getString("productCategory");

                //create the object
                Product product = new Product(id, userId, productName, description, colour, brand, originalPrice, listedPrice, status, sizeId, productCondition, productImage, productGender, category);
                //Add it to the array list.
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the allProductsOfCategoryChosen() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the allProductsOfCategoryChosen() method: " + e.getMessage());
            }
        }

        return products;
    }

    @Override
    public int addProduct(int userId, String productName, String description, int colour, int brand, double price, String size, String productCondition, int productImage, String productGender, String productCategory) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();
            String query = "INSERT INTO products (id, userId, productName, description, colour, brandId, originalPrice, listedPrice, status, sizeId, productCondition, productImage, productGender, productCategory) VALUES (NULL , ? , ? , ? , ? , ? , ? , NULL , 'forSale' , ? , ? , ? , ? , ? )";

            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, productName);
            ps.setString(3, description);
            ps.setInt(4, colour);
            ps.setInt(5, brand);
            ps.setDouble(6, price);
            ps.setString(7, size);
            ps.setString(8, productCondition);
            ps.setInt(9, productImage);
            ps.setString(10, productGender);
            ps.setString(11, productCategory);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("An exception has occured in the addProduct() method: " + ex.getMessage() + " " + productCondition);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addProduct() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }

    public static void main(String[] args) {
        ProductDao pDao = new ProductDao("swapperz");

        System.out.println(pDao.successfulPurchase(3, 3));
    }

    @Override
    public int successfulPurchase(int purchaseId, int productId) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {

        
            String query = "UPDATE products SET purchaseId = 12 WHERE id = 19";

            ps = con.prepareStatement(query);
            ps.setInt(1, purchaseId);
            ps.setInt(2, productId);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception has occured in the successfulPurchase() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the successfulPurchase() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }

    @Override
    public int changeSoldStatus(int productId, String status) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String query = "UPDATE products SET status = ? WHERE id = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, productId);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception has occured in the changeSoldStatus() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the changeSoldStatus() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }
    
     @Override
    public ArrayList<Product> unapprovedPurchases() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Product> purchaseList = new ArrayList();

        try {
            con = getConnection();

            String query = "SELECT * from products where status = 'pending'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                
                //Get all the data fields
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int colour = rs.getInt("colour");
                int brand = rs.getInt("brandId");
                double originalPrice = rs.getDouble("originalPrice");
                double listedPrice = rs.getDouble("listedPrice");
                String status = rs.getString("status");
                int sizeId = rs.getInt("sizeId");
                String productCondition = rs.getString("productCondition");
                int productImage = rs.getInt("productImage");
                String productGender = rs.getString("productImage");
                String category = rs.getString("productCategory");

                //create the object
                Product p = new Product(id, userId, productName, description, colour, brand, originalPrice, listedPrice, status, sizeId, productCondition, productImage, productGender, category);
                purchaseList.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the allProductsOfCategoryChosen() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the allProductsOfCategoryChosen() method: " + e.getMessage());
            }
        }

        return purchaseList;
    }

    /**
     * A method/function that builds a query based off of what a user has
     * selected Used for searching for items with certain qualities
     *
     * @param stringSearchTerms
     * @param intSearchTerms
     * @return A list of items that match the search criteria
     */
    @Override
    public ArrayList<Product> searchedProducts(HashMap<String, String> stringSearchTerms, HashMap<String, Integer> intSearchTerms) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Product> products = new ArrayList();
        try {
            con = getConnection();
            //we will have 2 parts of the query constant & variable parts
            String constQuery = "SELECT * FROM products WHERE ";
            String varQuery = "";
            //need to check which of or if the two hashmaps have data
            int intSearchCount = 0;
            if (!intSearchTerms.isEmpty()) {//if its not empty build query
                for (String key : intSearchTerms.keySet()) {
                    intSearchCount++; // increase count
                    if (intSearchCount == 1) {//here we must check to see if it is the first or second item due to sql syntax
                        varQuery = varQuery + key + " = ?";
                    } else {
                        //note field will be the name of the db field
                        varQuery = varQuery + " AND " + key + " = ?";
                    }
                }
            }

            int stringSearchCount = 0;
            if (!stringSearchTerms.isEmpty()) {//if its not empty build query
                for (String key : stringSearchTerms.keySet()) {
                    stringSearchCount++; // increase count
                    //here we will need another if statement in case the intSearchTerm was selected
                    if (intSearchCount > 0) {
                        //note field will be the name of the db field 
                        varQuery = varQuery + " AND " + key + " = ?";
                        //we are using the OR query builder only as 
                        //there has been a beinning of a query created
                    } else if (intSearchCount < 1) { //Check if the int search count has not been used
                        if (stringSearchCount == 1) {
                            //here we must check to see if it is the first or second item due to sql syntax
                            varQuery = varQuery + key + " = ?";
                        } else {
                            //note field will be the name of the db field
                            varQuery = varQuery + " AND " + key + " = ?";
                        }
                    }
                }
            }
            //now to build the final query
            String query = constQuery + varQuery + ";";
            ps = con.prepareStatement(query);

            //here we will loop and set ints and set strings
            //We create an arraylist to hold the values from the hashmap,
            //here we can then simply use a for loop to set the prepared statement
            List intValues = new ArrayList(intSearchTerms.values());
            for (int i = 0; i < intSearchCount; i++) {
                int intSearchItem = (int) intValues.get(i);
                ps.setInt((i + 1), intSearchItem);
            }

            //as the int has already taken the most recent place, 
            //the string will have to start from the final position of the int
            List stringValues = new ArrayList(stringSearchTerms.values());
            int fullCount = intSearchCount + stringSearchCount;
            if (intSearchCount < 1) {
                for (int i = 0; i < stringSearchCount; i++) { //Int search
                    //now loop through the map to get the array
                    String stringSearchItem = (String) stringValues.get(i);
                    ps.setString((i + 1), stringSearchItem);
                }
            } else {
                int j = 0;
                for (int i = intSearchCount + 1; i <= fullCount; i++) { //String search
                    //now loop through the map to get the key
                    String stringSearchItem = (String) stringValues.get(j);
                    ps.setString(i, stringSearchItem);
                    j++;
                }
            }

            rs = ps.executeQuery(); //execute query
            while (rs.next()) {

                //Get all the data fields
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int colour = rs.getInt("colour");
                int brand = rs.getInt("brandId");
                double originalPrice = rs.getDouble("originalPrice");
                double listedPrice = rs.getDouble("listedPrice");
                String status = rs.getString("status");
                int sizeId = rs.getInt("sizeId");
                String productCondition = rs.getString("productCondition");
                int productImage = rs.getInt("productImage");
                String productGender = rs.getString("productImage");
                String category = rs.getString("productCategory");

                //create the object
                Product product = new Product(id, userId, productName, description, colour, brand, originalPrice, listedPrice, status, sizeId, productCondition, productImage, productGender, category);
                //Add it to the array list.
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the searchedProduts() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the searchedProduts() method: " + e.getMessage());
            }
        }

        return products;
    }

    //Get the number of adds of a user of a selling status//
    //example user id and product status will be passed as a paramater
    //product status will be either forSale or sold
    @Override
    public int getNumOfUsersProductsOfStatus(int userId, String status) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = -1;

        try {
            con = getConnection();

            String query = "select count(id) from products where userId = ? and status = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, status);

            rs = ps.executeQuery();

            if (rs.next()) {
                num = rs.getInt("COUNT(id)");
            }

        } catch (SQLException e) {
            System.out.println("An exception has occured in the                 System.out.println(\"Exception occured in the finally section of the getLastInsertedId() method: \" + e.getMessage());\n"
                    + "getNumOfUsersProductsOfStatus() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getNumOfUsersProductsOfStatus() method: " + e.getMessage());
                e.getMessage();
            }

        }

        return num;
    }
    
    
     public int getNumberOfProductsOfCategoryForSale(String status) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = -1;

        try {
            con = getConnection();

            String query = "select count(id) from products where productCategory = ? and status = 'forSale'";
            ps = con.prepareStatement(query);
            ps.setString(1, status);

            rs = ps.executeQuery();

            if (rs.next()) {
                num = rs.getInt("COUNT(id)");
            }

        } catch (SQLException e) {
            System.out.println("An exception has occured in the                 System.out.println(\"Exception occured in the finally section of the getLastInsertedId() method: \" + e.getMessage());\n"
                    + "getNumberOsfProductsOfCategoryForSale() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getNumberOsfProductsOfCategoryForSale() method: " + e.getMessage());
                e.getMessage();
            }

        }

        return num;
    }

    //DELETE FROM products ORDER BY id DESC LIMIT 1
    @Override
    public int deleteLastInsert() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int effetced = -1;

        try {
            con = getConnection();

            String query = "DELETE FROM products ORDER BY id DESC LIMIT 1";
            ps = con.prepareStatement(query);

            effetced = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception has occured in the                 System.out.println(\"Exception occured in the finally section of the deleteLastInsert() method: \" + e.getMessage());\n"
                    + "getNumOfUsersProductsOfStatus() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the deleteLastInsert() method: " + e.getMessage());
                e.getMessage();
            }

        }

        return effetced;
    }

    //getUsersProductsOf Type
    @Override
    public ArrayList<Product> allUsersProductsOfStatus(int theUserId, String theStatus) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Product> products = new ArrayList();

        try {
            con = getConnection();

            String query = "SELECT * from products where userId = ? AND status = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, theUserId);
            ps.setString(2, theStatus);
            rs = ps.executeQuery();

            while (rs.next()) {

                //Get all the data fields
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int colour = rs.getInt("colour");
                int brand = rs.getInt("brandId");
                double originalPrice = rs.getDouble("originalPrice");
                double listedPrice = rs.getDouble("listedPrice");
                String status = rs.getString("status");
                int sizeId = rs.getInt("sizeId");
                String productCondition = rs.getString("productCondition");
                int productImage = rs.getInt("productImage");
                String productGender = rs.getString("productImage");
                String category = rs.getString("productCategory");

                //create the object
                Product product = new Product(id, userId, productName, description, colour, brand, originalPrice, listedPrice, status, sizeId, productCondition, productImage, productGender, category);
                //Add it to the array list.
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the allUsersProductsOfStatus() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the allUsersProductsOfStatus() method: " + e.getMessage());
            }
        }

        return products;
    }

    //this will change an item from forSale to 
    @Override
    public int withdrawAdd(int productId) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();
            String query = "update products set status = 'withdrawn' where id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, productId);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("An exception has occured in the withdrawAdd() method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the withdrawAdd() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }

    //This method will change the products status back to forSale
    @Override
    public int relistAdd(int productId) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();
            String query = "update products set status = 'forSale' where id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, productId);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("An exception has occured in the relistAdd() method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the relistAdd() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }

    //This method will change the products status to removed 
    @Override
    public int removeAdd(int productId) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();
            String query = "update products set status = 'removed' where id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, productId);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("An exception has occured in the removeAdd() method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the removeAdd() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }

    //This method will return a product object given the product id
    @Override
    public Product getProductById(int productId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product p = null;

        try {
            conn = getConnection();

            String query = "SELECT * FROM products WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();

            if (rs.next()) {

                //Get all the data fields
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int colour = rs.getInt("colour");
                int brand = rs.getInt("brandId");
                double originalPrice = rs.getDouble("originalPrice");
                double listedPrice = rs.getDouble("listedPrice");
                String status = rs.getString("status");
                int sizeId = rs.getInt("sizeId");
                String productCondition = rs.getString("productCondition");
                int productImage = rs.getInt("productImage");
                String productGender = rs.getString("productImage");
                String category = rs.getString("productCategory");

                //create the object
                p = new Product(id, userId, productName, description, colour, brand, originalPrice, listedPrice, status, sizeId, productCondition, productImage, productGender, category);

            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific user in the getProductById() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getProductById() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getProductById() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return p;
    }

    @Override
    public boolean editAdPrice(int productId, double newPrice) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        boolean successfulUpdate = false;

        try {
            con = getConnection();
            String query = "UPDATE products SET listedPrice = ? where id = ?";

            ps = con.prepareStatement(query);
            ps.setDouble(1, newPrice);
            ps.setInt(2, productId);

            rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                successfulUpdate = true;
            }
        } catch (SQLException ex) {
            System.out.println("An exception has occured in the editAdPrice() method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the editAdPrice() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return successfulUpdate;
    }

    
}

