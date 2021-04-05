/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import daos.ProductImageDao;
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
import javax.servlet.http.Part;
import static org.apache.catalina.connector.InputBuffer.DEFAULT_BUFFER_SIZE;

/**
 *
 * @author Leigh Briody
 */
public class UploadImageCommand implements Command {
//What should the response be

    String forwardToJsp = null;

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {

        //What should the response be
       forwardToJsp = "sellProduct.jsp";

        InputStream fileContent = null;
        try {
            //Get the file part and put the file into an input stream
            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            fileContent = filePart.getInputStream();

            //We set the target we want to send this file
            String target = "C:/SwapperzWebApplication/web/images/productImages/" + fileName;
            File targetFile = new File(target);

            //change it to a file and storer in location
            copyInputStreamToFile(fileContent, targetFile);

            //now that the image is put into the c folder i want to insert the name into the database 
            
            
            
            //imageDao. insert image name into database images/imageName.png
            ProductImageDao prodImgDao = new ProductImageDao("swapperz");
            prodImgDao.insertImageLocation("images/productImages/" + fileName);
            
            
            //if that was a success 
            //we then need to get the latest image id
            //and find out which user is using the app 
            //and insert image 
            
            //get the image if for that file
      

           

        } catch (IOException ex) {
            Logger.getLogger(UploadImageCommand.class.getName()).log(Level.SEVERE, null, ex);
            forwardToJsp = "error.jsp";
        } catch (ServletException ex) {
            Logger.getLogger(UploadImageCommand.class.getName()).log(Level.SEVERE, null, ex);
            forwardToJsp = "error.jsp";
        } finally {
            try {
                fileContent.close();
            } catch (IOException ex) {
                Logger.getLogger(UploadImageCommand.class.getName()).log(Level.SEVERE, null, ex);
                forwardToJsp = "error.jsp";

            }
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
