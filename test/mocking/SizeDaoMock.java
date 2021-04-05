
import business.Size;
import daos.SizeDao;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leigh Briody
 */
public class SizeDaoMock {

    public SizeDaoMock() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Test
    public void testGetAllSizes() throws SQLException {

        //Create Expected results
        Size s1 = new Size(1, "XXS");
        Size s2 = new Size(2, "XS");
        Size s3 = new Size(3, "S");
        Size s4 = new Size(4, "M");
        Size s5 = new Size(5, "L");
        Size s6 = new Size(6, "XL");
        Size s7 = new Size(7, "XXL");
        Size s8 = new Size(8, "3");
        Size s9 = new Size(9, "3.5");
        Size s10 = new Size(10, "4");
        Size s11 = new Size(11, "4.5");
        Size s12 = new Size(12, "5");
        Size s13 = new Size(13, "5.5");
        Size s14 = new Size(14, "6");
        Size s15 = new Size(15, "6.5");
        Size s16 = new Size(16, "7");
        Size s17 = new Size(17, "7.5");
        Size s18 = new Size(18, "8");
        Size s19 = new Size(19, "8.5");
        Size s20 = new Size(20, "9");
        Size s21 = new Size(21, "9.5");
        Size s22 = new Size(22, "10");
        Size s23 = new Size(23, "10.5");
        Size s24 = new Size(25, "11");
        Size s25 = new Size(25, "11.5");

        ArrayList<Size> expectedResults = new ArrayList();
        expectedResults.add(s1);
        expectedResults.add(s2);
        expectedResults.add(s3);
        expectedResults.add(s4);
        expectedResults.add(s5);
        expectedResults.add(s6);
        expectedResults.add(s7);
        expectedResults.add(s8);
        expectedResults.add(s9);
        expectedResults.add(s10);
        expectedResults.add(s11);
        expectedResults.add(s12);
        expectedResults.add(s13);
        expectedResults.add(s14);
        expectedResults.add(s15);
        expectedResults.add(s16);
        expectedResults.add(s17);
        expectedResults.add(s18);
        expectedResults.add(s19);
        expectedResults.add(s20);
        expectedResults.add(s21);
        expectedResults.add(s22);
        expectedResults.add(s23);
        expectedResults.add(s24);
        expectedResults.add(s25);

        // Create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select * from clothingsize")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        //we will be getting 25 results
        when(rs.next()).thenReturn(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false);
        //fill mock objects with dummy data
        when(dbConn.prepareStatement("Select * from clothingsize")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        //fill results 
        when(rs.getInt("id")).thenReturn(s1.getId(), s2.getId(), s3.getId(), s4.getId(), s5.getId(), s6.getId(),
                s7.getId(), s8.getId(), s9.getId(), s10.getId(), s11.getId(), s12.getId(), s13.getId(), s14.getId(),
                s15.getId(), s16.getId(), s17.getId(), s18.getId(), s19.getId(), s20.getId(), s21.getId(), s22.getId(),
                s23.getId(), s24.getId(), s25.getId());

        when(rs.getString("size")).thenReturn(s1.getSize(), s2.getSize(), s3.getSize(), s4.getSize(), s5.getSize(), s6.getSize(),
                s7.getSize(), s8.getSize(), s9.getSize(), s10.getSize(), s11.getSize(), s12.getSize(), s13.getSize(), s14.getSize(),
                s15.getSize(), s16.getSize(), s17.getSize(), s18.getSize(), s19.getSize(), s20.getSize(), s21.getSize(), s22.getSize(),
                s23.getSize(), s24.getSize(), s25.getSize());

        int numOfSizesInTable = 25;

        SizeDao sizeDao = new SizeDao("swapperz_testdb");
        List<Size> result = sizeDao.getAllSizes();

        assertEquals(numOfSizesInTable, result.size());

    }
}
