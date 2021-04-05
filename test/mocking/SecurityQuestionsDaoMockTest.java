/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocking;

import business.SecurityQuestions;
import daos.SecurityQuestionsDao;
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
public class SecurityQuestionsDaoMockTest {
    
    private static SecurityQuestionsDao sDao;
    
    public SecurityQuestionsDaoMockTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        sDao = new SecurityQuestionsDao("swapperz_testdb");
    }
    
    /**
     * This will retrieve all security questions from our test database.
     * We will then compare the size of our expected list and actual list to verify our results.
     * @throws SQLException 
     */
    @Test
    public void getAllQuestions() throws SQLException{
        //create expected data
        SecurityQuestions s1 = new SecurityQuestions(1, "What was the name of your childhood pet?");
        SecurityQuestions s2 = new SecurityQuestions(1, "Where did you go to primary school?");
        SecurityQuestions s3 = new SecurityQuestions(1, "What was the house number and street name you lived in as a child?");
        SecurityQuestions s4 = new SecurityQuestions(1, "What were the last four digits of your childhood telephone number?");
        SecurityQuestions s5 = new SecurityQuestions(1, "In what town or city was your first full time job?");
        SecurityQuestions s6 = new SecurityQuestions(1, "What is your grandmother's (on your mother's side) maiden name?");
        SecurityQuestions s7 = new SecurityQuestions(1, "What is your grandmother's (on your mother's side) maiden name?");
        SecurityQuestions s8 = new SecurityQuestions(1, "When did you get your first car?");
        
        //ADD IT TO A LIST
        ArrayList<SecurityQuestions> expResult = new ArrayList();
        expResult.add(s1);
        expResult.add(s2);
        expResult.add(s3);
        expResult.add(s4);
        expResult.add(s5);
        expResult.add(s6);
        expResult.add(s7);
        expResult.add(s8);
     
        //create our mock objects
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        //fill in the mock objects with the dummy data
        when(conn.prepareStatement("SELECT * FROM securityQuestions")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        //place the appropriate number of expected results
        when(rs.next()).thenReturn(true,true,true,true,true,true,true,true,true,false);
        
        when(rs.getInt("id")).thenReturn(s1.getId(), s2.getId(), s3.getId(), s4.getId(), s5.getId(), s6.getId(), s7.getId(), s8.getId());
        when(rs.getString("question")).thenReturn(s1.getQuestion(), s2.getQuestion(), s3.getQuestion(), s4.getQuestion(), s5.getQuestion(), s6.getQuestion(), s7.getQuestion(), s8.getQuestion());
        
        //get the result
        List<SecurityQuestions> result = sDao.getAllQuestions();
        
        //assert they are the same
        assertEquals(expResult.size(), result.size());
    }
}
