/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Leigh Briody
 */
public class deleteThis {
     public static void main(String[] args){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            
            
           // Files.move(from, to, CopyOption... options).
            
           
           
             File chosenPic = chooser.getSelectedFile();
             //File pic = new File(sourcePath)
             boolean isMoved = chosenPic.renameTo(new File(""));
             
             String fileName = chosenPic.getName();
             
            
             String newFilePath = "C:/Users/Leigh Briody/Desktop/newlocation/" +fileName;
             
             System.out.println(chosenPic.renameTo(new File(newFilePath)));
             System.out.println(newFilePath);
        
        }     
    }
}
