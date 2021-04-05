package commands;

import business.SecurityAnswers;
import business.User;
import daos.AddressDao;
//import daos.CardsDao;
import daos.SecurityAnswersDao;
import daos.UserDao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import static org.apache.catalina.connector.InputBuffer.DEFAULT_BUFFER_SIZE;
import utilities.Pbkdf2;

/**
 * @author Osama Kheireddine
 *
 */
public class RegisterCommand implements Command {

    /**
     * @author Osama
     * @param request
     * @param response
     * @return the user if successfully signed up, will be sent to the login
     * page
     */
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        //we need to default going to an error page
        String forwardToJsp = "error.jsp";

        //get parameters
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String email = request.getParameter("email");
        String addrLine1 = request.getParameter("addrLine1");
        String addrLine2 = request.getParameter("addrLine2");
        String town = request.getParameter("town");
        String county = request.getParameter("county");
        String country = request.getParameter("country");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");

        //dealing with the profile picture image
        int imageId = -1;

        InputStream fileContent = null;
        Part filePart = null;

        //*************************************************************
        //Dealing With The Image
        //Get the file part and put the file into an input stream
        try {
            filePart = request.getPart("profilePic"); // Retrieves <input type="file" name="file">

            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            fileContent = filePart.getInputStream();

            //We set the target we want to send this file
            String target = "C:/SwapperzWebApplication/web/images/profilePictures/" + fileName;
            File targetFile = new File(target);

            String profilePicture = "images/profilePictures/" + fileName;

            //change it to a file and storer in location
            copyInputStreamToFile(fileContent, targetFile);

            //to ensure the fields have been filled out, we will validate now...
            if (username != null && !username.equals("") && password != null && !password.equals("") && email != null && !email.equals("") && fName != null && !fName.equals("") && lName != null && !lName.equals("")) {
                UserDao uDao = new UserDao("swapperz");
                User u = new User();

                //now to hash password
                try {
                    password = Pbkdf2.genPasswordHash(password);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                    Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
                }

                //now to validate the username is unique:
                if (uDao.getUserByUsername(username) != null) {
                    //Username already exists
                    String errorMessage = "Sorry but that username already exists. Please <a href='register.jsp'>go back</a> and try again.<br/>Try a different username!";
                    HttpSession session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                } else {

                    //If we get to here, the username is unique
                    //We will want to add this user to the db, then add their address info to the db
                    //then update the user and add the corresponding id for their address to their user field in db
                    int rows = uDao.register(fName, lName, email, username, password, phone, gender, profilePicture);

                    if (rows > 0) {
                        //this means our insert to db was successful
                        //get user id now
                        int userId = uDao.getUserId(username);

                        //obtain the security Question
                        int securityQuestion1 = Integer.parseInt(request.getParameter("securityQuestion1"));
                        String securityAnswer1 = request.getParameter("answer1");
                        int securityQuestion2 = Integer.parseInt(request.getParameter("securityQuestion2"));
                        String securityAnswer2 = request.getParameter("answer2");
                        int securityQuestion3 = Integer.parseInt(request.getParameter("securityQuestion3"));
                        String securityAnswer3 = request.getParameter("answer3");

                        if (userId > -1) {
                            //add the answers to the db if the user is successfully created
                            SecurityAnswers sAns = new SecurityAnswers();

                            SecurityAnswersDao sAnsDao = new SecurityAnswersDao("swapperz");
                            //set the user id
                            sAns.setUserId(userId);
                            //set the question id
                            sAns.setQuestionId(securityQuestion1);
                            try {
                                //set the users question (we need to hash it, we can use the password hash method)
                                securityAnswer1 = Pbkdf2.genPasswordHash(securityAnswer1);
                            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                                Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //add it to the instance
                            sAns.setAnswer(securityAnswer1);
                            //add it to the db
                            sAnsDao.addAnswer(sAns.getQuestionId(), sAns.getUserId(), sAns.getAnswer());
                            //add the answers to the db if the user is successfully created
                            SecurityAnswers sAns2 = new SecurityAnswers();

                            //set the user id
                            sAns2.setUserId(userId);
                            //set the question id
                            sAns2.setQuestionId(securityQuestion2);
                            try {
                                //set the users question (we need to hash it, we can use the password hash method)
                                securityAnswer2 = Pbkdf2.genPasswordHash(securityAnswer2);
                            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                                Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //add it to the instance
                            sAns2.setAnswer(securityAnswer2);
                            //add it to the db
                            sAnsDao.addAnswer(sAns2.getQuestionId(), sAns2.getUserId(), sAns2.getAnswer());

                            //add the answers to the db if the user is successfully created
                            SecurityAnswers sAns3 = new SecurityAnswers();
                            //set the user id
                            sAns3.setUserId(userId);
                            //set the question id
                            sAns3.setQuestionId(securityQuestion3);
                            try {
                                //set the users question (we need to hash it, we can use the password hash method)
                                securityAnswer3 = Pbkdf2.genPasswordHash(securityAnswer3);
                            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                                Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //add it to the instance
                            sAns3.setAnswer(securityAnswer3);
                            //add it to the db
                            sAnsDao.addAnswer(sAns3.getQuestionId(), sAns3.getUserId(), sAns3.getAnswer());
                            
                            if (userId > -1) {
                                //create an instance of the addressdao
                                AddressDao aDao = new AddressDao("swapperz");
                                //add the address to the address table
                                int wasAddressAdded = aDao.newAddress(userId, addrLine1, addrLine2, town, county, country);
                                //now to check if successfully added
                                if (wasAddressAdded > 0) {
                                    //now we want to update the user's address by getting the address id firstly,
                                    int addressId = aDao.getAddressId(userId);
                                    //then adding that address to the user table where applicable
                                    boolean wasAddressAddedForUserTable = uDao.addAddress(userId, addressId);
                                    if (wasAddressAddedForUserTable == true) {
                                        //forward them to the login.jsp page
                                        forwardToJsp = "login.jsp";
                                    } else {
                                        //error adding to users
                                        String errorMessage = "An error occurred when adding your address.\nPlease try again.";
                                        HttpSession session = request.getSession();
                                        session.setAttribute("errorMessage", errorMessage);
                                        forwardToJsp = "register.jsp";
                                    }
                                } else {
                                    //error adding to address
                                    String errorMessage = "An error occurred when adding your address.\nPlease try again.";
                                    HttpSession session = request.getSession();
                                    session.setAttribute("errorMessage", errorMessage);
                                    forwardToJsp = "register.jsp";
                                }
                            } else {
                                //User couldn't be added to the db, output error
                                String errorMessage = "An error occurred when searching for your account.\nPlease try again.";
                                HttpSession session = request.getSession();
                                session.setAttribute("errorMessage", errorMessage);
                                forwardToJsp = "register.jsp";
                            }
                        } else {
                            //User couldn't be added to the db, output error
                            String errorMessage = "An error occurred when searching for a user id.\nPlease try again.";
                            HttpSession session = request.getSession();
                            session.setAttribute("errorMessage", errorMessage);
                            forwardToJsp = "register.jsp";
                        }
                    } else {
                        //error registering user

                        String errorMessage = "An error occurred when registering, please try again." ;
                        HttpSession session = request.getSession();
                        session.setAttribute("errorMessage", errorMessage);
                        forwardToJsp = "register.jsp";
                    }
                }

            } else {
                // One or more fields were missing
                String errorMessage = "One or more fields were missing.\nPlease try again.";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
            }
            return forwardToJsp;
        } catch (IOException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return forwardToJsp;

    }

    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }
}
