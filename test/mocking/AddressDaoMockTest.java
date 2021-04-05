/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocking;

import business.Address;
import daos.AddressDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Osama Kheireddine
 */
public class AddressDaoMockTest {

    private static AddressDao aDao;
    
    public AddressDaoMockTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        aDao = new AddressDao("swapperz_tesdb");
    }

    /**
     * This Will mock adding in a new address into the address table.
     * We will assert the result returned (should be 1) is equal.
     */
    @Test
    public void newAddressMockTest() throws SQLException{
        //Create expected results
        int rowsAffected = 0; 

        //Create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        //Fill in mock objects with appropriate dummy data
        when(dbConn.prepareStatement("INSERT INTO address (userId, line1, line2, town, county, country) VALUES (2,'mock','test','testTown','testCounty','testCountry')")).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(rowsAffected = 1);

        //add the address to the object
        int result = aDao.newAddress(12, "mock", "test", "testTown", "testCounty", "testCountry");

        //assert they are the same
        assertEquals(rowsAffected, result);
    }
    
    /**
     * This will mock the method to retrieve an address based off a user's id
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void getUserAddressForId0MockTest() throws SQLException {
        //Create expected results
        Address a1 = new Address(1, 2, "13255 East End Ave", "test", "Drogheda", "Louth", "Ireland");

        //Create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //Fill in mock objects with appropriate dummy data
        when(dbConn.prepareStatement("SELECT * FROM address WHERE userId = 2 ORDER BY id DESC")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        //Want the single result in the result set
        when(rs.next()).thenReturn(true, false); // this is true then false as there is only 1 result
        //fill in the resultset
        when(rs.getInt("id")).thenReturn(a1.getId());
        when(rs.getInt("userId")).thenReturn(a1.getUserId());
        when(rs.getString("line1")).thenReturn(a1.getAddrLine1());
        when(rs.getString("line2")).thenReturn(a1.getAddrLine2());
        when(rs.getString("town")).thenReturn(a1.getTown());
        when(rs.getString("county")).thenReturn(a1.getCounty());
        when(rs.getString("country")).thenReturn(a1.getCountry());
        
        //add the address to the object
        Address result = aDao.getUserAddress(2);

        //assert they are the same
        assertEquals(a1.getAddrLine1(), result.getAddrLine1());

        System.out.println(result);

    }
}
