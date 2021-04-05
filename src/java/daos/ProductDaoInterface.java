/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Product;
import business.Purchase;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Leigh Briody
 */
public interface ProductDaoInterface {

        //when a user selects a product category we want tp display all the items of that catrogry fore that gender 
    //now given thaat category chosen we want to display all items of that category chosen
    public ArrayList<Product> allProductsOfCategoryChosen(String productCategory);

    public int addProduct(int userId, String productName, String description, int colour, int brand, double price, String size, String productCondition, int productImage, String productGender, String productCategory);

    public ArrayList<Product> searchedProducts(HashMap<String, String> stringSearchTerms, HashMap<String, Integer> intSearchTerms);

    //will get number of products uuser has either for sale or sold
    public int getNumOfUsersProductsOfStatus(int userId, String status);

    //Retruns all users products of status either sold or selling
    public ArrayList<Product> allUsersProductsOfStatus(int userId, String status);

    public int changeSoldStatus(int productId, String status);

    public int successfulPurchase(int purchaseId, int productId);

    //this will remove an item for sale
    public int withdrawAdd(int productId);

    //this method will re list a withdrawn add for sale
    public int relistAdd(int productId);

    //this method will chnage the products status to removed
    public int removeAdd(int productId);

    //This will return the product object given its id
    public Product getProductById(int productId);

    public boolean editAdPrice(int productId, double newPrice);

    public int deleteLastInsert();
    
    public int getNumberOfProductsOfCategoryForSale(String status);
    
     public ArrayList<Product> unapprovedPurchases();
}
