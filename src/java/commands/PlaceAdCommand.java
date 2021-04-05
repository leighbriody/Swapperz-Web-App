/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import daos.ProductDao;
import daos.ProductImageDao;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import static org.apache.catalina.connector.InputBuffer.DEFAULT_BUFFER_SIZE;

/**
 *
 * @author Osama Kheireddine
 */
public class PlaceAdCommand implements Command {

    /**
     * This implementation of doAction will allow the user's advertisement
     * details to be uploaded to the db
     *
     * @param request
     * @param response
     * @return user will be sent to relevant page (forwardToJsp)
     */
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "error.jsp";

        //get parameters
        String userIdStr = request.getParameter("userId");
        int userId = Integer.parseInt(userIdStr);
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String colourStr = request.getParameter("colour");
        String brandStr = request.getParameter("brand");
        String priceStr = request.getParameter("price");
        String size = request.getParameter("size");
        String productCondition = request.getParameter("productCondition");
        String productGender = request.getParameter("productGender");
        String productCategory = request.getParameter("productCategory");
        int imageId = -1;

        InputStream fileContent = null;
        Part filePart = null;

        //*************************************************************
        //Dealing With The Image
        //Get the file part and put the file into an input stream
        try {
            filePart = request.getPart("file"); // Retrieves <input type="file" name="file">

            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            fileContent = filePart.getInputStream();

            //We set the target we want to send this file
            String target = "C:/SwapperzWebApplication/web/images/productImages/" + fileName;
            File targetFile = new File(target);

            //now that the image is put into the c folder i want to insert the name into the database
            //imageDao. insert image name into database images/imageName.png
            ProductImageDao prodImgDao = new ProductImageDao("swapperz");
            int prodImgInsertRows = prodImgDao.insertImageLocation("images/productImages/" + fileName);

            //change it to a file and storer in location
            copyInputStreamToFile(fileContent, targetFile);

            //IF THE IMAGE INSERTED CORRECTLY
            if (prodImgInsertRows > 0) {
                //the image inserted successfully
                //THEN WE NEED TO GET THAT ID
                imageId = prodImgDao.getLastInsertedId();
            } else {
                String errorMessage = "an error occurred while uploading the image..." + target;
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

            //**********
            //**********************THIS DEALS WITH EVERYTHING ELSE BUT THE IIMAE*********************************//
            //Now when we are inserting the new product we must use this id for the image
            //make sure the use was logged in
            if (userId > 0) {
                //check all fields are valid
                if (productName != null && !productName.equals("") && description != null && !description.equals("") && colourStr != null && !colourStr.equals("") && brandStr != null && !brandStr.equals("") && priceStr != null && !priceStr.equals("") && size != null && !size.equals("") && productCondition != null && !productCondition.equals("") && productGender != null && !productGender.equals("") && productCategory != null && !productCategory.equals("")) {
                    ProductDao pDao = new ProductDao("swapperz");
                    //NOW TO PARSE THE NUMBERS
                    int colour = Integer.parseInt(colourStr);
                    int brand = Integer.parseInt(brandStr);
                    try {
                        Double price = Double.parseDouble(priceStr);
                        //now we can add the product
                        int rows = pDao.addProduct(userId, productName, description, colour, brand, price, size, productCondition, imageId, productGender, productCategory);
                        //if rows = 0 then ad successfully placed
                        if (rows == 1) {
                            String successMessage = "Your ad was successfully listed" + " ";
                            HttpSession session = request.getSession();
                            session.setAttribute("successMessage", successMessage);
                            forwardToJsp = "ownListedProducts.jsp";
                        } else {
                            //error listing ad
                            String errorMessage = "An error occurred when listing your ad.\nPlease try again later.";
                            HttpSession session = request.getSession();
                            session.setAttribute("errorMessage", errorMessage);
                            forwardToJsp = "sellProduct.jsp";
                        }
                    } catch (NumberFormatException e) {
                        String errorMessage = "Please enter a valid price";
                        HttpSession session = request.getSession();
                        session.setAttribute("errorMessage", errorMessage);
                        forwardToJsp = "sellProduct.jsp";
                    }
                } else {
                    // One or more fields were missing
                    String errorMessage = "You left some fields missing, please fill them out";
                    HttpSession session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "sellProduct.jsp";
                }
            } else {
                // One or more fields were missing
                String errorMessage = "You must be logged in to place an ad.<\n>Please <a href='login.jsp'>login</a>";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
            }
        } catch (IOException | ServletException ex) {
            //error adding to address
            String errorMessage = ex.toString();
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
            Logger.getLogger(PlaceAdCommand.class.getName()).log(Level.SEVERE, null, ex);
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
