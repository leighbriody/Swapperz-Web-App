/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.Product;
import daos.ProductDao;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ME
 */
public class SearchCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "error.jsp";

        //get input...
        String colour = request.getParameter("colour");
        String brand = request.getParameter("brand");
        String size = request.getParameter("size");
        String condition = request.getParameter("condition");
        String gender = request.getParameter("gender");
        String category = request.getParameter("category");

        //as we can allow user's search without logging in, we don't need to validate
        //now we see which are not empty & add to a hash map
        //key will be the db field
        //value will be the user's selected value
        HashMap<String, Integer> intSearchTerms = new HashMap<>(); //we will need 2 hashmaps due to having a mix of id's & strings
        //check if the value is filled (will be used for building query)
        if (colour != null && !colour.equals("Colour")) {
            //parse the colourId
            int colourId = Integer.parseInt(colour);
            intSearchTerms.put("colour", colourId);
        }
        if (brand != null && !brand.equals("Brand")) {
            //parse brandId
            int brandId = Integer.parseInt(brand);
            intSearchTerms.put("brandId", brandId);
        }
        if (size != null && !size.equals("Size")) {
            int sizeId = Integer.parseInt(size);
            intSearchTerms.put("sizeId", sizeId);
        }
        //NEED TO ADD PRICE...

        //map for the string search terms
        HashMap<String, String> stringSearchTerms = new HashMap<>();
        if (!condition.equals("Condition")) {
            stringSearchTerms.put("productCondition", condition);
        }
        if (!gender.equals("Gender")) {
            stringSearchTerms.put("productGender", gender);
        }
        if (!category.equals("Category")) {
            stringSearchTerms.put("productCategory", category);
        }

        //to make sure the user hasnt tried to search for nothing...
        //We replace the && here with an or here as we are allowed some inputs to be empty
        //Its only if they are all not selected we wont search *********
        if (!intSearchTerms.isEmpty() || !stringSearchTerms.isEmpty()) {//if they are not empty go ahead
            //now that we need to call our function...
            ProductDao pDao = new ProductDao("swapperz");
            //create a list to hold the result
            ArrayList<Product> searchResults = pDao.searchedProducts(stringSearchTerms, intSearchTerms);
            //if the results are not empty, continue
            if (searchResults != null) {
                //for testing purposes
                HttpSession session = request.getSession(); // get the user's session & pass the arraylist to it
                session.setAttribute("query1", stringSearchTerms);
                session.setAttribute("query2", intSearchTerms);
                session.setAttribute("searchResults", searchResults);
                forwardToJsp = "search.jsp";
            } else {
                String errorMessage = "Whoops! Something went wrong...";
                HttpSession session = request.getSession(); // get the user's session & pass the arraylist to it
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "search.jsp";
            }

        } else {

            //**************************************
            // for testing delete when done
            boolean stringEmpty = false;
            boolean intEmpty = false;

            if (!intSearchTerms.isEmpty()) {
                intEmpty = true;
            }

            if (!stringSearchTerms.isEmpty()) {
                stringEmpty = true;
            }
            //***************************************
            String errorMessage = "You must select criteria for a search" + stringEmpty + " " + intEmpty;
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "search.jsp";
        }

        return forwardToJsp;
    }

}
