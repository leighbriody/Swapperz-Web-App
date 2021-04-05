/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import daos.AddressDao;
import daos.ColourDao;
import daos.ProductDao;
import daos.ProductImageDao;
import daos.PurchaseDao;
import daos.SizeDao;
import daos.UserDao;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Leigh Briody
 */
public class testDelete {

    public static void main(String[] args) {

        ProductDao p = new ProductDao("swapperz");
        HashMap<String, Integer> intSearchTerms = new HashMap<>();
        HashMap<String, String> stringSearchTerms = new HashMap<>();

        ColourDao cDao = new ColourDao("swapperz");

        ArrayList<Product> all = p.allProductsOfCategoryChosen("menshoodies");

        UserDao uDao = new UserDao("swapperz_testdb");
        ProductDao pDao = new ProductDao("swapperz");

        //Test if two produccts are equal
        ArrayList<Product> cart = new ArrayList<>();

        Product test = pDao.getProductById(19);

        cart.add(test);
        
        System.out.println(cart.size());
        
        System.out.println(cart.remove(test));
        
        System.out.println(cart.size());
    }

}
