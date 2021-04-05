package mocking;


import business.User;
import daos.UserDao;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Leigh Briody
 */
public class UserDaoMockTests {

    public UserDaoMockTests() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @Test
    public void testGetAllUsers() throws SQLException {

        //create the expected results 
        //String username , String passowrd , String fName , String lName , String email , int addressId , String phone , int sellerRating , String gender , String profilePicture){
        User u1 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");
        User u2 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");
        User u3 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");
        User u4 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");
        User u5 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");
        User u6 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");
        User u7 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");
        User u8 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");
        User u9 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");
        User u10 = new User("username", "password", "fName", "lName", "email", "addressId", "phone", "SellerRating", "gender", "profile");

        ArrayList<User> expectedResults = new ArrayList<>();
        expectedResults.add(u1);
        expectedResults.add(u2);
        expectedResults.add(u3);
        expectedResults.add(u4);
        expectedResults.add(u5);
        expectedResults.add(u6);
        expectedResults.add(u7);
        expectedResults.add(u8);
        expectedResults.add(u9);
        expectedResults.add(u10);

        //Create mock objects
        // Create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //Fill mock objects with the dummy data
        when(dbConn.prepareStatement("Select * from users")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 10 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, true, true, true, true, true, true, true, false);

        //Fill in the result set
        when(rs.getString("username")).thenReturn(u1.getUsername(), u2.getUsername(), u3.getUsername(), u4.getUsername(), u5.getUsername(), u6.getUsername(), u7.getUsername(), u8.getUsername(), u9.getUsername(), u10.getUsername());

        when(rs.getString("password")).thenReturn(u1.getPassword(), u2.getPassword(), u3.getPassword(), u4.getPassword(), u5.getPassword(), u6.getPassword(), u7.getPassword(), u8.getPassword(), u9.getPassword(), u10.getPassword());

        when(rs.getString("fName")).thenReturn(u1.getfName(), u2.getfName(), u3.getfName(), u4.getfName(), u5.getfName(), u6.getfName(), u7.getfName(), u8.getfName(), u9.getfName(), u10.getfName());

        when(rs.getString("lName")).thenReturn(u1.getlName(), u2.getlName(), u3.getlName(), u4.getlName(), u5.getlName(), u6.getlName(), u7.getlName(), u8.getlName(), u9.getlName(), u10.getlName());

        when(rs.getString("email")).thenReturn(u1.getEmail(), u2.getEmail(), u3.getEmail(), u4.getEmail(), u5.getEmail(), u6.getEmail(), u7.getEmail(), u8.getEmail(), u9.getEmail(), u10.getEmail());

        when(rs.getString("addressId")).thenReturn(u1.getAddressId(), u2.getAddressId(), u3.getAddressId(), u4.getAddressId(), u5.getAddressId(), u6.getAddressId(), u7.getAddressId(), u8.getAddressId(), u9.getAddressId(), u10.getAddressId());

        when(rs.getString("phone")).thenReturn(u1.getPhone(), u2.getPhone(), u3.getPhone(), u4.getPhone(), u5.getPhone(), u6.getPhone(), u7.getPhone(), u8.getPhone(), u9.getPhone(), u10.getPhone());

        when(rs.getString("sellerRating")).thenReturn(u1.getSellerRating(), u2.getSellerRating(), u3.getSellerRating(), u4.getSellerRating(), u5.getSellerRating(), u6.getSellerRating(), u7.getSellerRating(), u8.getSellerRating(), u9.getSellerRating(), u10.getSellerRating());

        when(rs.getString("gender")).thenReturn(u1.getGender(), u2.getGender(), u3.getGender(), u4.getGender(), u5.getGender(), u6.getGender(), u7.getGender(), u8.getGender(), u9.getGender(), u10.getGender());

        when(rs.getString("profilePicture")).thenReturn(u1.getProfilePicture(), u2.getProfilePicture(), u3.getProfilePicture(), u4.getProfilePicture(), u5.getProfilePicture(), u6.getProfilePicture(), u7.getProfilePicture(), u8.getProfilePicture(), u9.getProfilePicture(), u10.getProfilePicture());

        int numUsersInTable = 9;

        UserDao userDao = new UserDao("swapperz_testdb");

        ArrayList<User> result = userDao.getAllUsers();

        // Check that the number of entries retrieved matches the (known) number 
        // of entries in the supplied dummy data
        assertEquals(numUsersInTable, result.size());

        System.out.println(result);
    }
    
    
    

}
