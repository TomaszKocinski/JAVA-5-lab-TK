package pl.edu.uksw.wmp.prja.laboratorium5;

//@author kot
public class Mapa {
    MapOfLinkedCities[] MapOfLinCit;
    int wherePutNewCity;

    public Mapa() {
        MapOfLinCit = new MapOfLinkedCities[255];
        wherePutNewCity = 0;
    }

    public Mapa(Miasto[] MapOfCities, int whereputnewmap, MapOfLinkedCities[] ConOfLinCit, int wherePutNewCon) {
        this.MapOfLinCit = ConOfLinCit;
        this.wherePutNewCity = wherePutNewCon;
    }

    public void dodajMiasto(Miasto miasto) throws MiastoIstniejeException {
        for (int i = 0; i < wherePutNewCity; i++) {
            if (miasto.toString().equals(MapOfLinCit[i].RootCity.toString())) {
                throw new MiastoIstniejeException();
            }
        }
        MapOfLinCit[wherePutNewCity++]=new MapOfLinkedCities(miasto);
    }

    public void dodajDroge(Miasto skad, Miasto dokad, int dlugosc) throws MiastoNieIstniejeException {
        if (czyIstniejeDroga(skad, dokad)) {
            return;
        }
        for (int i = 0; i < wherePutNewCity; i++) {
            if (skad.toString().equals(MapOfLinCit[i].RootCity.toString())) {
                MapOfLinCit[i].addConnectedCity(dokad, dlugosc);
                break;
            }
        }
    }

    public boolean czyIstniejeDroga(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException {
        DoesCitiesExistInMap(skad, dokad);
        if (skad.toString().equals(dokad.toString())) {
            return true;
        }
        MapOfLinkedCities RootCity = null;
        for (int i = 0; i < wherePutNewCity; i++) {
            if (skad.toString().equals(MapOfLinCit[i].RootCity.toString())) {
                RootCity = MapOfLinCit[i];
                break;
            }
        }
        if (RootCity == null) {
            throw new MiastoNieIstniejeException();
        }
        MapOfLinkedCities DestinationCity = null;
        for (int i = 0; i < RootCity.howMuchConnectedCity; i++) {
            if (dokad.toString().equals(RootCity.DCaD[i].B.toString())) {
                DestinationCity = MapOfLinCit[i];
                break;
            }
        }
        if (DestinationCity == null) {
            for (int i = 0; i < RootCity.howMuchConnectedCity; i++) {
                return czyIstniejeDroga(RootCity.DCaD[i].B, dokad);
            }
        }
        return DestinationCity
                != null;
    }

    public int podajDlugoscDrogi(Miasto[] trasa) throws MiastoNieIstniejeException, DrogaNieIstniejeException { 
        if(!czyIstniejeDroga(trasa[0], trasa[trasa.length-1])){
            throw new DrogaNieIstniejeException();
        }
        MapOfLinkedCities RootCity = null;
        for (int i = 0; i < wherePutNewCity; i++) {
            if (trasa[0].toString().equals(MapOfLinCit[i].RootCity.toString())) {
                RootCity = MapOfLinCit[i];
                break;
            }
        }
        if (RootCity == null) {
            throw new MiastoNieIstniejeException();
        }
        int distance=0;
        for (int i = 0; i < RootCity.howMuchConnectedCity; i++) {
            if (trasa[1].toString().equals(RootCity.DCaD[i].B.toString())) {
                distance=RootCity.DCaD[i].distanceBeetweenCities;
                break;
            }
        }
        if(trasa.length==2) return distance;
        Miasto[] new_trasa=new Miasto[trasa.length-1];
        
        System.arraycopy(trasa, 1, new_trasa, 0, trasa.length-1);
        return distance+podajDlugoscDrogi(new_trasa);
    }

    public Miasto[] podajNajkrotszaDroge(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        if(!czyIstniejeDroga(skad, dokad)){
            throw new DrogaNieIstniejeException();
        }
        
        return new Miasto[]{};
    }
    
    public void DoesCitiesExistInMap(Miasto from, Miasto where) throws MiastoNieIstniejeException {
        boolean FirstIsInMap = false, SecoundIsInMap = false;
        for (int i = 0; i < wherePutNewCity; i++) {
            if (from.toString().equals(MapOfLinCit[i].RootCity.toString())) {
                FirstIsInMap = true;
            }
            if (where.toString().equals(MapOfLinCit[i].RootCity.toString())) {
                SecoundIsInMap = true;
            }
        }
        if (!FirstIsInMap || !SecoundIsInMap) {
            throw new MiastoNieIstniejeException();
        }
    }
}
