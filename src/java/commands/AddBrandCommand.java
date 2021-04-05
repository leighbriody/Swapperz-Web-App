/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import daos.BrandDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leigh Briody
 */
public class AddBrandCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
     
        String forwardToJsp = "error.jsp";
        
        
        if(request.getParameter("newBrand") != null){
            BrandDao bDao = new BrandDao("swapperz");
            
            int add = bDao.addNewBrand(request.getParameter("newBrand"));
            
            if(add == 1){
                //success 
                forwardToJsp = "addBrand.jsp";
                HttpSession session = request.getSession();
                session.setAttribute("message", "New Brand " + request.getParameter("newBrand") + " Added !");
            }else {
                HttpSession session = request.getSession();
                session.setAttribute("message", "New Brand " + request.getParameter("newBrand") + " Not Added .. ");
            }
         
            
        }
        
        return forwardToJsp;
    }
    
}
