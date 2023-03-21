package com.github.samikoogithub.harjoitustyo;

/**
 * Rajapinta Laskutus, jossa metodi "kuitin" tulostukseen (voidaan hyodyntaa esim.
 * ravintolan keittion tulostimelle tulostamissessa) seka metodi hinnan laskemiseksi.
 * @author samikoti
 * @version 2.0
 */
public interface Laskutus {
    /**
     * rajapintametodi, selitetty tarkemmin KokoPizza-luokan yhteydessa
     */
    void tulostaKuitti();

    /**
     * rajapintametodi, selitetty tarkemmin KokoPizza-luokan yhteydessa
     * @return  double yhteenlaskettu hinta
     */
    double laskeHinta();
}
