/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium5;

/**
 *
 * @author pkacz_000
 */
public class Miasto {
    String nameOfCity;

    public Miasto() {
        nameOfCity="";
    }
    
    public Miasto(String nazwa) {
        nameOfCity=nazwa;
    }

    @Override
    public String toString() {
        return nameOfCity; //To change body of generated methods, choose Tools | Templates.
    }
    
}
