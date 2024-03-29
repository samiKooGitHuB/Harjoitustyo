package com.github.samikoogithub.harjoitustyo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Luokkaa perii Pizza-luokan ja laajentaa sita.
 * Vakioina taytteiden hinnat. Boolean-muuttuja tiedolle valkosipulista.
 * Double-muuttujana hinta.
 * @author samikoti
 * @version 2.0
 */
public class KokoPizza extends Pizza implements Laskutus, Serializable {
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double PIZZAPOHJA = 10.00;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTAANANAS = 0.30;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTATOMAATTI = 0.50;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTAKATKARAPU = 1.00;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTAKINKKU = 0.75;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTATONNIKALA = 1.00;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTAVALKOSIPULI = 0.00;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTAJAUHELIHA = 1.00;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTASIENET = 1.00;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTAAURAJUUSTO = 1.00;
    /**
     * Asetetaan vakio hintamuutoksia helpottamaan
     */
    final private double HINTAKANA = 1.00;
    /**
     * Merkkijono-tyyppinen muuttuja. Kaytetaan tulostuksessa, kun tarkistetaan, etta onko
     * tieto tyhja ja muokataan tulostetta sen mukaan.
     */
    private String tarkistaNimi = ("");
    /**
     * Merkkijono-tyyppinen muuttuja. Kaytetaan tulostuksessa, kun tarkistetaan, etta onko
     * tieto tyhja ja muokataan tulostetta sen mukaan.
     */
    private String tarkistaPohja = ("");
    /**
     * Merkkijono-tyyppinen muuttuja. Kaytetaan tulostuksessa, kun tarkistetaan, etta onko
     * tieto tyhja ja muokataan tulostetta sen mukaan.
     */
    private String tarkistaTaytteet = ("");
    /**
     * Boolean-tyyppinen muuttuja sen mukaan, etta halutaanko pizza kotipakettiin
     */
    private boolean kotiPaketti;
    /**
     * Double-tyyppinen muuttuja hinnan laskemiseen
     */
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
     * @param lista ArrayList Taytteet listana
     *
     */
    public KokoPizza(String pohja, String nimi, ArrayList<String> lista){
        setPohja(pohja);
        setNimi(nimi);
        super.tayteLista = lista;
        setPizzojenLkm();

    }
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
     * Metodi taytteiden lisaamiseksi olion tayteListalle
     * @param tayte String lisattava tayte
     */
    public void lisaaTayte(String tayte){ tayteLista.add(tayte); }

    /**
     * Metodi asettaa kotiPaketin arvon
     * @param arvo boolean
     */
    public void setKotiPaketti(boolean arvo){
        kotiPaketti = arvo;
    }

    /**
     * Metodi palauttaa kotiPaketin boolean-arvon
     * @return boolean
     */
    public boolean getKotiPaketti(){
        return kotiPaketti;
    }
    /**
     * Metodi palauttaa pizzan taytteet
     * @return String tayteListaus
     */
    public String getTaytteet(){
        String tayteListaus = "";
        for (String s : tayteLista) {
            tayteListaus += s;
            tayteListaus += "\n";
        }
        return tayteListaus;
        }
    /**
     * Uusi metodi, jonka rajapinta Tulostus vaatii.
     * Tulostetaan pizza-olion tiedot
     */
    public void tulostaKuitti(){
        String takeAway;
        tarkistaPohja = getPohja();
       if (tarkistaPohja == null){
          tarkistaPohja = "Ei valintaa";
       }
        tarkistaNimi = getNimi();

       if (tayteLista.isEmpty()){
            tarkistaTaytteet = "Ei täytteitä";
       }
       else tarkistaTaytteet = getTaytteet();

       if (getKotiPaketti()){
            takeAway = "Kyllä";
       }
       else takeAway = "Ei";

       System.out.println("---------------------------------");
       System.out.println("Pizzan nimi: " + tarkistaNimi);
       System.out.println("Kotipakettiin: " + takeAway);
       System.out.println("Pohja: " + tarkistaPohja);
       System.out.println("Valitut täytteet:\n" + tarkistaTaytteet);
       laskeHinta();
       System.out.println("Pizzan hinta on: " + String.format("%,.2f" , hinta) + " €");
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

        if (tayteLista.contains("Ananas")) hinta += getHintaAnanas();
        if (tayteLista.contains("Tomaatti")) hinta += getHintaTomaatti();
        if (tayteLista.contains("Katkarapu")) hinta += getHintaKatkarapu();
        if (tayteLista.contains("Kinkku")) hinta += getHintaKinkku();
        if (tayteLista.contains("Tonnikala")) hinta += getHintaTonnikala();
        if (tayteLista.contains("Valkosipuli")) hinta += getHintaValkosipuli();  // Vaikka hinta tällä
        // hetkellä on 0.00, niin varaudutaan mahdolliseen hinnannousuun.
        if (tayteLista.contains("Jauheliha")) hinta += getHintaJauheliha();
        if (tayteLista.contains("Herkkusieni")) hinta += getHintaSienet();
        if (tayteLista.contains("Kana")) hinta += getHintaKana();
        if (tayteLista.contains("Aurajuusto")) hinta += getHintaAuraJuusto();
        return hinta;
    }

}

