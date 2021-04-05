/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.User;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public interface UserDaoInterface {
    public int register(String fName, String lName, String email, String username, String password , String phone , String gender , String profilePicture);
    
    public User getUserByUsername(String username);
    
    public int removeUserByUsername(String username);
    
    public int getUserId(String username);
    
    public boolean addAddress(int userId, int addressId);
    
    public int getUserAddressId(int userId);
    
    public User getUserById(int userI);
    
    public boolean updateUserDetails(int uId, String fName, String lName, String email, String phone);
    
    public ArrayList<User> getAllUsers();
}
