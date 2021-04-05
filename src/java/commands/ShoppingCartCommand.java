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
public class ShoppingCartCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp = "error.jsp";

        //We get the id of the product that the user wishes to add to cart
        int productId = Integer.parseInt(request.getParameter("productId"));

        //Create a dao of the product
        ProductDao pDao = new ProductDao("swapperz");

        //Create the session
        HttpSession session = request.getSession();

        //Create a product objecy
        Product newItem = pDao.getProductById(productId);

        try {

            //try get the cart in the session
            ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");

            //if we get it it means we have an item in the cart      
            //add to that item list 
            System.out.println(cart.toString());
            cart.add(newItem);

            session.setAttribute("cart", cart);

            forwardToJsp = "index.jsp";

        } catch (Exception e) {
            System.out.println(e.toString());
            ArrayList<Product> cart = new ArrayList<>();
            cart.add(newItem);

            String errorMessage = e.toString();

            session.setAttribute("errorMessage", errorMessage);

            session.setAttribute("cart", cart);

            forwardToJsp = "index.jsp";
        }

        return forwardToJsp;

    }

}
