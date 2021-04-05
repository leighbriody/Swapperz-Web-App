/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Blob;
import java.util.Objects;

/**
 *
 * @author Leigh Briody
 */
public class Product  {
 
    
    //all fields
    int id;
    int userId;
    String productName;
    String description;
    int colour;
    int brand;
    double originalPrice;
    double listedPrice;
    String status;
    int size;
    String productCondition;
    int productImage;
    String productGender;
    String productCategory;
    int purchaseId;
    
    
   //Constrcutor
    /**
     * This constructor will create an instance of the product object
     * @param userId
     * @param productName
     * @param productType
     * @param description
     * @param colour
     * @param brand
     * @param originalPrice
     * @param listedPrice
     * @param status
     * @param size
     * @param productCondition
     * @param productImage 
     */


   
   //WITH PRIMARY KEY
     public Product(int id , int userId , String productName , String description , int colour , int brand , double originalPrice , double listedPrice , String status , int size  , String productCondition , int productImage , String productGender , String productCategory){
       
       this.id = id;
       this.userId = userId;
       this.productName = productName;
       this.description = description;
       this.colour = colour;
       this.brand = brand;
       this.originalPrice = originalPrice;
       this.listedPrice = listedPrice;
       this.status = status;
       this.size = size;
       this.productCondition = productCondition;
       this.productImage = productImage;
       this.productGender = productGender;
       this.productCategory = productCategory;
               
   }
     
     
     //One now with purchase id also
      //WITH PRIMARY KEY
     public Product(int id , int userId , String productName , String description , int colour , int brand , double originalPrice , double listedPrice , String status , int size  , String productCondition , int productImage , String productGender , String productCategory , int purchaseId){
       
       this.id = id;
       this.userId = userId;
       this.productName = productName;
       this.description = description;
       this.colour = colour;
       this.brand = brand;
       this.originalPrice = originalPrice;
       this.listedPrice = listedPrice;
       this.status = status;
       this.size = size;
       this.productCondition = productCondition;
       this.productImage = productImage;
       this.productGender = productGender;
       this.productCategory = productCategory;
       this.purchaseId = purchaseId;
               
   }

   
     
     

 
    
    //GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(double listedPrice) {
        this.listedPrice = listedPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductGender() {
        return productGender;
    }

    public void setProductGender(String productGender) {
        this.productGender = productGender;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }
    
    
    
    
    //To String

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", userId=" + userId + ", productName=" + productName + ", description=" + description + ", colour=" + colour + ", brand=" + brand + ", originalPrice=" + originalPrice + ", listedPrice=" + listedPrice + ", status=" + status + ", size=" + size + ", productCondition=" + productCondition + ", productImage=" + productImage + ", productGender=" + productGender + ", productCategory=" + productCategory + '}';
    }
    
    //We need to override the equals method so the contain works

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

    
    

    
  

   
    
   
   
}
