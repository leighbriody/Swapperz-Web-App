/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import daos.ProductDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leigh Briody
 */
public class RemoveAddCommand implements Command{
    
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp = "myProfile.jsp";

        String productToWithdraw = request.getParameter("id");
        int prodId = Integer.parseInt(productToWithdraw);

        ProductDao pDao = new ProductDao("swapperz");

        int relistAdd = pDao.removeAdd(prodId);

        if (relistAdd == -1) {

            String errorMessage = "Error occured removing add try again .. ";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        
        return forwardToJsp;
    }
}
