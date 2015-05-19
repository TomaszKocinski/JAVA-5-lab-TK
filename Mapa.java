/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium5;

import java.util.Vector;

/**
 *
 * @author pkacz_000
 */
public class Mapa {

    Vector<MapOfLinkedCities> MapOfLinCit;
    Miasto[][] allConnections = new Miasto[255][255];
    Integer column = 0, row = 0;

    public Mapa() {
        this.MapOfLinCit = new Vector<>();
    }

    public void dodajMiasto(Miasto miasto) throws MiastoIstniejeException {
        for (MapOfLinkedCities MapOfLinCit1 : MapOfLinCit) {
            if (miasto.equals(MapOfLinCit1.RootCity)) {
                throw new MiastoIstniejeException();
            }
        }

        MapOfLinCit.add(new MapOfLinkedCities(miasto));
    }

    public void dodajDroge(Miasto skad, Miasto dokad, int dlugosc) throws MiastoNieIstniejeException {
        if (czyIstniejeDroga(skad, dokad)) {
            return;
        }
        for (MapOfLinkedCities MapOfLinCit1 : MapOfLinCit) {
            if (skad.equals(MapOfLinCit1.RootCity)) {
                MapOfLinCit1.addConnectedCity(dokad, dlugosc);
                break;
            }
        }

    }

    public boolean czyIstniejeDroga(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException {
        DoesCitiesExistInMap(skad, dokad);
        if (skad.equals(dokad)) {
            return true;
        }
        MapOfLinkedCities tempRootCity = null;
        for (MapOfLinkedCities MapOfLinCit1 : MapOfLinCit) {
            if (skad.equals(MapOfLinCit1.RootCity)) {
                tempRootCity = MapOfLinCit1;
            }
        }

        if (tempRootCity == null) {
            throw new MiastoNieIstniejeException();
        }
        MapOfLinkedCities DestinationCity = null;

        for (CityAndDistance Conn : tempRootCity.ConnectedCitiesAndDistance) {
            if (dokad.equals(Conn.ConnectedCity)) {
                for (MapOfLinkedCities MapOfLinCit1 : MapOfLinCit) {
                    if (dokad.equals(MapOfLinCit1.RootCity)) {
                        DestinationCity = MapOfLinCit1;
                    }
                }
            }
        }
        if (DestinationCity == null) {
            for (CityAndDistance Conn : tempRootCity.ConnectedCitiesAndDistance) {
                return czyIstniejeDroga(Conn.ConnectedCity, dokad);
            }
        }
        /*if (DestinationCity == null) {
         for (int i = 0; i < tempRootCity.howMuchConnectedCity; i++) {
         return czyIstniejeDroga(tempRootCity.ConnectedCitiesAndDistance[i].ConnectedCity, dokad);
         }
         }*/
        if (DestinationCity == null) {
            return false;
        }
        return true;
    }

    public int podajDlugoscDrogi(Miasto[] trasa) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        if (!czyIstniejeDroga(trasa[0], trasa[trasa.length - 1])) {
            throw new DrogaNieIstniejeException();
        }
        MapOfLinkedCities RootCity = null;
        for (MapOfLinkedCities MapOfLinCit1 : MapOfLinCit) {
            if (trasa[0].equals(MapOfLinCit1.RootCity)) {
                RootCity = MapOfLinCit1;
            }
        }
        if (RootCity == null) {
            throw new MiastoNieIstniejeException();
        }
        int distance = 0;

        for (CityAndDistance Conn : RootCity.ConnectedCitiesAndDistance) {
            if (trasa[1].toString().equals(Conn.ConnectedCity.toString())) {
                distance = Conn.distanceBeetweenCities;
            }
        }
        /*for (int i = 0; i < RootCity.howMuchConnectedCity; i++) {
         if (trasa[1].toString().equals(RootCity.ConnectedCitiesAndDistance[i].ConnectedCity.toString())) {
         distance = RootCity.ConnectedCitiesAndDistance[i].distanceBeetweenCities;
         }
         }*/
        if (trasa.length == 2) {
            return distance;
        }
        Miasto[] new_trasa = new Miasto[trasa.length - 1];
        System.arraycopy(trasa, 1, new_trasa, 0, trasa.length - 1);
        return distance + podajDlugoscDrogi(new_trasa);
    }

    public boolean SearchForCitisInMap(Miasto from, Miasto where) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        if (!czyIstniejeDroga(from, where)) {
            throw new DrogaNieIstniejeException();
        }
        MapOfLinkedCities RootCity = null, DestinationCity = null;
        for (MapOfLinkedCities MapOfLinCit1 : MapOfLinCit) {

            if (from.equals(MapOfLinCit1.RootCity)) {
                RootCity = MapOfLinCit1;
            }
            if (where.equals(MapOfLinCit1.RootCity)) {
                DestinationCity = MapOfLinCit1;
            }
        }
        if (RootCity == null || DestinationCity == null) {
            throw new MiastoNieIstniejeException();
        }
        return true;
    }

    public Miasto[] podajNajkrotszaDroge(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        zaglebienie(skad, dokad);
        // Miasto[][] allConSkrocne;
        Miasto[] najmniejszy = null;
        int dlugosc = 9999999;
        for (int i = 0; allConnections[i] != null; i++) {
            int j;
            for (j = 0; allConnections[i][j] != null; j++);
            Miasto[] terazniejszy = new Miasto[j];
            System.arraycopy(allConnections[i], 0, terazniejszy, 0, j);
            if (terazniejszy.length < 2) {
                break;
            }
            int int_temp = podajDlugoscDrogi(terazniejszy);
            if (dlugosc > int_temp) {
                najmniejszy = terazniejszy;
                dlugosc = int_temp;
            }
        }
        return najmniejszy;
    }

    public boolean zaglebienie(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException, DrogaNieIstniejeException {

        if (!czyIstniejeDroga(skad, dokad)) {
            throw new DrogaNieIstniejeException();
        }
        MapOfLinkedCities from = null, where = null;
        for (MapOfLinkedCities MapOfLinCit1 : MapOfLinCit) {

            if (skad.toString().equals(MapOfLinCit1.RootCity.toString())) {
                from = MapOfLinCit1;
            }
            if (dokad.toString().equals(MapOfLinCit1.RootCity.toString())) {
                where = MapOfLinCit1;
            }
            if (from != null && where != null) {
                break;
            }
        }
        if (from == null || where == null) {
            throw new MiastoNieIstniejeException();
        }
        
        if (from.toString().equals(where.toString())) {
            System.arraycopy(allConnections[column], 0, allConnections[column + 1], 0, row - 1);
            allConnections[column++][row] = from.RootCity;
            return true;
        }
        for (int i = 0; i < from.ConnectedCitiesAndDistance.size(); i++) {
            allConnections[column][row++] = from.RootCity;
            for (CityAndDistance Conn : from.ConnectedCitiesAndDistance) {
                zaglebienie(Conn.ConnectedCity, dokad);
            }
            row--;
        }
        return false;
    }

    public void DoesCitiesExistInMap(Miasto from, Miasto where) throws MiastoNieIstniejeException {
        boolean FirstIsInMap = false, SecondIsInMap = false;
        for (MapOfLinkedCities MapOfLinCit1 : MapOfLinCit) {
            if (from.equals(MapOfLinCit1.RootCity)) {
                FirstIsInMap = true;
            }
            if (where.equals(MapOfLinCit1.RootCity)) {
                SecondIsInMap = true;
            }
        }
        if (!FirstIsInMap || !SecondIsInMap) {
            throw new MiastoNieIstniejeException();
        }
    }
}
