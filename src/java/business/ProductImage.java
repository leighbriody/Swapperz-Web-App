/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Leigh Briody
 */
public class ProductImage {
    
    //all fields 
    int id;
    String imageLocation;
    
    //constrcutos
    public ProductImage(int id , String imageLocation ){
        this.id = id;
        this.imageLocation = imageLocation;

    }
    
    //getters setters and to string

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    @Override
    public String toString() {
        return "ProductImage{" + "id=" + id + ", imageLocation=" + imageLocation + '}';
    }
    
    
    
}
