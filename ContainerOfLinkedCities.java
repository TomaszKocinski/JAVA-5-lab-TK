/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium5;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Kot
 */
public class ContainerOfLinkedCities {
    Map<Miasto, Integer> MapOfLinkedCities;
    

    public ContainerOfLinkedCities() {
      MapOfLinkedCities=new HashMap<>();
    }

    public ContainerOfLinkedCities(Map<Miasto, Integer> VecOfLinkedCities) {
        this.MapOfLinkedCities = VecOfLinkedCities;
    }
    public ContainerOfLinkedCities(Miasto City, Integer Inte) {
        MapOfLinkedCities.put(City, Inte);
    }
    
   
    
}
