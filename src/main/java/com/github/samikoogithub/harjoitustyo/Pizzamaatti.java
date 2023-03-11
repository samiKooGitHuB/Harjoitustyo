package com.github.samikoogithub.harjoitustyo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.*;


import static java.lang.System.exit;

public class Pizzamaatti extends Application{

    private final TextField tfPizzannimi = new TextField("Syötä nimi pizzalle");
    private final Button btLopeta = new Button("Lopeta");
    private final Button btLaskeHinta = new Button("Laske hinta");
    private final Button btTalletaTiedot = new Button("Talleta tiedot");
    private final Button btLueTiedot = new Button("Lue tiedot");
    private final Button btTyhjaa = new Button("Tyhjää tiedot");
    private final Label lbPohja = new Label("Valittu pohja:" );
    private final Label lbNimi = new Label("Nimi: ");
    private final Label lbKuitti = new Label("Kuitti:");
    private final Label lbValitseTaytteet = new Label("Valitse täytteet:");
    private final Label lbValitutTaytteet = new Label("Valitut täytteet:");
    private final Label lbKuvanTekija = new Label("Photo by Jack Moreh");
    private final KokoPizza luotuPizza = new KokoPizza();
    private KokoPizza[] pizzatKirjoitus;
    //private KokoPizza[] pizzatLuku;
    private Text pizzanNimi = new Text("");



    private final String tiedostoNimi = "pizzat.dat";
    private final Text ilmoitusteksti = new Text("");
    private final Text txtKuitti = new Text("");


    @Override
    public void start(Stage alkuIkkuna)  {


        // Määritellään paneeleja:
        StackPane taustaKuvalle = new StackPane();
        BorderPane taustaPaneeli = new BorderPane();
        taustaPaneeli.setPadding(new Insets(40,0,0,250));
        GridPane nappulaPaneeli = new GridPane();
        nappulaPaneeli.setPadding(new Insets(10,20,10,20));
        nappulaPaneeli.setHgap(5);
        btTyhjaa.setPrefWidth(100);
        btLueTiedot.setPrefWidth(100);
        btLopeta.setPrefWidth(100);
        btLaskeHinta.setPrefWidth(100);
        btTalletaTiedot.setPrefWidth(100);

        HBox keskiPaneeli = new HBox();
        keskiPaneeli.setPadding(new Insets(50,20,0,120
        ));
        VBox paneeliKuitille = new VBox();
        paneeliKuitille.setPadding(new Insets(50,80,0,0));
        paneeliKuitille.setOnMouseEntered(e ->{
            if (!txtKuitti.getText().equals("")){
            ilmoitusteksti.setText("Tässä kuitti. Kiitos käynnistä!");}

        });
        paneeliKuitille.setOnMouseExited(e ->{
            ilmoitusteksti.setText("");
        });

        VBox valitutTuotteet = new VBox(20);
        VBox paneeliCheckboxeille = new VBox(20);
        paneeliCheckboxeille.setPadding(new Insets(5, 50, 5, 5));


        //Määritellään tekstejä ja labeleita:
        Text txtPohja = new Text("");
        txtPohja.setFill(Color.WHITE);
        Text txtTaytteet = new Text("");
        txtTaytteet.setFill(Color.WHITE);
        Text txtHinta = new Text("");
        txtHinta.setFill(Color.WHITE);

        txtKuitti.setFill(Color.WHITE);
        lbKuvanTekija.setStyle("-fx-text-fill: white");
        lbKuvanTekija.setPadding(new Insets(5,5,5,0));
        Text maara = new Text("Pizzojen lukumäärä: "+ luotuPizza.getPizzojenLkm());
        lbValitseTaytteet.setStyle("-fx-text-fill: white");
        lbKuitti.setStyle("-fx-text-fill: white");
        lbValitutTaytteet.setStyle("-fx-text-fill: white");
        lbPohja.setStyle("-fx-text-fill: white");
        lbNimi.setStyle("-fx-text-fill: white");
        ilmoitusteksti.setFill(Color.WHITE);
        tfPizzannimi.setPrefWidth(180);

        valitutTuotteet.setPadding(new Insets(5,0,0,0));

        RadioButton rbVehna = new RadioButton("Vehnä");
        RadioButton rbRuis = new RadioButton("Ruis");
        RadioButton rbGluteeniton = new RadioButton("Gluteeniton");
        ToggleGroup ryhma = new ToggleGroup();
        rbVehna.setToggleGroup(ryhma);
        rbRuis.setToggleGroup(ryhma);
        rbGluteeniton.setToggleGroup(ryhma);


        CheckBox chkAnanas = new CheckBox("Ananas");
        chkAnanas.setStyle("-fx-text-fill: white");
        CheckBox chkTomaatti = new CheckBox("Tomaatti");
        chkTomaatti.setStyle("-fx-text-fill: white");
        CheckBox chkKatkarapu = new CheckBox("Katkarapu");
        chkKatkarapu.setStyle("-fx-text-fill: white");
        CheckBox chkKinkku = new CheckBox("Kinkku");
        chkKinkku.setStyle("-fx-text-fill: white");
        CheckBox chkTonnikala = new CheckBox("Tonnikala");
        chkTonnikala.setStyle("-fx-text-fill: white");
        CheckBox chkValkoSipuli = new CheckBox("Valkosipuli");
        chkValkoSipuli.setStyle("-fx-text-fill: white");
        CheckBox chkJauheliha = new CheckBox("Jauheliha");
        chkJauheliha.setStyle("-fx-text-fill: white");
        CheckBox chkSienet = new CheckBox("Herkkusienet");
        chkSienet.setStyle("-fx-text-fill: white");
        CheckBox chkAuraJuusto = new CheckBox("Aurajuusto");
        chkAuraJuusto.setStyle("-fx-text-fill: white");
        CheckBox chkKana = new CheckBox("Kana");
        chkKana.setStyle("-fx-text-fill: white");



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
            String uusiNimi = tfPizzannimi.getText();
            luotuPizza.setNimi(uusiNimi);
            pizzanNimi.setText(luotuPizza.getNimi());
            pizzanNimi.setFill(Color.WHITE);

        });

        //Määritellään nappuloille toimintoja

        btLopeta.setOnAction(e -> exit(0));
        btTalletaTiedot.setOnAction(e -> {
            luotuPizza.setNimi(tfPizzannimi.getText());
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
                    txtTaytteet.setText("");
                    txtHinta.setText("");
                    tfPizzannimi.setText("Syötä nimi pizzalle");
                    txtKuitti.setText("");


                }
        );
        btLueTiedot.setOnAction(e -> lueTiedosto());
        btLaskeHinta.setOnAction(e -> {
            luotuPizza.lista.clear();
            txtPohja.setText(luotuPizza.getPohja());
            ilmoitusteksti.setText("");

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
            txtTaytteet.setText("");
            txtTaytteet.setText(luotuPizza.getTaytteet());

            txtHinta.setText("Hinta: " + luotuPizza.laskeHinta());
        });

        // Paneelien täyttäminen eri elementeillä
        valitutTuotteet.getChildren().addAll(tfPizzannimi,
                lbPohja,txtPohja,lbNimi,pizzanNimi,lbValitutTaytteet,txtTaytteet,txtHinta,ilmoitusteksti);

        paneeliCheckboxeille.getChildren().addAll(cbo, lbValitseTaytteet, chkAnanas,chkTomaatti, chkKatkarapu, chkKinkku,
                chkTonnikala,chkJauheliha, chkSienet,chkAuraJuusto,chkKana,chkValkoSipuli);
        paneeliKuitille.getChildren().add(txtKuitti);


        keskiPaneeli.getChildren().addAll(paneeliCheckboxeille, valitutTuotteet);
        nappulaPaneeli.add(lbKuvanTekija,0,1);
        nappulaPaneeli.add(btLaskeHinta,1,0);
        nappulaPaneeli.add(btTyhjaa,1,1);
        nappulaPaneeli.add(btTalletaTiedot, 2,0);
        nappulaPaneeli.add(btLueTiedot,2,1);

        nappulaPaneeli.add(btLopeta,3,1);


        taustaPaneeli.setBottom(nappulaPaneeli);
        taustaPaneeli.setCenter(keskiPaneeli);
        taustaPaneeli.setRight(paneeliKuitille);
        taustaKuvalle.getChildren().addAll(kuvanaytto1, taustaPaneeli);
        // sijoitetaan paneeli kehykseen
        Scene kehys = new Scene(taustaKuvalle,1000   ,600);
        alkuIkkuna.setResizable(false);     // ikkunan koko ei saa muuttaa
        alkuIkkuna.setTitle("Tervetuloa herkuttelemaan Pizzamaatilla!"); // otsikko ikkunalle
        alkuIkkuna.setScene(kehys); // sijoitetaan kehys ikkunaan
        alkuIkkuna.show(); // n�ytet��n ikkuna
    }

    public static void main(String[] args) {
        launch(args);
    }


    private void talletaTiedosto(){

         pizzatKirjoitus= new KokoPizza[]{luotuPizza};

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
        KokoPizza[] pizzatLuku = null;
        try {
            pizzatLuku = new KokoPizza[pizzatKirjoitus.length];

            lueTiedosto = new ObjectInputStream(new FileInputStream(tiedostoNimi));
            pizzatLuku = ((KokoPizza[]) lueTiedosto.readObject());
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
        for (KokoPizza tiedostoPizza : pizzatLuku){
            String kuitinTeksti = ("Kuitti:" + "\n------------------------------" +
                    "\nPizzan nimi: " + tiedostoPizza.getNimi() +
                    "\nPohja: " + tiedostoPizza.getPohja() +
                    "\nTäytteet:\n" +tiedostoPizza.getTaytteet() +
                    "\nHinta: " + tiedostoPizza.laskeHinta() +
                    "\n------------------------------");
            txtKuitti.setText(kuitinTeksti);
        }

    }


}
