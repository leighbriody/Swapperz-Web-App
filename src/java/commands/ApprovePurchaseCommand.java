/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import daos.ProductDao;
import daos.PurchaseDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Osama Kheireddine
 */
public class ApprovePurchaseCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "approvePurchases.jsp";

        //get inputs
        String buyerId = request.getParameter("buyerId");
        String sellerId = request.getParameter("sellerId");
        String productId = request.getParameter("productId");

        if (buyerId != null && !buyerId.equalsIgnoreCase("") && productId != null && !productId.equalsIgnoreCase("") && sellerId != null && !sellerId.equalsIgnoreCase("")) {
            //Parse to integer
            int bId = Integer.parseInt(buyerId);
            int sId = Integer.parseInt(sellerId);
            int prodId = Integer.parseInt(productId);

            if (bId != 0 && prodId != 0 && sId != 0) {
                //now to change the status of the item
                ProductDao pDao = new ProductDao("swapperz");
                
                //now to change the status to sold
                if (pDao.changeSoldStatus(prodId, "sold") == 1) {
                    //set a success message
                    String successMessage = "Product successfuly approved";
                    HttpSession session = request.getSession();
                    session = request.getSession();
                    session.setAttribute("successMessage", successMessage);
                } else {
                    String errorMessage = "An error occurred whilst changing the approval status in the product section";
                    HttpSession session = request.getSession();
                    session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                }
            } else {
                String errorMessage = "An input was 0";
                HttpSession session = request.getSession();
                session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
            }
        } else {
            String errorMessage = "An input caused an error";
            HttpSession session = request.getSession();
            session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
        }
        return forwardToJsp;
    }

}
