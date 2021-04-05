/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Address;

/**
 *
 * @author Osama Kheireddine
 */
public interface AddressDaoInterface {
    
    public int newAddress(int userId, String addrLine1, String addrLine2, String town, String county, String country);
    
    public int getAddressId(int userId);
    
    public Address getUserAddress(int userId);
}
