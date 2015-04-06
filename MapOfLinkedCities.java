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
class CityAndDistance{
    Miasto B;
    int distanceBeetweenCities;

    public CityAndDistance() {
    distanceBeetweenCities=0;
    }

    public CityAndDistance(Miasto B, int distanceBeetweenCities) {
        this.B = B;
        this.distanceBeetweenCities = distanceBeetweenCities;
    }
    
}
public class MapOfLinkedCities {
    Miasto RootCity;
    CityAndDistance[] DCaD;
    public int howMuchConnectedCity;
    public MapOfLinkedCities() {
        DCaD=new CityAndDistance[255];
        howMuchConnectedCity=0;
    }
    public MapOfLinkedCities(Miasto A) {
        this.RootCity = A;
        DCaD=new CityAndDistance[255];
        howMuchConnectedCity=0;
    }
    public MapOfLinkedCities(Miasto A, CityAndDistance[] DCaD, int howMuchConnectedCity) {
        this.RootCity = A;
        this.DCaD = DCaD;
        this.howMuchConnectedCity = howMuchConnectedCity;
    }
    /*public MapOfLinkedCities(Miasto A, Miasto dokad, int howMuchConnectedCity) {
        this.RootCity = A;
        addConnectedCity(dokad, howMuchConnectedCity);
        this.howMuchConnectedCity = howMuchConnectedCity;
    }*/
    public void addConnectedCity(Miasto where, int distance){
        /*DCaD[howMuchConnectedCity].B=where;
        DCaD[howMuchConnectedCity++].distanceBeetweenCities=distance;
                */
        DCaD[howMuchConnectedCity++]=new CityAndDistance(where, distance);
    }
    
    
}
