/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.Address;
import business.User;
import daos.AddressDao;
import daos.UserDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Osama Kheireddine
 */
public class EditUserDetailsCommand implements Command {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "editProfile.jsp";
        //get user details
        String username = request.getParameter("username");
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        //get user address details
        String line1 = request.getParameter("line1");
        String line2 = request.getParameter("line2");
        String town = request.getParameter("town");
        String county = request.getParameter("county");
        String country = request.getParameter("country");

        //process the information
        //get user id for creating a new address if needed
        UserDao uDao = new UserDao("swapperz");
        int userId = uDao.getUserId(username);

        //edit user details now
        //if any details are the same go to else
        User u = uDao.getUserById(userId);
        if (u.getfName().equals(fName) && u.getlName().equals(lName) && u.getEmail().equals(email) && u.getPhone().equals(phone)) {
            String errorMessage = "You must use different data to change your user account details";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "editProfle.jsp";
        } else {
            boolean updatedUser = uDao.updateUserDetails(userId, fName, lName, email, phone);

            if (updatedUser) {
                String successMessage = "Your details were successfully updated!";
                HttpSession session = request.getSession();
                session.setAttribute("successMessage", successMessage);
                //obtain user session, delete it & remake by changing variables
                u = uDao.getUserById(userId);
                session.removeAttribute("loggedInUser");
                session.setAttribute("loggedInUser", u);                
                forwardToJsp = "editProfile.jsp";
            } else {
                String errorMessage = "An error occurred whilst updating your details...\nPlease try again later.";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "editProfle.jsp";
            }
        }

        //get the user address & compare them. if theyre the same dont add
        AddressDao aDao = new AddressDao("swapperz");
        Address newAddress = new Address(userId, line1, line2, town, county, country);
        Address userAddress = aDao.getUserAddress(userId);
        //carry on and update the address
        if (!userAddress.equals(newAddress)) {
            int success = aDao.newAddress(userId, line1, line2, town, county, country);
            if (success == 1) {
                //now update the user's address in the user table
                boolean updateUserAddress = uDao.addAddress(userId, aDao.getAddressId(userId));
                if (updateUserAddress == true) {
                    //set the session attribute to say it was successful
                    String successMessage = "Your details were successfully updated!";
                    HttpSession session = request.getSession();
                    session.setAttribute("successMessage", successMessage);
                    forwardToJsp = "editProfile.jsp";
                } else {
                    String errorMessage = "An error occurred whilst updating your details...\nPlease try again later.";
                    HttpSession session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "editProfle.jsp";
                }
            } else {
                String errorMessage = "An error occurred whilst updating your details...\nPlease try again later.";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "editProfle.jsp";
            }
        }

        return forwardToJsp;
    }

}
