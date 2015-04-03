/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium5;

/**
 *
 * @author Kot
 */
public class ContainerOfLinkedCities {
    Miasto A;
    Miasto B;
    int distanceBeetweenCities;

    public ContainerOfLinkedCities() {
        
    }
    
    public ContainerOfLinkedCities(Miasto A, Miasto B, int distanceBeetweenCities) {
        this.A = A;
        this.B = B;
        this.distanceBeetweenCities = distanceBeetweenCities;
    }
    
}
