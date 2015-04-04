package pl.edu.uksw.wmp.prja.laboratorium5;
import java.util.HashMap;
import java.util.Map;

//@author kot
public class Mapa {

    Map<Miasto,ContainerOfLinkedCities> MapOfCities;

    public Mapa() {
        MapOfCities=new HashMap<Miasto,ContainerOfLinkedCities>() {};
    }
            
    public void dodajMiasto(Miasto miasto) throws MiastoIstniejeException {
       
            if (MapOfCities.containsKey(miasto)) {
                throw new MiastoIstniejeException();
            }
        

        MapOfCities.put(miasto, new ContainerOfLinkedCities());
        System.out.println("sda");
    }

    public void dodajDroge(Miasto skad, Miasto dokad, int dlugosc) throws MiastoNieIstniejeException {
        if(!MapOfCities.containsKey(skad) || !MapOfCities.containsKey(dokad)) throw new MiastoNieIstniejeException();
        else{
            MapOfCities.put(skad, new ContainerOfLinkedCities(dokad,dlugosc));
        }
    }

    public boolean czyIstniejeDroga(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException {
        
        return false;
    }

    public int podajDlugoscDrogi(Miasto[] trasa) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        return 0;
    }

    public Miasto[] podajNajkrotszaDroge(Miasto skad, Miasto dokad) throws MiastoNieIstniejeException, DrogaNieIstniejeException {
        return new Miasto[]{};
    }

    public void DoesCitiesExistInMap(Miasto from, Miasto where) throws MiastoNieIstniejeException {
      
    }
}
