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

    public Miasto(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }
    @Override
    public String toString() {
        return nameOfCity; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object o) { // netbeans, mówi, że trzeba hashcode, nie mam pojecia co to jest, ale equals bez tego dziala.
        return o.toString().equals(this.toString()) ;
    }
}
