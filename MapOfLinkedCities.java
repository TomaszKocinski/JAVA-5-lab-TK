/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium5;

import java.util.Vector;

/**
 *
 * @author student
 */
class CityAndDistance {

    Miasto ConnectedCity;
    int distanceBeetweenCities;

    public CityAndDistance(Miasto B, int distanceBeetweenCities) {
        this.ConnectedCity = B;
        this.distanceBeetweenCities = distanceBeetweenCities;
    }
}

public class MapOfLinkedCities {

    Miasto RootCity;
    Vector<CityAndDistance> ConnectedCitiesAndDistance;
    public int howMuchConnectedCity;

    public MapOfLinkedCities(Miasto RootCity, Vector<CityAndDistance> ConnectedCitiesAndDistance, int howMuchConnectedCity) {
        this.RootCity = RootCity;
        this.ConnectedCitiesAndDistance = ConnectedCitiesAndDistance;
        this.howMuchConnectedCity = howMuchConnectedCity;
    }
    public MapOfLinkedCities(Miasto RootCity) {
        this.RootCity = RootCity;
        ConnectedCitiesAndDistance = new Vector<>();
        howMuchConnectedCity = 0;
    }
    public void addConnectedCity(Miasto where, int distance) {
        ConnectedCitiesAndDistance.add(new CityAndDistance(where, distance)) ;
    }
}
