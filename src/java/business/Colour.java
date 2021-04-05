/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author ME
 */
public class Colour {
    private int id;
    
    private String colour;

    public Colour(int id, String colour) {
        this.id = id;
        this.colour = colour;
    }

    public int getId() {
        return id;
    }

    public String getColour() {
        return colour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    
    
}
