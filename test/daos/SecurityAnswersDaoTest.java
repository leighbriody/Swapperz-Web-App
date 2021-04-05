/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

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
public class SecurityAnswersDaoTest {
    
    private static SecurityAnswersDao sDao;
    
    public SecurityAnswersDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        sDao = new SecurityAnswersDao("swapperz_testdb");
    }

    /**
     * Test of addAnswer method, of class SecurityAnswersDao.
     * This will be tested by inserting an answer with a valid user id and to fail we will use an invalid user id.
     * The same will be done with in/valid questions id's
     */
    @Test
    public void givenAddAnswer_whenAllValidInput_return1() {
        System.out.println("addAnswer() - valid user & question Id - success");
        int questionId = 1;
        int userId = 12;
        String answer = "test answer";
        int expResult = 1;
        int result = sDao.addAnswer(questionId, userId, answer);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addAnswer method, of class SecurityAnswersDao.
     * This will be tested by inserting an answer with a valid user id and to fail we will use an invalid user id.
     * The same will be done with in/valid questions id's
     */
    @Test
    public void givenAddAnswer_whenValidUserIdAndInvalidQuestionId_return0() {
        System.out.println("addAnswer() - valid userId - success");
        int questionId = -1;
        int userId = 12;
        String answer = "test answer";
        int expResult = 0;
        int result = sDao.addAnswer(questionId, userId, answer);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of addAnswer method, of class SecurityAnswersDao.
     * This will be tested by inserting an answer with a valid user id and to fail we will use an invalid user id.
     * The same will be done with in/valid questions id's
     */
    @Test
    public void givenAddAnswer_whenInvalidUserIdAndValidQuestionId_return0() {
        System.out.println("addAnswer() - valid userId - success");
        int questionId = 1;
        int userId = -1;
        String answer = "test answer";
        int expResult = 0;
        int result = sDao.addAnswer(questionId, userId, answer);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of addAnswer method, of class SecurityAnswersDao.
     * This will be tested by inserting an answer with a valid user id and to fail we will use an invalid user id.
     * The same will be done with in/valid questions id's
     */
    @Test
    public void givenAddAnswer_whenNoValidInput_return0() {
        System.out.println("addAnswer() - valid userId - success");
        int questionId = -1;
        int userId = -1;
        String answer = "test answer";
        int expResult = 0;
        int result = sDao.addAnswer(questionId, userId, answer);
        assertEquals(expResult, result);
    }
    
}
