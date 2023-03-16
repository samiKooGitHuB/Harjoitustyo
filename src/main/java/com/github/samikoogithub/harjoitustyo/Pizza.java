package com.github.samikoogithub.harjoitustyo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Luokka toteuttaa yksinkertaisen pizzan, jolla on
 * lukumaara, pohja, nimi ja taytteet listana.
 */
public class Pizza implements Serializable{
    private static int pizzojenLkm = 0;
    private String pohja;
    private String nimi;
    ArrayList<String> lista = new ArrayList<String>();


    /**
     * Lisaa pizzojen lukumaaraa staattiseen muuttujaan
     */
    public void setPizzojenLkm(){
        pizzojenLkm++;
    }

    /**
     * Palauttaa luotujen pizza-olioiden lukumaaran
     * @return static int
     */
    public static int getPizzojenLkm(){
        return pizzojenLkm;
    }

    /**
     * Asettaa pohjan paaraaka-aineen annetun parametrin mukaan
     * @param pohja String
     */
    public void setPohja(String pohja){
        this.pohja = pohja;
    }

    /**
     * Palauttaa pohjan paaraaka-aineen
     * @return String
     */
    public String getPohja(){
        return this.pohja;
    }

    /**
     * Asettaa pizzalle nimen parametrin mukaan
     * @param nimi String
     */
    public void setNimi(String nimi){
        this.nimi = nimi;
    }

    /**
     * Palauttaa pizzan nimen
     * @return String
     */
    public String getNimi(){
        return this.nimi;
    }

}
