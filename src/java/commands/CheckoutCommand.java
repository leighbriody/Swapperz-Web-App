/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.Product;
import daos.ProductDao;
import daos.PurchaseDao;
import daos.UserDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Osama Kheireddine
 */
public class CheckoutCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;

        //Once they get to the checkout page they will be checking out will all products currently in their cart in the session
        HttpSession session = request.getSession();

        //get inputs from previous page (NOTE. AS WE ARE ONLY USING DUMMY DATA WE WILL ONLY NEED LOGIC FOR MARKING ITEMS AS SOLD)
        //make sure the user was logged in 
        String uId = request.getParameter("userId");
        int userId = Integer.parseInt(uId);
//        if its not empty continue
        if (uId != null && !uId.equals("")) {
            //user was logged in, now lets try to add their purchase to the db
            UserDao uDao = new UserDao("swapperz");
            ProductDao prodDao = new ProductDao("swapperz");
            PurchaseDao purchaseDao = new PurchaseDao("swapperz");

            ArrayList<Product> cart = new ArrayList<>();
            //now we must loop around each product in the cart arrayList
            if (session.getAttribute("cart") == null) {
                //dont do the rest
            } else {
                //do it 
                cart = (ArrayList<Product>) session.getAttribute("cart");
            }

            for (Product p : cart) {
                //try add to purchase table first
                int successfulPurchase = purchaseDao.addPurchase(userId, p.getId());

                //NOTE IF THERE ARE MULTIPLE ITEMS BEING PURCHASE, USE A LOOP TO ON THIS METHOD
                if (successfulPurchase == 1) {
                    //get the id of the addition and then add it to the product table where the status and purchase id can be changed
                    //LOOP THIS AGAIN IF NEEDED
                    int purchaseId = purchaseDao.getPurchaseId(userId, p.getId());

//                    if (prodDao.successfulPurchase(purchaseId, p.getId()) == 1) {
                        //now to mark the item as sold
                        if (prodDao.changeSoldStatus(p.getId(), "pending") == 1) {                            
//AS WE WILL SEND THE USER TO A CONFIRMATION PAGE, WE WILL NEED TO PASS THROUGH THEIR ORDER
                            session = request.getSession();

                            Product pd = prodDao.getProductById(p.getId());
                            ArrayList<Product> order = new ArrayList<Product>();
                            order.add(pd);
                            session.setAttribute("orderConfirmation", order);
                            String successMessage = "Payment successful!";
                            session.setAttribute("successMessage", successMessage);
                            forwardToJsp = "orderConfirmation.jsp";
                        } else {
                            String errorMessage = "There was an error whilst de-listing your purchase 1";
                            session = request.getSession();
                            session.setAttribute("errorMessage", errorMessage);
                            forwardToJsp = "error.jsp";
                        }
//                    } else {
//                        String errorMessage = "There was an error whilst making your purchase 2";
//                        session = request.getSession();
//                        session.setAttribute("errorMessage", errorMessage);
//                        forwardToJsp = "error.jsp";
//                    }
                } else {
                    String errorMessage = "There was an error whilst making your purchase 3";
                    session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                }

            }

        } else {
            String errorMessage = "You seem to have been logged out, please login";
            session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
    }

}
