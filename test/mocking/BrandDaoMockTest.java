/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocking;

import business.Brand;
import daos.BrandDao;
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
public class BrandDaoMockTest {

    private static BrandDao bDao;

    public BrandDaoMockTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        bDao = new BrandDao("swapperz_testdb");
    }

    /**
     * This will be a mock test that will test our dummy data against the valid
     * data in our database for brands. These brands will be then added to a
     * list and we will compare the list size.
     *
     * @throws SQLException
     */
    @Test
    public void getAllBrandsMockTest() throws SQLException {
        //our expected results
        Brand b1 = new Brand(1, "Nike");
        Brand b2 = new Brand(2, "Adidas");
        Brand b3 = new Brand(3, "The North Face");
        Brand b4 = new Brand(4, "Puma");
        Brand b5 = new Brand(5, "Lacoste");
        Brand b6 = new Brand(6, "Regatta");
        Brand b7 = new Brand(7, "Gap");
        Brand b8 = new Brand(8, "Vans");
        Brand b9 = new Brand(9, "Asos");
        Brand b10 = new Brand(10, "Fred Perry");
        Brand b11 = new Brand(11, "Superdry");
        Brand b12 = new Brand(12, "Columbia");
        Brand b13 = new Brand(13, "Under Armour");

        //add these results to a list
        ArrayList<Brand> expResults = new ArrayList();
        expResults.add(b1);
        expResults.add(b2);
        expResults.add(b3);
        expResults.add(b4);
        expResults.add(b5);
        expResults.add(b6);
        expResults.add(b7);
        expResults.add(b8);
        expResults.add(b9);
        expResults.add(b10);
        expResults.add(b11);
        expResults.add(b12);
        expResults.add(b13);

        //create our mock objects
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        //fill in the mock objects with the dummy data
        when(conn.prepareStatement("CALL getAllBrands()")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        //place the appropriate number of expected results
        when(rs.next()).thenReturn(true, true, true, true, true, true, true, true, true, false);
        //fill in our result set
        when(rs.getInt("id")).thenReturn(b1.getId(), b2.getId(), b3.getId(), b4.getId(), b5.getId(), b6.getId(), b7.getId(), b8.getId(), b9.getId(), b10.getId(), b11.getId(), b12.getId(), b13.getId());
        when(rs.getString("name")).thenReturn(b1.getName(), b2.getName(), b3.getName(), b4.getName(), b5.getName(), b6.getName(), b7.getName(), b8.getName(), b9.getName(), b10.getName(), b11.getName(), b12.getName(), b13.getName());
        //get a list to compare them
        List<Brand> result = bDao.getAllBrands();

        //assert the sizes are equal
        assertEquals(expResults.size(), result.size());
    }

    /**
     * This will be a mock test that will test our dummy data against the valid
     * data in our database for a given brand (based off id). These brands will
     * be then be compared based off the name supplied (to lowercase).
     *
     * @throws SQLException
     */
    @Test
    public void getBrandMockTest() throws SQLException {
        //get our expected result
        Brand b1 = new Brand(1, "nike");

        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //fill in the mock objects with the dummy data
        when(conn.prepareStatement("CALL getBrand(1)")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        //place the appropriate number of expected results
        when(rs.next()).thenReturn(true, false);
        //fill in our result set
        when(rs.getInt("id")).thenReturn(b1.getId());
        when(rs.getString("name")).thenReturn(b1.getName());
        //add the mock objects to a list
        Brand result = bDao.getBrand(1);

        //assert the sizes are equal
        assertEquals(b1.getName().toLowerCase(), result.getName().toLowerCase());
    }

}
