/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Colour;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public interface ColourDaoInterface {
    public ArrayList<Colour> getAllColours();
    
    //returns the color object given the id
    public Colour getColour(int id);
}
