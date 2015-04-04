/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium5;

import java.util.Objects;

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
    public boolean equals(Object obj) {
        if(this.toString().equals(obj.toString())) return true;
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nameOfCity);
        return hash;
    }
    
    @Override
    public String toString() {
        return nameOfCity; //To change body of generated methods, choose Tools | Templates.
    }
    
}
