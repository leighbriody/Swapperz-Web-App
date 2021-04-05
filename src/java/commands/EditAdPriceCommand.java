/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.User;
import daos.ProductDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Osama Kheireddine
 */
public class EditAdPriceCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "myProfile.jsp?adds=forSale";

        //get user's id to make sure that they cannot change another user's ad price
        String uId = request.getParameter("userId");
        //parse
        int userId = Integer.parseInt(uId);
        //get ad id
        String adId = request.getParameter("adId");
        //cast it to an int
        int id = Integer.parseInt(adId);
        //get new price
        String price = request.getParameter("newPrice");
        //parse price
        double newPrice = Double.parseDouble(price);
        //to validate
        //get session id attribute
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser.getId() == userId) {
            //to validate
            if (adId != null && !adId.equals("") && price != null && !price.equals("")) {
                //create a product dao & call the function to update the ad
                ProductDao pDao = new ProductDao("swapperz");
                boolean successfulUpdate = pDao.editAdPrice(id, newPrice);

                if (successfulUpdate) {
                    String successMessage = "Your ad has now been updated!";
                    session = request.getSession(); // get the user's session & pass the arraylist to it
                    session.setAttribute("successMessage", successMessage);
                    forwardToJsp = "myProfile.jsp?adds=forSale";
                } else {
                    String errorMessage = "Uh oh...\nSomething went wrong...";
                    session = request.getSession(); // get the user's session & pass the arraylist to it
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "myProfile.jsp?adds=forSale";
                }
            } else {
                String errorMessage = "Uh oh...\nSomething went wrong...";
                session = request.getSession(); // get the user's session & pass the arraylist to it
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "myProfile.jsp?adds=forSale";
            }
        } else {
            String errorMessage = "Uh oh...\nSomething went wrong...";
            session = request.getSession(); // get the user's session & pass the arraylist to it
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "myProfile.jsp?adds=forSale";
        }

        return forwardToJsp;
    }

}
