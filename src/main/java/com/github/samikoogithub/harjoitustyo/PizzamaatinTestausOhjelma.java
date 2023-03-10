package com.github.samikoogithub.harjoitustyo;

import java.io.*;
import java.util.ArrayList;

public class PizzamaatinTestausOhjelma {


    public static void main(String[] args) throws Exception{
        /*
        KokoPizza torttu1 = new KokoPizza();
        torttu1.setPohja("Ruis");
        torttu1.setNimi("TeStIpIzZa");
        torttu1.lisaaTayte("Jauheliha");
        torttu1.lisaaTayte("Kinkku");
        torttu1.lisaaTayte("Aurajuusto");
        torttu1.setTuleekoValkosipuli(true);*/
        ArrayList<String> lista2 = new ArrayList<String>();
        lista2.add("Katkarapu");
        lista2.add("Ananas");
        ArrayList<String> lista3 = new ArrayList<String>();
        lista3.add("Katkarapu");
        lista3.add("Ananas");
        lista3.add("Tomaatti");
        lista3.add("Kinkku");
        lista3.add("Tonnikala");
        lista3.add("Valkosipuli");
        lista3.add("Jauheliha");
        lista3.add("Herkkusieni");
        lista3.add("Aurajuusto");

        KokoPizza torttu1 = new KokoPizza("Gluteeniton", "Omanimi", lista2,true);
        KokoPizza torttu2 = new KokoPizza("Ruis","TehtiinPizza", lista3, true);



        torttu1.tulostaKuitti();
        torttu2.tulostaKuitti();
        //torttu1.poistaTayte("Jauheliha");
        //torttu1.tulostaKuitti();

        // tässä kirjoitus poikkeustenkäsittelyllä

        try {
            FileOutputStream fstream = new FileOutputStream("pizzat.dat");
            ObjectOutputStream outputfile = new ObjectOutputStream(fstream);
            outputfile.writeObject(torttu1);
            outputfile.writeObject(torttu2);
            outputfile.close();
            System.out.println("Tiedot kirjoitettiin tiedostoon pizzat.dat");
            } catch (FileNotFoundException e) {
            System.err.println("Tiedostoa ei ole olemassa");
            } catch (IOException e) {
            System.err.println("Tiedosto löytyi, mutta jotain outoa tapahtui...");
            }


        boolean tiedostonLoppu = false;

        FileInputStream fstream2 = new FileInputStream("pizzat.dat");
        ObjectInputStream inputFile = new ObjectInputStream(fstream2);
        System.out.println("Tietoja luetaan parhaillaan.");
        KokoPizza luettuOlio;
        while (!tiedostonLoppu) {
            try {
                luettuOlio = (KokoPizza) inputFile.readObject();
                luettuOlio.tulostaKuitti();
                //System.out.println("Luetut tiedot:");
                //System.out.println("Pizzan nimi: " + luettuOlio.getNimi());
                //System.out.println("Pohja: " + luettuOlio.getPohja());
                //System.out.println("Tuleeko valkosipuli: " + luettuOlio.getTuleekoValkosipuli());
                //// for (int i = 0 ; i < luettuOlio.lista.size(); i++){
                  //  System.out.println(luettuOlio.lista.get(i));
               // }
                System.out.println("Hyvää ruokahalua!!");

            } catch (EOFException e) {
                tiedostonLoppu = true;
            }
        }
        // tästä alkaa poikkkeusten käsittelyä

/*

        // Lukuhommeleita
        KokoPizza torttu11 = null, torttu22 = null;
        try(

            ObjectInputStream oliotiedosto2 = new ObjectInputStream((new FileInputStream("pizzat.dat")))){
            torttu11 = (KokoPizza) oliotiedosto2.readObject();
            //torttu22 = (KokoPizza) oliotiedosto2.readObject();
            oliotiedosto2.close();

        } catch (FileNotFoundException e) {
            System.err.println("Tiedostoa ei löytynyt");
            System.out.println("Virhe: " + e.getMessage());
        } catch (EOFException e) {
            System.err.println("Tiedoston loppu ylitetty.");
            System.out.println("Virhe:" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Tiedosto olemassa, mutta tapahtui virhe.");
            System.out.println("Virhe: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Virhe: " + e.getMessage());
            System.err.println("Serialisoitua luokkaa ei löytynyt.");
        }
        if (torttu11 != null) {
            System.out.println(torttu11);
        }
        if (torttu22 != null) {
            System.out.println(torttu22);
        }*/

    }
}
