
import business.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class SizeDaoMockTest {

    public SizeDaoMockTest() {

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
    }
}
