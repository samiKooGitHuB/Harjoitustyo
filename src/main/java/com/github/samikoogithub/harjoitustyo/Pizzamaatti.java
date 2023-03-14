package com.github.samikoogithub.harjoitustyo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import static java.lang.System.exit;

public class Pizzamaatti extends Application{

    private final TextField tfPizzannimi = new TextField("Syötä nimi pizzalle");
    private final Button btLopeta = new Button("Lopeta");
    private final Button btLaskeHinta = new Button("Laske hinta");
    private final Button btTalletaTiedot = new Button("Talleta tiedot");
    private final Button btLueTiedot = new Button("Lue tiedot");
    private final Button btTyhjaa = new Button("Tyhjää tiedot");
    private final Button btUusiPizza = new Button("Uusi pizza");
    private final Button btTulosta = new Button("Tulosta");
    private final Label lbPohja = new Label("Valittu pohja:" );
    private final Label lbNimi = new Label("Nimi: ");
    private final Label lbKuitti = new Label("Kuitti:");
    private final Label lbValitseTaytteet = new Label("Valitse täytteet:");
    private final Label lbValitutTaytteet = new Label("Valitut täytteet:");
    private final Label lbKuvanTekija = new Label("Photo by Jack Moreh");
    private KokoPizza luotuPizza;
    private final Text txtOtsikko = new Text("Pizzamaatti");
    private CheckBox chkAnanas = new CheckBox("Ananas");
    private CheckBox chkTomaatti = new CheckBox("Tomaatti");
    private CheckBox chkKatkarapu = new CheckBox("Katkarapu");
    private CheckBox chkKinkku = new CheckBox("Kinkku");
    private CheckBox chkTonnikala = new CheckBox("Tonnikala");
    private CheckBox chkValkoSipuli = new CheckBox("Valkosipuli");
    private CheckBox chkJauheliha = new CheckBox("Jauheliha");
    private CheckBox chkSienet = new CheckBox("Herkkusienet");
    private CheckBox chkAuraJuusto = new CheckBox("Aurajuusto");
    private CheckBox chkKana = new CheckBox("Kana");
    private CheckBox chkKotipaketti = new CheckBox("Kotipakettiin?");
    private KokoPizza[] pizzatKirjoitus;
    //private KokoPizza[] pizzatLuku;
    private Text pizzanNimi = new Text("");
    private final String tiedostoNimi = "pizzat.dat";
    private Text ilmoitusteksti = new Text("");
    private final Text txtKuitti = new Text("");


    @Override
    public void start(Stage alkuIkkuna)  {

        // Luodaan ensimmäinen pizza
        luotuPizza = new KokoPizza();

        // Luodaan tiedostoon kirjoitusta varten taulukko
        pizzatKirjoitus= new KokoPizza[2];

        // Määritellään paneeleja:
        StackPane taustaKuvalle = new StackPane();
        BorderPane taustaPaneeli = new BorderPane();
        taustaPaneeli.setPadding(new Insets(20,40,0,250));
        taustaPaneeli.setAlignment(txtOtsikko, Pos.TOP_CENTER);

        GridPane nappulaPaneeli = new GridPane();
        nappulaPaneeli.setAlignment(Pos.CENTER_LEFT);
        nappulaPaneeli.setPadding(new Insets(0,20,10,3));
        nappulaPaneeli.setHgap(5);

        // Määritellään nappuloiden leveys
        btTyhjaa.setPrefWidth(90);
        btLueTiedot.setPrefWidth(90);
        btLopeta.setPrefWidth(90);
        btLaskeHinta.setPrefWidth(90);
        btTalletaTiedot.setPrefWidth(90);
        btUusiPizza.setPrefWidth(90);
        btTulosta.setPrefWidth(90);

        // Keskimmäisen paneelin määrityksiä
        HBox keskiPaneeli = new HBox();
        keskiPaneeli.setPadding(new Insets(20,20,0,120
        ));
        VBox paneeliKuitille = new VBox();
        paneeliKuitille.setPadding(new Insets(25,40,0,0));

        // Näytetään teksti, jos kuitti on näkyvillä, muuten ei
        // tehdä mitään
        paneeliKuitille.setOnMouseEntered(e ->{
            if (!txtKuitti.getText().equals("")){
            ilmoitusteksti.setText("Tässä kuitti. Kiitos käynnistä!");}

        });
        paneeliKuitille.setOnMouseExited(e ->{
            ilmoitusteksti.setText("");
        });

        // Määritellään paneeli ComboBoxille ja CheckBoxeille
        VBox paneeliCheckboxeille = new VBox(20);
        paneeliCheckboxeille.setPadding(new Insets(5, 50, 5, 5));

        // Määritetään paneeli valituille tuotteille
        VBox valitutTuotteet = new VBox(15);
        valitutTuotteet.setPadding(new Insets(5,0,0,0));




        //Määritellään tekstejä ja labeleita:
        Text txtPohja = new Text("");
        txtPohja.setFill(Color.WHITE);

        Text txtTaytteet = new Text("");
        txtTaytteet.setFill(Color.WHITE);

        Text txtHinta = new Text("");
        txtHinta.setFill(Color.WHITE);

        Text txtKuitinPvm = new Text("");
        txtKuitinPvm.setFill(Color.WHITE);
        txtKuitinPvm.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        txtKuitti.setFill(Color.WHITE);
        txtOtsikko.setFont(Font.font ("Lucida Calligraphy", 40));
        txtOtsikko.setFill(Color.WHITE);
        lbKuvanTekija.setStyle("-fx-text-fill: white");
        lbKuvanTekija.setPadding(new Insets(5,5,5,0));
        Text maara = new Text("Pizzojen lukumäärä: "+ luotuPizza.getPizzojenLkm());
        maara.setFill(Color.WHITE);
        lbValitseTaytteet.setStyle("-fx-text-fill: white");
        lbKuitti.setStyle("-fx-text-fill: white");
        lbValitutTaytteet.setStyle("-fx-text-fill: white");
        lbPohja.setStyle("-fx-text-fill: white");
        lbNimi.setStyle("-fx-text-fill: white");
        ilmoitusteksti.setFill(Color.WHITE);
        tfPizzannimi.setPrefWidth(180);
        chkAnanas.setStyle("-fx-text-fill: white");
        chkTomaatti.setStyle("-fx-text-fill: white");
        chkKatkarapu.setStyle("-fx-text-fill: white");
        chkKinkku.setStyle("-fx-text-fill: white");
        chkTonnikala.setStyle("-fx-text-fill: white");
        chkValkoSipuli.setStyle("-fx-text-fill: white");
        chkJauheliha.setStyle("-fx-text-fill: white");
        chkSienet.setStyle("-fx-text-fill: white");
        chkAuraJuusto.setStyle("-fx-text-fill: white");
        chkKana.setStyle("-fx-text-fill: white");
        chkKotipaketti.setStyle("-fx-text-fill: white");




        RadioButton rbVehna = new RadioButton("Vehnä");
        RadioButton rbRuis = new RadioButton("Ruis");
        RadioButton rbGluteeniton = new RadioButton("Gluteeniton");
        ToggleGroup ryhma = new ToggleGroup();
        rbVehna.setToggleGroup(ryhma);
        rbRuis.setToggleGroup(ryhma);
        rbGluteeniton.setToggleGroup(ryhma);


        StackPane kuvaPaneeli = new StackPane();
        Image kuva1 = new Image("file:pizzaBackground.jpg"); //haetaan kuva
        ImageView kuvanaytto1 = new ImageView(kuva1);
        kuvanaytto1.setFitWidth(1000);
        kuvanaytto1.setFitHeight(600);


        //paneeliComboboksille.setStyle("-fx-border-color: green");
        ComboBox<String> cbo = new ComboBox<>();
        cbo.getItems().addAll("Vehnä","Ruis","Gluteeniton");
        cbo.setValue("Valitse pohja"); // alkuteksti

        cbo.setOnAction(e ->{
            if (cbo.getValue().equals("Vehnä")){
                luotuPizza.setPohja("Vehnä");
                txtPohja.setText("Vehnä");
            }
            if (cbo.getValue().equals("Ruis")){
                luotuPizza.setPohja("Ruis");
                txtPohja.setText("Ruis");

            }
            if (cbo.getValue().equals("Gluteeniton")){
                luotuPizza.setPohja("Gluteeniton");
                txtPohja.setText("Gluteeniton");

            }

        });
        //Luetaan pizzan nimi tekstikentästä
        //ja asetetaan se olion nimeksi.
        tfPizzannimi.setOnAction(e -> {
            if (tfPizzannimi.getText().length() > 10){
                tfPizzannimi.setText("Liian pitkä, max 10 merkkiä");
            }
            else {
            String uusiNimi = tfPizzannimi.getText();
            luotuPizza.setNimi(uusiNimi);
            pizzanNimi.setText(luotuPizza.getNimi());
            pizzanNimi.setFill(Color.WHITE);
            }

        });
        tfPizzannimi.setOnMouseClicked(e ->{
            tfPizzannimi.clear();
        });

        //Määritellään nappuloille toimintoja

        btLopeta.setOnAction(e -> exit(0));
        btTalletaTiedot.setOnAction(e -> {
            luotuPizza.setNimi(tfPizzannimi.getText());
            luotuPizza.lista.clear();
            lisaaTaytteet();
            talletaTiedosto();
        });
        btTyhjaa.setOnAction(e -> {

                    cbo.setValue("Valitse pohja");
                    txtPohja.setText("");
                    chkAnanas.setSelected(false);
                    chkTomaatti.setSelected(false);
                    chkKatkarapu.setSelected(false);
                    chkKinkku.setSelected(false);
                    chkTonnikala.setSelected(false);
                    chkValkoSipuli.setSelected(false);
                    chkJauheliha.setSelected(false);
                    chkSienet.setSelected(false);
                    chkAuraJuusto.setSelected(false);
                    chkKana.setSelected(false);
                    chkKotipaketti.setSelected(false);
                    txtTaytteet.setText("");
                    txtHinta.setText("");
                    tfPizzannimi.setText("Syötä nimi pizzalle");
                    pizzanNimi.setText("");
                    txtKuitti.setText("");
                    txtKuitinPvm.setText("");



                }
        );
        btLueTiedot.setOnAction(e -> {
            lueTiedosto();
            });
        btLaskeHinta.setOnAction(e -> {
            luotuPizza.lista.clear();
            txtPohja.setText(luotuPizza.getPohja());
            ilmoitusteksti.setText("");
            lisaaTaytteet();
            txtTaytteet.setText("");
            txtTaytteet.setText(luotuPizza.getTaytteet());

            txtHinta.setText("Hinta: " + luotuPizza.laskeHinta());
        });
        btUusiPizza.setOnAction(e -> {
            if (KokoPizza.getPizzojenLkm() < 2){
                luotuPizza = new KokoPizza();
                cbo.setValue("Valitse pohja");
                txtPohja.setText("");
                chkAnanas.setSelected(false);
                chkTomaatti.setSelected(false);
                chkKatkarapu.setSelected(false);
                chkKinkku.setSelected(false);
                chkTonnikala.setSelected(false);
                chkValkoSipuli.setSelected(false);
                chkJauheliha.setSelected(false);
                chkSienet.setSelected(false);
                chkAuraJuusto.setSelected(false);
                chkKana.setSelected(false);
                txtTaytteet.setText("");
                txtHinta.setText("");
                tfPizzannimi.setText("Syötä nimi pizzalle");
                txtKuitti.setText("");
                maara.setText("Pizzojen lukumäärä: "+ luotuPizza.getPizzojenLkm());
            }
            else {
                btUusiPizza.setDisable(true);
                ilmoitusteksti.setText("Voit luoda max. 2 pizzaa!");
            }


        });

        btTulosta.setOnAction(e -> {
            luotuPizza.tulostaKuitti();
        });

        // Paneelien täyttäminen eri elementeillä
        valitutTuotteet.getChildren().addAll(tfPizzannimi,chkKotipaketti,
                lbPohja,txtPohja,lbNimi,pizzanNimi,lbValitutTaytteet,txtTaytteet,txtHinta,maara,ilmoitusteksti);

        paneeliCheckboxeille.getChildren().addAll(cbo, lbValitseTaytteet, chkAnanas,chkTomaatti, chkKatkarapu, chkKinkku,
                chkTonnikala,chkJauheliha, chkSienet,chkAuraJuusto,chkKana,chkValkoSipuli);
        paneeliKuitille.getChildren().addAll(txtKuitti);


        keskiPaneeli.getChildren().addAll(paneeliCheckboxeille, valitutTuotteet);
        nappulaPaneeli.add(lbKuvanTekija,0,1);
        nappulaPaneeli.add(btLaskeHinta,1,0);
        nappulaPaneeli.add(btTyhjaa,1,1);
        nappulaPaneeli.add(btTalletaTiedot, 3,0);
        nappulaPaneeli.add(btLueTiedot,2,1);
        nappulaPaneeli.add(btUusiPizza,2,0);
        nappulaPaneeli.add(btLopeta,3,1);


        // Asetetaan taustapaneeliin muuta panelit/teksti/kuva
        taustaPaneeli.setTop(txtOtsikko);
        taustaPaneeli.setBottom(nappulaPaneeli);
        taustaPaneeli.setCenter(keskiPaneeli);
        taustaPaneeli.setRight(paneeliKuitille);

        // Asetetaan ihan alimmaiseksi kuva ja sen päälle taustapaneli, jossa
        // muut panelit
        taustaKuvalle.getChildren().addAll(kuvanaytto1, taustaPaneeli);

        // Sijoitetaan paneeli kehykseen
        Scene kehys = new Scene(taustaKuvalle,1000   ,600);
        alkuIkkuna.setResizable(false);     // Ikkunan koko ei saa muuttaa
        alkuIkkuna.setTitle("Tervetuloa herkuttelemaan Pizzamaatilla!"); // Otsikko ikkunalle
        alkuIkkuna.setScene(kehys); // Sijoitetaan kehys ikkunaan
        alkuIkkuna.show(); // Näytetään ikkuna


    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metodi lisaa valitut taytteet olion taytteet-listalle
     */
    private void lisaaTaytteet(){


        if (chkAnanas.isSelected()) {
            luotuPizza.lista.add("Ananas");
        }

        if (chkTomaatti.isSelected()) {
            luotuPizza.lista.add("Tomaatti");
        }

        if (chkKatkarapu.isSelected()) {
            luotuPizza.lista.add("Katkarapu");
        }

        if (chkKinkku.isSelected()) {
            luotuPizza.lista.add("Kinkku");
        }

        if (chkTonnikala.isSelected()) {
            luotuPizza.lista.add("Tonnikala");
        }

        if (chkValkoSipuli.isSelected()) {
            luotuPizza.lista.add("Valkosipuli");
        }

        if (chkJauheliha.isSelected()) {
            luotuPizza.lista.add("Jauheliha");
        }

        if (chkSienet.isSelected()) {
            luotuPizza.lista.add("Herkkusieni");
        }

        if (chkKana.isSelected()){
            luotuPizza.lista.add("Kana");
        }

        if (chkAuraJuusto.isSelected()) {
            luotuPizza.lista.add("Aurajuusto");
        }

        if (chkKotipaketti.isSelected()){
            luotuPizza.setKotiPaketti(true);
        }
    }

    private void talletaTiedosto(){


        if (luotuPizza.getPizzojenLkm() == 1 ) {
            pizzatKirjoitus[0] = luotuPizza;

        }
        else if (luotuPizza.getPizzojenLkm() == 2) {
            //System.out.println("==2: " + KokoPizza.getPizzojenLkm());
            pizzatKirjoitus[1] = luotuPizza;

        }

         ObjectOutputStream kirjoitusTiedosto = null;

        try {
            kirjoitusTiedosto = new ObjectOutputStream(new FileOutputStream(tiedostoNimi));
            kirjoitusTiedosto.writeObject(pizzatKirjoitus);
            kirjoitusTiedosto.close();
            ilmoitusteksti.setText("Tiedot tiedostossa: pizzat.dat");
        } catch (FileNotFoundException e) {
            System.err.println("Tiedostoa ei ole olemassa");
        } catch (IOException e) {
            System.err.println("Tiedosto löytyi, mutta jotain outoa tapahtui...");
        }
        finally{
            try{
                if (kirjoitusTiedosto != null) kirjoitusTiedosto.close();
            }
            catch (IOException e){
                e.printStackTrace();
                System.out.println("Virhe.");
            }
        }

    }

    /**
     * Metodi lukee tallennetut tiedot tiedostosta
     */
    private void lueTiedosto() {
        ObjectInputStream lueTiedosto = null;
        KokoPizza[] pizzatLuku = new KokoPizza[ KokoPizza.getPizzojenLkm()];
        try {

            lueTiedosto = new ObjectInputStream(new FileInputStream(tiedostoNimi));
            pizzatLuku = (KokoPizza[]) lueTiedosto.readObject();

        } catch (IOException e) {
            System.err.println("Virhe. Tiedosto on olemassa, mutta tapahtui virhe.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Virhe. Ei löytynyt sarjallistettua luokkaa.");
            e.printStackTrace();
        }
        finally{
            try{
                if (pizzatLuku != null) lueTiedosto.close();
                ilmoitusteksti.setText("Tiedot luettu.");
            }
            catch (IOException e){
                System.out.println("Virhe.");
                e.printStackTrace();
            }

        }
        String tarkistaNimi = new String("");
        String tarkistaPohja = new String("");
        String takeAway = new String("");
        String kuitinTeksti = new String("Kuitti:\n");
        String hinta = new String("");
        for (int i = 0 ; i < KokoPizza.getPizzojenLkm() ; i++){

            tarkistaNimi = pizzatLuku[i].getNimi();

            if (tarkistaNimi.equals("Syötä nimi pizzalle"))
            {
                tarkistaNimi = "Ei syötettyä arvoa";
            }
            tarkistaPohja = pizzatLuku[i].getPohja();
            if (tarkistaPohja == null){
                tarkistaPohja = "Ei valintaa";
            }

            if (luotuPizza.getKotiPaketti())
                takeAway = "Kyllä";
            else takeAway = "Ei";
            pizzatLuku[i].tulostaKuitti();
            kuitinTeksti +=("------------------------------" +
                    "\nPizzan nimi: " + tarkistaNimi +
                    "\nKotipakettiin: " + takeAway +
                    "\nPohja: " + tarkistaPohja +
                    "\nTäytteet:\n" + pizzatLuku[i].getTaytteet()+
                    "Hinta: " + pizzatLuku[i].laskeHinta() + " €\n");

            txtKuitti.setText(kuitinTeksti);
        }

    }


}
