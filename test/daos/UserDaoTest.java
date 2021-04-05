package daos;


import business.User;
import daos.UserDao;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.junit.runner.RunWith;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leigh Briody
 */
public class UserDaoTest {

    //Create a global User Dao for the whole class to user 
    //USER DAO TESTS
    UserDao uDao = new UserDao("swapperz_testdb");

    //Creating UNIT Tests for the User Class
    //Register method will all fields correct - this should be a success register
    @Test
    public void testRegister1() {

        int registerSuccess = uDao.register("joe", "blogs", "joe@gmail.com", "joeuser123", "badpassword123", "0876261232", "male", "profilePic.png");
        //expected result is 1 as if the insert was success it will return 1
        int expResult = 1;
        assertEquals(expResult, registerSuccess);

        uDao.removeUserByUsername("joeuser123");
    }

    //Test 1 of get user by username , if we pass a valid username that is in the database
    @Test
    public void testGetUserByUsername1() {
        User leigh = uDao.getUserByUsername("leigh");

        //the id of this user should be 1
        int expectedId = 1;
        int id = leigh.getId();

        //assert the equals
        assertEquals(id, expectedId);

    }

    //Test if we pass a username that doesnt exist in our database
    @Test
    public void testGetUserByUsername2() {

        User invalidUser = uDao.getUserByUsername("paulito");
        boolean invalid = false;
        try {
            int id = invalidUser.getId();
        } catch (NullPointerException e) {
            //it should trow a null pointer exception as it is an invalid user
            invalid = true;
        }

        //assert its an invalaid user
        assertEquals(invalid, true);

    }

    //Test remove user by username when supplied a valid username
    @Test
    public void removeUserByUsername1() {

        //First create a new user to remove
        uDao.register("joe", "blogs", "joe@gmail.com", "joeuser123", "badpassword123", "0876261232", "male", "profilePic.png");

        //now we will call the dao method to remove that user
        int removeSuccesss = uDao.removeUserByUsername("joeuser123");

        //if the remove is successfull removeSuccess will be = 1
        int expectedResult = 1;

        assertEquals(expectedResult, removeSuccesss);

    }

    //Test remove user by username when supplied a invalid username
    @Test
    public void removeUserByUsername2() {

        //now we will call the dao method to remove that user
        int removeSuccesss = uDao.removeUserByUsername("invalidUser");

        //if the remove is successfull removeSuccess will be = 1
        int expectedResult = 0;

        assertEquals(expectedResult, removeSuccesss);

    }

    //Testing get user id when passed a valid username
    @Test
    public void getUserId1() {

        //Kyles id is 11
        int id = uDao.getUserId("kyle");

        int expetcedResult = 11;

        assertEquals(expetcedResult, id);
    }

    //Testing get user id when passed an invalid username
    @Test
    public void getUserId2() {

        //Kyles id is 11
        int id = uDao.getUserId("invalidUsername");

        int expetcedResult = -1;

        assertEquals(expetcedResult, id);
    }

    //Test a valid user id passed
    @Test
    public void getUserById1() {

        //user 11 is the user kyle
        User kyle = uDao.getUserById(11);

        int expectedId = 11;
        int actual = kyle.getId();

        assertEquals(expectedId, actual);
    }

    //Here we will test if we pass an invalid user id
    @Test
    public void getUserById2() {

        User kyle = uDao.getUserById(22222);
        boolean userInvalid = false;
        try {
            int id = kyle.getId();
        } catch (NullPointerException e) {
            userInvalid = true;
        }

        assertEquals(userInvalid, true);

    }

    //Here we will test a valid update user details method
    @Test
    public void updateUserDetails1() {

        //here we will update the user kyle with the id of 11 
        boolean result = uDao.updateUserDetails(11, "kyleo", "briody", "newEmail@gmail.com", "0887261232");

        assertEquals(result, true);

        //we will change his details back 
        uDao.updateUserDetails(11, "kyle", "molloy", "kmolloy@gmail.com", "872625123");
    }

    //Here we will test if we pass an invalid user id
    @Test
    public void updateUserDetails2() {

        boolean result = uDao.updateUserDetails(99, "test", "lName", "email", "phone");

        boolean expectedResult = false;

        assertEquals(result, expectedResult);
    }

}
