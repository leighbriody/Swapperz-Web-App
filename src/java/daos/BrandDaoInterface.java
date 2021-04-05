/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Brand;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public interface BrandDaoInterface {
    public ArrayList<Brand> getAllBrands();
    
    public Brand getBrand(int brandId);
    
    public int addNewBrand(String brandName);
}
