/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.Product;
import daos.ProductDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leigh Briody
 */
public class RemoveItemFromCartCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp = "checkout.jsp";
        //When the useer wishes to remove an item from the cart

        //We get the id of the product they want to remove
        int productId = Integer.parseInt(request.getParameter("productId"));

        //we need to send over the whole cart
        //with the id of the 1 we want to remove
        //create a product dao
        ProductDao pDao = new ProductDao("swapperz");

        //Get the product as an object that they want to remove
        Product prodToRemove = pDao.getProductById(productId);

        //Create the session
        //When we create a session it removes the cart
        HttpSession session = request.getSession();

        ArrayList<Product> cart = new ArrayList<>();

        cart = (ArrayList<Product>) session.getAttribute("cart");

        //try get the cart in the session
        if (session.getAttribute("cart") != null) {
            //then there is no cart session so they should not be here
            //we get the cart
            cart = (ArrayList<Product>) session.getAttribute("cart");
            //we remove the item we want to remove
            cart.remove(prodToRemove);
            //remove the cart from session
            session.removeAttribute("cart");
            //add back the cart with item removed
            session.setAttribute("cart", cart);
            forwardToJsp = "cart.jsp";
        } else {

        }

        return forwardToJsp;

        //we want to take that id
        //get the cart from the session
        //remove the product from the cart
    }

}
