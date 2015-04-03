package pl.edu.uksw.wmp.prja.laboratorium5;

//@author kot
public class Mapa {

    Miasto[] MapOfCities;
    int wherePutNewMap;
    ContainerOfLinkedCities[] ConOfLinCit;
    int wherePutNewCon;

    public Mapa() {
        MapOfCities = new Miasto[255];
        ConOfLinCit = new ContainerOfLinkedCities[255];
        /* for (Miasto MapOfCity : MapOfCities) {
         MapOfCity.nameOfCity="";
         }
         for (ContainerOfLinkedCities ConOfLinC : ConOfLinCit) {
         ConOfLinC.A.nameOfCity="";
         ConOfLinC.B.nameOfCity="";
         }*/
        wherePutNewMap = 0;
        wherePutNewCon = 0;
    }

    public Mapa(Miasto[] MapOfCities, int whereputnewmap, ContainerOfLinkedCities[] ConOfLinCit, int wherePutNewCon) {
        this.MapOfCities = MapOfCities;
        this.wherePutNewMap = whereputnewmap;
        this.ConOfLinCit = ConOfLinCit;
        this.wherePutNewCon = wherePutNewCon;
    }

    public void dodajMiasto(Miasto miasto) throws MiastoIstniejeException {

        for (int i = 0; i < wherePutNewMap; i++) {
            if (miasto.nameOfCity.equals(MapOfCities[i].nameOfCity)) {
                throw new MiastoIstniejeException();
            }
        }
        MapOfCities[wherePutNewMap++] = miasto;
    }

    public void dodajDroge(Miasto skad, Miasto dokad, int dlugosc) throws MiastoNieIstniejeException {
        if (czyIstniejeDroga(skad, dokad)) {
            return;
        }
        ConOfLinCit[wherePutNewCon++] = new ContainerOfLinkedCities(skad, dokad, dlugosc);
    }

    public boolean czyIstniejeDroga(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException {
        DoesCitiesExistInMap(skad, dokad);
        if (skad.equals(dokad)) {
            return true;
        }
        for (int i = 0; i < wherePutNewCon; i++) {
            if (ConOfLinCit[i].A.nameOfCity.equals(skad.nameOfCity) && ConOfLinCit[i].B.nameOfCity.equals(dokad.nameOfCity)) {
                return true;
            }
        }
        return false;
    }

    public int podajDlugoscDrogi(Miasto[] trasa) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        return 0;
    }

    public Miasto[] podajNajkrotszaDroge(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        return new Miasto[]{};
    }

    public void DoesCitiesExistInMap(Miasto from, Miasto where) throws MiastoNieIstniejeException {
        boolean FirstIsInMap = false, SecoundIsInMap = false;
        for (int i = 0; i < wherePutNewMap; i++) {
            if (from.nameOfCity.equals(MapOfCities[i].nameOfCity)) {
                FirstIsInMap = true;
            }
            if (where.nameOfCity.equals(MapOfCities[i].nameOfCity)) {
                SecoundIsInMap = true;
            }
        }
        if (!FirstIsInMap || !SecoundIsInMap) {
            throw new MiastoNieIstniejeException();
        }
    }
}
