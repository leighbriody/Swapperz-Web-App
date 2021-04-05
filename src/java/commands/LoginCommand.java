/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.User;
import daos.UserDao;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.Pbkdf2;

/**
 *
 * @author User
 */
public class LoginCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //if fields are not null, proceed
        if (username != null && password != null && !username.equals("") && !password.equals("")) {
            //create userdao instance
            UserDao uDao = new UserDao("swapperz");

            //search db to see if user exists
            User u = uDao.getUserByUsername(username);
            //if a user was found, we can continue...
            if (u != null) {
                //now to validate user's password
                boolean validUser = false;
//                if(u.getPassword().equalsIgnoreCase(password)){
                try {
                    validUser = Pbkdf2.validatePassword(password, u.getPassword());
                    //now we have to user's username & password verified
                    if (validUser) {
                        //create a user session
                        //this will be used to track if user is logged in, if its there they have successfully logged in
                        //if not, then there was an error
                        HttpSession session = request.getSession();
                        session.setAttribute("loggedInUser", u);
                        //set the forward to the index page
                        forwardToJsp = "index.jsp";
                    } else {
                        String errorMessage = "Incorrect username or password...";
                        HttpSession session = request.getSession();
                        session.setAttribute("errorMessage", errorMessage);
                        forwardToJsp = "login.jsp";
                    }
                } 
                catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                    Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String errorMessage = "Incorrect username or password...";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "login.jsp";
            }
        } else {
            String errorMessage = "One or more fields were left blank...";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "login.jsp";
        }
        return forwardToJsp;
    }

}
