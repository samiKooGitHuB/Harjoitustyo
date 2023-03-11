package com.github.samikoogithub.harjoitustyo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Luokkaa perii Pizza-luokan ja laajentaa sita.
 * Vakioina taytteiden hinnat. Boolean-muuttuja tiedolle valkosipulista.
 * Double-muuttujana hinta.
 */
public class KokoPizza extends Pizza implements Laskutus, Serializable {
    final private double PIZZAPOHJA = 10.00;
    final private double HINTAANANAS = 0.30;
    final private double HINTATOMAATTI = 0.50;
    final private double HINTAKATKARAPU = 1.00;
    final private double HINTAKINKKU = 0.75;
    final private double HINTATONNIKALA = 1.00;
    final private double HINTAVALKOSIPULI = 0.00;
    final private double HINTAJAUHELIHA = 1.00;
    final private double HINTASIENET = 1.00;
    final private double HINTAAURAJUUSTO = 1.00;
    final private double HINTAKANA = 1.00;
    public double hinta = 0;


    /**
     * Tyhjä konstruktori
     *
     */
    public KokoPizza(){
        setPizzojenLkm();
    }

    /**
     * Konstruktori parametreilla, lisataan pizzojen lukumaaraa yhdella staattiseen muuuttujaan
     * @param pohja String Mika pohja valittu
     * @param nimi String Pizzalle annettu nimmi
     * @param lista ArrayList<String> Taytteet listana
     *
     */
    public KokoPizza(String pohja, String nimi, ArrayList<String> lista){
        setPohja(pohja);
        setNimi(nimi);
        super.lista = lista;
        setPizzojenLkm();

    };
    /**
     * Haetaan ananaksen hinta vakiosta
     *@return  double HINTAANANAS
     */
    public double getHintaAnanas() {
        return HINTAANANAS;
    }
    /**
     * Haetaan tomaatin hinta vakiosta
     *@return  double HINTATOMAATTI
     */
    public double getHintaTomaatti() {
        return HINTATOMAATTI;
    }
    /**
     * Haetaan katkaravun hinta vakiosta
     *@return  double HINTAKATKARAPU
     */
    public double getHintaKatkarapu() {
        return HINTAKATKARAPU;
    }

    /**
     * Haetaan kinkun hinta vakiosta
     *@return  double HINTAKINKKU
     */
    public double getHintaKinkku() {
        return HINTAKINKKU;
    }

    /**
     * Haetaan tonnikalan hinta vakiosta
     *@return  double HINTATONNIKALA
     */
    public double getHintaTonnikala() {
        return HINTATONNIKALA;
    }

    /**
     * Haetaan jauhelihan hinta vakiosta
     *@return  double HINTAJAUHELIHA
     */
    public double getHintaJauheliha() {
        return HINTAJAUHELIHA;
    }
    /**
     * Haetaan herkkusienien hinta vakiosta
     *@return  double HINTASIENET
     */
    public double getHintaSienet() {
        return HINTASIENET;
    }
    /**
     * Haetaan aurajuuston hinta vakiosta
     *@return  double HINTAAURAJUUSTO
     */
    public double getHintaAuraJuusto() {
        return HINTAAURAJUUSTO;
    }

    /**
     * Haetaan kanan hinta vakiosta
     *@return  double HINTAKANA
     */
    public double getHintaKana(){
        return HINTAKANA;
    }

    /**
     * Haetaan valkosipulin hinta vakiosta
     * @return double HINTAVALKOSIPULI
     */
    public double getHintaValkosipuli(){
        return HINTAVALKOSIPULI;
    }

    /**
     * Lisataan tayte lista-nimiseen listaan
     */
    public void lisaaTayte(String tayte){

        lista.add(tayte);
    }

    /**
     * Metodi taytteen poistamiseen listalta
     * @param tayte listalta poistettava tayte parametrina
     */
    public void poistaTayte(String tayte){
        lista.remove(tayte);
    }

    /**
     * Metodi palauttaa pizzan taytteet
     * @return String tayteListaus
     */
    public String getTaytteet(){
        String tayteListaus = "";
        for (int i = 0 ; i < lista.size(); i++){
            tayteListaus += lista.get(i);
            tayteListaus += "\n";

        }
        return tayteListaus;
        }
    /**
     * Uusi metodi, jonka rajapinta Tulostus vaatii.
     * Tulostetaan pizza-olion tiedot
     */
    public void tulostaKuitti(){
        System.out.println("---------------------------------");
        System.out.println("Pizzojen kokonaismäärä: "+ getPizzojenLkm());
        System.out.println("Pizzan nimi: " + getNimi());
        System.out.println("Pohja: " + getPohja());
        System.out.println("Valitut täytteet: ");
       for (int i = 0 ; i < lista.size(); i++){
           System.out.println(lista.get(i));

       }

        laskeHinta();
        System.out.println("Pizzan hinta on: " + String.format("%,.2f" , hinta) + "€");
        System.out.println("---------------------------------");

    }

    /**
     *  Metodi, jonka rajapinta Laskutus vaatii.
     *  Lasketaan pizzan hinta pohjan ja taytteiden mukaan
     *  ja talletetaan muuttujaan hinta.
     */
    public double laskeHinta(){
        hinta = 0;
        hinta += PIZZAPOHJA;

        if (lista.contains("Ananas")){
            hinta += HINTAANANAS;
        }
        if (lista.contains("Tomaatti")){
            hinta += HINTATOMAATTI;
        }
        if (lista.contains("Katkarapu")){
            hinta += HINTAKATKARAPU;
        }
        if (lista.contains("Kinkku")){
            hinta += HINTAKINKKU;
        }
        if (lista.contains("Tonnikala")){
            hinta += HINTATONNIKALA;
        }
        if (lista.contains("Valkosipuli")){
            hinta += HINTAVALKOSIPULI;  // Vaikka hinta tällä hetkellä 0.00, niin tällä varaudutaan
                                         // mahdolliseen hinnannousuun.
        }
        if (lista.contains("Jauheliha")){
            hinta += HINTAJAUHELIHA;
        }
        if (lista.contains("Herkkusieni")){
            hinta += HINTASIENET;
        }
        if (lista.contains("Kana")){
            hinta += HINTAKANA;
        }
        if (lista.contains("Aurajuusto")) {
            hinta += HINTAAURAJUUSTO;
        }
        return hinta;
    }

}
