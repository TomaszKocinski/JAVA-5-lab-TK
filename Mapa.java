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
            if (miasto.nameOfCity.equals(MapOfLinCit[i].RootCity.nameOfCity)) {
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
            if (skad.nameOfCity.equals(MapOfLinCit[i].RootCity.nameOfCity)) {
                MapOfLinCit[i].addConnectedCity(dokad, dlugosc);
                break;
            }
        }
    }

    public boolean czyIstniejeDroga(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException {
        DoesCitiesExistInMap(skad, dokad);
        if (skad.nameOfCity.equals(dokad.nameOfCity)) {
            return true;
        }
        MapOfLinkedCities RootCity = null;
        for (int i = 0; i < wherePutNewCity; i++) {
            if (skad.nameOfCity.equals(MapOfLinCit[i].RootCity.nameOfCity)) {
                RootCity = MapOfLinCit[i];
                break;
            }
        }
        if (RootCity == null) {
            throw new MiastoNieIstniejeException();
        }
        MapOfLinkedCities DestinationCity = null;
        for (int i = 0; i < RootCity.howMuchConnectedCity; i++) {
            if (dokad.nameOfCity.equals(RootCity.DCaD[i].B.nameOfCity)) {
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
        return 0;
    }

    public Miasto[] podajNajkrotszaDroge(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        return new Miasto[]{};
    }
    
    public void DoesCitiesExistInMap(Miasto from, Miasto where) throws MiastoNieIstniejeException {
        boolean FirstIsInMap = false, SecoundIsInMap = false;
        for (int i = 0; i < wherePutNewCity; i++) {
            if (from.nameOfCity.equals(MapOfLinCit[i].RootCity.nameOfCity)) {
                FirstIsInMap = true;
            }
            if (where.nameOfCity.equals(MapOfLinCit[i].RootCity.nameOfCity)) {
                SecoundIsInMap = true;
            }
        }
        if (!FirstIsInMap || !SecoundIsInMap) {
            throw new MiastoNieIstniejeException();
        }
    }
}
