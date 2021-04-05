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
public class BuyNowCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp = "error.jsp";

        int productId = Integer.parseInt(request.getParameter("productId"));
        ArrayList<Product> cart = new ArrayList<>();

        //with the buy now command we have to take into account what if 
        //the user clicks buy now but already has items in their cart
        //we want to bring them to the checkout with everything in their cart
        ProductDao pDao = new ProductDao("swapperz");

        Product buyNowProduct = pDao.getProductById(productId);

        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            //then there is no cart currently in the sessio
            //we create a cart array list 

            cart.add(buyNowProduct);
            //add product to the cart
            //add cart to the session 
            session.setAttribute("cart", cart);
            //redirect user to checkout page
            forwardToJsp = "checkout.jsp";

        } else {
            //there is currently a cart in the session
            //we add the item they clicked by now on to the cart
            cart = (ArrayList<Product>) session.getAttribute("cart");
            cart.add(buyNowProduct);
            session.removeAttribute("cart");
            session.setAttribute("cart", cart);

            //and go to checout
            forwardToJsp = "checkout.jsp";
        }

        return forwardToJsp;
    }

}
