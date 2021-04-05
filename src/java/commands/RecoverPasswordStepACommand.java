/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.User;
import daos.UserDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Osama Kheireddine
 */
public class RecoverPasswordStepACommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "recoverPasswordStepB.jsp";

        //get username
        String username = request.getParameter("username");

        //make sure it's not left blank
        if (username != null && !username.equals("")) {
            //now check the username exists
            UserDao uDao = new UserDao("swapperz");
            User u = uDao.getUserByUsername(username);
            if (u != null) {
                //now we can forward the user onwards to the next page!
                String successMessage = "Please answer your security questions to continue";
                HttpSession session = request.getSession();
                session.setAttribute("user", u);
                session.setAttribute("successMessage", successMessage);
            } else {
                String errorMessage = "This user does not exist!";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "recoverPassword.jsp";
            }
        } else {
            String errorMessage = "You must enter a username...";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "recoverPassword.jsp";
        }

        return forwardToJsp;
    }

}
