/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.HashMap;

/**
 *
 * @author ME
 */
public class NewClass {

    public static void main(String[] args) {
        HashMap<String, Integer> abc = new HashMap<>();
        abc.put("colour", 4);
        String res = "";
        
        for (String field : abc.keySet()) {
            res = field;
        }
        System.out.println(res + ": " + abc.get(res));
    }
}
