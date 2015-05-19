/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium10;

import java.util.Vector;

/**
 *
 * @author pkacz_000
 */
public class Mapa {

    Vector<MapOfLinkedCities> MapOfLinCit;

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
        if (czyIstniejeBezposredniaDroga(skad, dokad)) {
            return;
        }
        for (MapOfLinkedCities MapOfLinCit1 : MapOfLinCit) {
            if (skad.equals(MapOfLinCit1.RootCity)) {
                MapOfLinCit1.addConnectedCity(dokad, dlugosc);
                break;
            }
        }

    }

    public boolean czyIstniejeBezposredniaDroga(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException {
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
            return false;
        }
        return true;
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
        Vector<Miasto> Najkrotsze_miasto = new Vector<>();
        {
            Vector<Miasto> Tymczasowe_miasto = new Vector<>();
            zaglebienie(skad, dokad, Najkrotsze_miasto, Tymczasowe_miasto);
        }
        for (Miasto Najkrotsze_miasto1 : Najkrotsze_miasto) {
            System.out.print(Najkrotsze_miasto1.toString());
        }
        System.out.println("");
        return Najkrotsze_miasto.toArray(new Miasto[Najkrotsze_miasto.size()]);
    }

    public boolean zaglebienie(Miasto skad, Miasto dokad, Vector<Miasto> Najkrotsze_miasto, Vector<Miasto> Tymczasowe_miasto) throws MiastoNieIstniejeException, DrogaNieIstniejeException {

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
            Tymczasowe_miasto.add(from.RootCity);
            if (Najkrotsze_miasto.isEmpty()) {
                Najkrotsze_miasto.addAll(Tymczasowe_miasto);
            } else if (podajDlugoscDrogi(Najkrotsze_miasto.toArray(new Miasto[Najkrotsze_miasto.size()])) > podajDlugoscDrogi(Tymczasowe_miasto.toArray(new Miasto[Tymczasowe_miasto.size()]))) {
                Najkrotsze_miasto.clear();
                Najkrotsze_miasto.addAll(Tymczasowe_miasto);
            }
            return true;
        }
        for (int i = 0; i < from.ConnectedCitiesAndDistance.size(); i++) {
            if (Tymczasowe_miasto.contains(from.RootCity)) {
                return false;
            }
            if(czyIstniejeBezposredniaDroga(skad, dokad)){
                
            
            
            
            }
            Tymczasowe_miasto.add(from.RootCity);
            
            for (CityAndDistance Conn : from.ConnectedCitiesAndDistance) {
                if (!zaglebienie(Conn.ConnectedCity, dokad, Najkrotsze_miasto, Tymczasowe_miasto)) {
                    continue;
                }
                Tymczasowe_miasto.removeElementAt(Tymczasowe_miasto.size() - 1);
            }
        }
        return true;
    }

    public void DoesCitiesExistInMap(Miasto from, Miasto where) throws MiastoNieIstniejeException {
        boolean FirstIsInMap = false, SecondIsInMap = false;
        //System.out.println(from.toString() + where.toString());
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
