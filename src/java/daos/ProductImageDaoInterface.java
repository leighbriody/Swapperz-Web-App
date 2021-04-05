/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

/**
 *
 * @author Leigh Briody
 */
public interface ProductImageDaoInterface {
    
    //this method will return the product image location store as a string given the prodcut timage id
    public String getProdcutImageLocation(int productImageId);
    
    public int insertImageLocation(String imageLocation);
    
    //this will get the last id that was inserted into the database
    public int getLastInsertedId();
   
    public int deleteRecentRecord();
}
