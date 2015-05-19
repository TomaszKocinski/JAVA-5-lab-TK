/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium10;

import pl.edu.uksw.wmp.prja.laboratorium10.MiastoIstniejeException;
import pl.edu.uksw.wmp.prja.laboratorium10.MiastoNieIstniejeException;
import pl.edu.uksw.wmp.prja.laboratorium10.Mapa;
import pl.edu.uksw.wmp.prja.laboratorium10.Miasto;
import pl.edu.uksw.wmp.prja.laboratorium10.DrogaNieIstniejeException;
import org.junit.*;
import static org.junit.Assert.*;

public class TestyLab10TK {

    private Mapa mapa;
    
    @Before
    public void setUp() {
        mapa = new Mapa();
    }
    
   
    @Test()
    public void najkrotszaDrogaPrzypadek2_Petla() throws MiastoNieIstniejeException, MiastoIstniejeException, DrogaNieIstniejeException {
        System.out.println("Test 2: powinno byc ABC");
        Miasto m0 = new Miasto("A");
        Miasto m1 = new Miasto("B");
        Miasto m2 = new Miasto("C");

        mapa.dodajMiasto(m0);
        mapa.dodajMiasto(m1);
        mapa.dodajMiasto(m2);

        mapa.dodajDroge(m0, m1, 10);
        mapa.dodajDroge(m1, m0, 10);
        mapa.dodajDroge(m1, m2, 10);

        assertArrayEquals(new Miasto[]{m0, m1, m2},
                mapa.podajNajkrotszaDroge(m0, m2));

    }
    @Test()
    public void najkrotszaDrogaPrzypadek3_Petla_plus_dwie_mozliwosci() throws MiastoNieIstniejeException, MiastoIstniejeException, DrogaNieIstniejeException {
        System.out.println("Test 3: powinno byc AB");
        Miasto m0 = new Miasto("A");
        Miasto m1 = new Miasto("B");
        Miasto m2 = new Miasto("C");

        mapa.dodajMiasto(m0);
        mapa.dodajMiasto(m1);
        mapa.dodajMiasto(m2);

        mapa.dodajDroge(m0, m1, 5);
        mapa.dodajDroge(m1, m0, 5);
        mapa.dodajDroge(m1, m2, 8);
        mapa.dodajDroge(m0, m2, 1);

        assertArrayEquals(new Miasto[]{m0, m2},
                mapa.podajNajkrotszaDroge(m0, m2));

    }
    @Test()
    public void najkrotszaDrogaPrzypadek4_DlugiLancuch() throws MiastoNieIstniejeException, MiastoIstniejeException, DrogaNieIstniejeException {
        System.out.println("Test 4: powinno byc ABCDEFGHI");
        Miasto m0 = new Miasto("A");
        Miasto m1 = new Miasto("B");
        Miasto m2 = new Miasto("C");
        Miasto m3 = new Miasto("D");
        Miasto m4 = new Miasto("E");
        Miasto m5 = new Miasto("F");
        Miasto m6 = new Miasto("G");
        Miasto m7 = new Miasto("H");
        Miasto m8 = new Miasto("I");

        mapa.dodajMiasto(m0);
        mapa.dodajMiasto(m1);
        mapa.dodajMiasto(m2);
        mapa.dodajMiasto(m3);
        mapa.dodajMiasto(m4);
        mapa.dodajMiasto(m5);
        mapa.dodajMiasto(m6);
        mapa.dodajMiasto(m7);
        mapa.dodajMiasto(m8);

        mapa.dodajDroge(m0, m1, 5);
        mapa.dodajDroge(m1, m2, 5);
        mapa.dodajDroge(m2, m3, 5);
        mapa.dodajDroge(m3, m4, 8);
        mapa.dodajDroge(m4, m5, 1);
        mapa.dodajDroge(m5, m6, 1);
        mapa.dodajDroge(m6, m7, 1);
        mapa.dodajDroge(m7, m8, 1);

        assertArrayEquals(new Miasto[]{m0,m1,m2,m3,m4,m5,m6,m7,m8},
                mapa.podajNajkrotszaDroge(m0, m8));
    }
    @Test()
    public void najkrotszaDrogaPrzypadek5_dwa_kwadraty_polaczone_jedna_nitka() throws MiastoNieIstniejeException, MiastoIstniejeException, DrogaNieIstniejeException {
        System.out.println("Test 3: powinno byc AB");
        Miasto m0 = new Miasto("A");
        Miasto m1 = new Miasto("B");
        Miasto m2 = new Miasto("C");
        Miasto m3 = new Miasto("D");
        Miasto m4 = new Miasto("E");
        Miasto m5 = new Miasto("F");
        Miasto m6 = new Miasto("G");
        Miasto m7 = new Miasto("H");
        

        mapa.dodajMiasto(m0);
        mapa.dodajMiasto(m1);
        mapa.dodajMiasto(m2);
        mapa.dodajMiasto(m3);
        mapa.dodajMiasto(m4);
        mapa.dodajMiasto(m5);
        mapa.dodajMiasto(m6);
        mapa.dodajMiasto(m7);
        

        mapa.dodajDroge(m0, m1, 5);
        mapa.dodajDroge(m0, m2, 5);
        mapa.dodajDroge(m1, m3, 8);
        mapa.dodajDroge(m2, m3, 1);
        mapa.dodajDroge(m3, m4, 1);
        mapa.dodajDroge(m4, m5, 5);
        mapa.dodajDroge(m4, m6, 5);
        mapa.dodajDroge(m6, m7, 8);
        mapa.dodajDroge(m5, m7, 1);

        assertArrayEquals(new Miasto[]{m0,m1,m2,m3,m4,m5,m7},
                mapa.podajNajkrotszaDroge(m0, m7));

    }
    
}   
