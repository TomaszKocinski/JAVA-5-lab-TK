package pl.edu.uksw.wmp.prja.laboratorium5;

//@author kot
public class Mapa {
    MapOfLinkedCities[] MapOfLinCit;
    int wherePutNewCity;
    Miasto[][] allConnections=new Miasto[255][255];
    Integer column=0,row=0;
        
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
        MapOfLinkedCities tempRootCity = null;
        for (int i = 0; i < wherePutNewCity; i++) {
            if (skad.toString().equals(MapOfLinCit[i].RootCity.toString())) {
                tempRootCity = MapOfLinCit[i];
                break;
            }
        }
        if (tempRootCity == null) {
            throw new MiastoNieIstniejeException();
        }
        MapOfLinkedCities DestinationCity = null;
        for (int i = 0; i < tempRootCity.howMuchConnectedCity; i++) {
            if (dokad.toString().equals(tempRootCity.DCaD[i].B.toString())) {
                DestinationCity = MapOfLinCit[i];
                break;
            }
        }
        if (DestinationCity == null) {
            for (int i = 0; i < tempRootCity.howMuchConnectedCity; i++) {
                return czyIstniejeDroga(tempRootCity.DCaD[i].B, dokad);
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
        zaglebienie(skad, dokad);
       // Miasto[][] allConSkrocne;
        Miasto[] najmniejszy=null;
        int dlugosc=9999999;
        for (int i = 0; allConnections[i]!=null; i++) {
            
            int j;
            for ( j = 0; allConnections[i][j]!=null; j++) {
            }
            Miasto[] terazniejszy=new Miasto[j];
            System.arraycopy(allConnections[i], 0, terazniejszy, 0, j);
            if(terazniejszy.length<2)break;
            int int_temp=podajDlugoscDrogi(terazniejszy);
            if(dlugosc>int_temp){
                najmniejszy=terazniejszy;
                dlugosc=int_temp;
            }
            
            
        }
        
        
        return najmniejszy;
    }
    public boolean zaglebienie(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        System.out.println(column+" "+row);   
        if(!czyIstniejeDroga(skad, dokad)){
            throw new DrogaNieIstniejeException();
        }
        MapOfLinkedCities from=null,where=null;
        for (int i = 0; i < wherePutNewCity; i++) {
            if (skad.toString().equals(MapOfLinCit[i].RootCity.toString())) {
                from = MapOfLinCit[i];
            }
            if (dokad.toString().equals(MapOfLinCit[i].RootCity.toString())) {
                where = MapOfLinCit[i];
            }
            if (from != null && where !=null) break;
        }
        if (from == null || where==null) {
            throw new MiastoNieIstniejeException();
        }
        if(from.toString().equals(where.toString()))
        {
            System.arraycopy(allConnections[column], 0, allConnections[column+1], 0, row-1);
            allConnections[column++][row]=from.RootCity;
            return true;
        }
        for (int i = 0; i < from.howMuchConnectedCity; i++) {
            allConnections[column][row++]=from.RootCity;
            zaglebienie(from.DCaD[i].B, dokad);
                row--;
        }
        
        return false;
     
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
