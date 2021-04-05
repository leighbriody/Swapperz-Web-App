/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocking;

import daos.SecurityAnswersDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class SecurityAnswersDaoMockTest {
    
    private static SecurityAnswersDao sDao;
    
    public SecurityAnswersDaoMockTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        sDao = new SecurityAnswersDao("swapperz_testdb");
    }
    
    /**
     * This will test adding in a new security answer to our test database.
     * We will use the number of rows affected to get our result.
     * @throws SQLException 
     */
    @Test
    public void addAnswerMockTest() throws SQLException{
        //expected result
        int expResult = 0;
        
        //Create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        //Fill in mock objects with appropriate dummy data
        when(dbConn.prepareStatement("INSERT INTO securityanswers (userId, questionId, answer) VALUES (1,12,'test')")).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(expResult = 1);

        //add the address to the object
        int result = sDao.addAnswer(1, 12, "test");

        //assert they are the same
        assertEquals(expResult, result);
    }
}
