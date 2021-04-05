/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Size;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public interface SizeDaoInterface {

    public ArrayList<Size> getAllSizes();

    public Size getSize(int id);
}
