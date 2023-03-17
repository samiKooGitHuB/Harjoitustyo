package com.github.samikoogithub.harjoitustyo;

/**
 * Rajapinta Laskutus, jossa metodi "kuitin" tulostukseen (voidaan hyodyntaa esim.
 * ravintolan keittion tulostimelle tulostamissessa=) sekä metodi hinnan laskemiseksi.
 */
public interface Laskutus {
    /**
     * rajapintametodi, selitetty tarkemmin KokoPizza-luokan yhteydessa
     */
    void tulostaKuitti();

    /**
     * rajapintametodi, selitetty tarkemmin KokoPizza-luokan yhteydessa
     * @return
     */
    double laskeHinta();
}
