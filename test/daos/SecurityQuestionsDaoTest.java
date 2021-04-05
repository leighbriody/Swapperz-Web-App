/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.SecurityQuestions;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Osama Kheireddine
 */
public class SecurityQuestionsDaoTest {
    
    private static SecurityQuestionsDao sDao;
    
    public SecurityQuestionsDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        sDao = new SecurityQuestionsDao("swapperz_testdb");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllQuestions method, of class SecurityQuestionsDao.
     * We will test to see the number of results that will be returned.
     * We will check our database to get the figure for this test.
     */
    @Test
    public void givenGetAllQuestions_whenMethodIsCalled_returnNumberOfQuestions() {
        System.out.println("getAllQuestions() - success");
        int expResult = 8;
        List<SecurityQuestions> result = sDao.getAllQuestions();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of changeExisitingQuestion method, of class SecurityQuestionsDao.
     * 
     * METHOD NOT YET COMPLETE! 
     * TEST WILL FAIL
     */
    @Test
    public void testChangeExisitingQuestion() {
        System.out.println("changeExisitingQuestion");
        SecurityQuestionsDao instance = null;
        boolean expResult = false;
        boolean result = instance.changeExisitingQuestion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
