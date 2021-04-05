/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocking;

import business.Colour;
import daos.ColourDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Osama Kheireddine
 */
public class ColourDaoMockTest {
    //for calling the colour dao as it will be used in all tests
    private static ColourDao cDao;
    
    public ColourDaoMockTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        //initalizing the colour dao object
        cDao = new ColourDao("swapperz_testdb");
    }
    
    /**
     * This will be a mock test that will test our dummy data against the valid data in our database for colours.
     * These colours will be then added to a list and we will compare the list size.
     * @throws SQLException 
     */
    @Test
    public void getAllColoursMockTest() throws SQLException{
        //all of our expected data
        Colour c1 = new Colour(1,"red");
        Colour c2 = new Colour(2,"yellow");
        Colour c3 = new Colour(3,"pink");
        Colour c4 = new Colour(4,"purple");
        Colour c5 = new Colour(5,"green");
        Colour c6 = new Colour(6,"black");
        Colour c7 = new Colour(7,"white");
        Colour c8 = new Colour(8,"navy");
        Colour c9 = new Colour(9,"blue");
        //add our expected results to a list
        ArrayList<Colour> expectedResults = new ArrayList();
        expectedResults.add(c1);
        expectedResults.add(c2);
        expectedResults.add(c3);
        expectedResults.add(c4);
        expectedResults.add(c5);
        expectedResults.add(c6);
        expectedResults.add(c7);
        expectedResults.add(c8);
        expectedResults.add(c9);
        
        //create our mock objects
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        //fill in the mock objects with the dummy data
        when(conn.prepareStatement("CALL getAllColours()")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        //place the appropriate number of expected results
        when(rs.next()).thenReturn(true,true,true,true,true,true,true,true,true,false);
        //fill in our result set
        when(rs.getInt("id")).thenReturn(c1.getId(), c2.getId(), c3.getId(), c4.getId(), c5.getId(), c6.getId(), c7.getId(), c8.getId(), c9.getId());
        when(rs.getString("colour")).thenReturn(c1.getColour(), c2.getColour(), c3.getColour(), c4.getColour(), c5.getColour(), c6.getColour(), c7.getColour(), c8.getColour(), c9.getColour());
        
        //add the mock objects to a list
        List<Colour> result = cDao.getAllColours();
        
        //assert the sizes are equal
        assertEquals(expectedResults.size(), result.size());
    }
    
    /**
     * This will be a mock test that will test our dummy data against the valid data in our database for a given colour.
     * These colours will be then be compared based off the name supplied (to lowercase).
     * @throws SQLException 
     */
    @Test
    public void getColourMockTest() throws SQLException{
        //get our expected result
        Colour c1 = new Colour(1, "red");
        
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        //fill in the mock objects with the dummy data
        when(conn.prepareStatement("CALL getColour(1)")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        //place the appropriate number of expected results
        when(rs.next()).thenReturn(true,false);
        //fill in our result set
        when(rs.getInt("id")).thenReturn(c1.getId());
        when(rs.getString("colour")).thenReturn(c1.getColour());
        //add the mock objects to a list
        Colour result = cDao.getColour(1);
        
        //assert the sizes are equal
        assertEquals(c1.getColour().toLowerCase(), result.getColour().toLowerCase());
    }
}
