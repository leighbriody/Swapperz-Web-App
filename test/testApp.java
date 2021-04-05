
import daos.UserDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leigh Briody
 */
public class testApp {
    public static void main(String[] args) {
        
        
        UserDao uDao = new UserDao("swapperz");
        
        String fName = "leigh";
         String lName = "leigh";
          String email = "leigh@gmail.com";
          String username = "leigh";
          String password = "daFf";
        String phone = "087828283";
        
        
        int rows = uDao.register(fName, lName, email, username, password , phone);
        
        System.out.println(rows);
    }
}
