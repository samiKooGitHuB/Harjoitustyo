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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.System.exit;
import static javafx.scene.text.FontWeight.EXTRA_BOLD;

/**
 * Pizza- ja KokoPizza-luokkia kayttava kayttoliittyma, jolla voidaan
 * luoda pizzoja valinnaisen pohjan ja taytteiden kera, antaa pizzalle
 * nimi ja tallentaa seka lukea tietoja tiedostosta.
 * @ samikoti
 * @version 1.0
 */
public class Pizzamaatti extends Application{

    /**
     * Painike ohjelman lopettamiselle
     */
    private final Button btLopeta = new Button("Lopeta");
    /**
     * Painike hinnan laskemiselle
     */
    private final Button btLaskeHinta = new Button("Laske hinta");
    /**
     * Painike tietojen tallentamiselle tiedostoon
     */
    private final Button btTalletaTiedot = new Button("Talleta & luo uusi pizza");
    /**
     * Painike tietojen lukemiselle tiedostosta
     */
    private final Button btLueTiedot = new Button("Lue  & tulosta tiedot");
    /**
     * Painike naytolla olevien tietojen tyhjaamiseen
     */
    private final Button btTyhjaa = new Button("Tyhjää tiedot");
    //private final Button btTulosta = new Button("Tulosta");
    /**
     * Otsikko valitulle pizzapohjalle
     */
    private final Label lbPohja = new Label("Valittu pohja:" );
    /**
     * Otsikko annetulle pizzan nimelle
     */
    private final Label lbNimi = new Label("Nimi: ");
    //private final Label lbKuitti = new Label("Kuitti:");
    /**
     * Otsikko taytteiden valinnalle
     */
    private final Label lbValitseTaytteet = new Label("Valitse täytteet:");
    /**
     * Otsikko valituille taytteille
     */
    private final Label lbValitutTaytteet = new Label("Valitut täytteet:");
    /**
     * Teksti kuvan tekijasta
     */
    private final Label lbKuvanTekija = new Label("Photo by Jack Moreh");
    /**
     * Kokopizza-tyyppinen muuttuja luotuPizza
     */
    private KokoPizza luotuPizza;
    /**
     * Pizzan nimen tekstikentta
     */
    private final TextField tfPizzannimi = new TextField("Syötä nimi pizzalle");
    /**
     * Otsikko ikkunan ylaosaan
     */
    private final Text txtOtsikko = new Text("Pizzamaatti");
    /**
     * CheckBox taytteelle ananas
     */
    private final CheckBox chkAnanas = new CheckBox("Ananas");
    /**
     * CheckBox taytteelle tomaatti
     */
    private final CheckBox chkTomaatti = new CheckBox("Tomaatti");
    /**
     * CheckBox taytteelle katkarapu
     */
    private final CheckBox chkKatkarapu = new CheckBox("Katkarapu");
    /**
     * CheckBox taytteelle kinkku
     */
    private final CheckBox chkKinkku = new CheckBox("Kinkku");
    /**
     * CheckBox taytteelle tonnikala
     */
    private final CheckBox chkTonnikala = new CheckBox("Tonnikala");
    /**
     * CheckBox taytteelle valkosipuli
     */
    private final CheckBox chkValkoSipuli = new CheckBox("Valkosipuli");
    /**
     * CheckBox taytteelle jauheliha
     */
    private final CheckBox chkJauheliha = new CheckBox("Jauheliha");
    /**
     * CheckBox taytteelle herkkusienet
     */
    private final CheckBox chkSienet = new CheckBox("Herkkusienet");
    /**
     * CheckBox taytteelle aurajuusto
     */
    private final CheckBox chkAuraJuusto = new CheckBox("Aurajuusto");
    /**
     * CheckBox taytteelle kana
     */
    private final CheckBox chkKana = new CheckBox("Kana");
    /**
     * CheckBox pizzan pakkaamiselle kotipakettiin
     */
    private final CheckBox chkKotipaketti = new CheckBox("Kotipakettiin?");
    /**
     * ArrayList pizza-olioiden kirjoittamiseksi tiedostoon ja lukemiseksi
     * tiedostosta
     */
    private ArrayList<KokoPizza> pizzatKirjoitus = new ArrayList<>();
    /**
     * Teksti kirjoitetun pizzan nimen nayttamiseksi ruudulla
     */
    private final Text pizzanNimi = new Text("");
    /**
     * Teksti kuittiin lisattavalle paivamaaratiedolle
     */
    private final Text txtKuitinPvm = new Text("");
    /**
     * Merkkijono tiedostoNimi, johon tiedot talletetaan/luetaan
     */
    private final String tiedostoNimi = "pizzat.dat";
    /**
     * Teksti ilmoitusasioille, esim. tieto tiedostokirjoittamisen onnistumisesta
     */
    private final Text ilmoitusteksti = new Text("");
    /**
     * Text kuitin tietojen kokoamiseen
     */
    private final Text txtKuitti = new Text("");
    /**
     * Tekstialue mm. tervetuloa-tekstin ja kuitin nayttamiseen naytolla
     */
    private final TextArea textAlue = new TextArea("");

    /**
     * Ohjelmaikkunan kaynnistys ja lukuisien eri elementtien maarittelyt
     * @param alkuIkkuna vaihdettu suomenkielinen nimi
     *
     */
    @Override
    public void start(Stage alkuIkkuna)  {

        // Luodaan ensimmäinen pizza
        luotuPizza = new KokoPizza();

        // Maaritetaan paneeleja
        StackPane taustaKuvalle = new StackPane();
        BorderPane taustaPaneeli = new BorderPane();
        GridPane nappulaPaneeli = new GridPane();
        HBox keskiPaneeli = new HBox();
        VBox valitutTuotteet = new VBox(15);
        VBox paneeliCheckboxeille = new VBox(20);
        //StackPane kuvaPaneeli = new StackPane();

        // Maaritetaan alimmaisena nakyva taustakuva
        Image kuva1 = new Image("file:pizzaBackground.jpg"); //haetaan kuva
        ImageView kuvanaytto1 = new ImageView(kuva1);
        kuvanaytto1.setFitWidth(1000);
        kuvanaytto1.setFitHeight(600);

        // Maaritetaan paneelien asetuksia
        nappulaPaneeli.setAlignment(Pos.CENTER_LEFT);
        nappulaPaneeli.setPadding(new Insets(0,20,10,3));
        nappulaPaneeli.setHgap(5);
        taustaPaneeli.setPadding(new Insets(20,40,0,250));
        taustaPaneeli.setAlignment(txtOtsikko, Pos.TOP_CENTER);
        keskiPaneeli.setPadding(new Insets(20,20,0,120));
        VBox paneeliKuitille = new VBox();
        paneeliKuitille.setPadding(new Insets(25,5,0,0));
        paneeliCheckboxeille.setPadding(new Insets(5, 50, 5, 5));
        valitutTuotteet.setPadding(new Insets(5,0,0,0));

        // Maaritetaan painikkeiden leveys
        btTyhjaa.setPrefWidth(90);
        btLueTiedot.setPrefWidth(150);
        btLopeta.setPrefWidth(90);
        btLaskeHinta.setPrefWidth(90);
        btTalletaTiedot.setPrefWidth(150);

        // Maaritetaan hiiritoiminto: jos textAlueella tekstia, niin naytetaan
        // oheinen teksti, muuten ei mitaan.
        paneeliKuitille.setOnMouseEntered(e ->{
            if (!textAlue.getText().equals("")){
            ilmoitusteksti.setText("Tässä kuitti. Kiitos käynnistä!");}

        });
        paneeliKuitille.setOnMouseExited(e -> ilmoitusteksti.setText(""));

        // Maaritetaan teksteja ja labeleita:
        Text txtPohja = new Text("");
        txtPohja.setFill(Color.WHITE);
        Text txtTaytteet = new Text("");
        txtTaytteet.setFill(Color.WHITE);
        Text txtHinta = new Text("");
        txtHinta.setFill(Color.WHITE);
        String tervetuloa = ("""
                        Tervetuloa
                      Pizzamaattiin!
                **************************
                Voit luoda erilaisia pizzoja, nimetä ne, tallentaa tiedostoon ja tulostaa tiedot.

                ********* Enjoy! *********""");
        textAlue.setFont(Font.font("Lucida Calligraphy", EXTRA_BOLD,12));
        textAlue.setWrapText(true);
        textAlue.setText(tervetuloa);
        textAlue.setMaxWidth(180);
        textAlue.setMinHeight(300);
        txtKuitti.setFill(Color.WHITE);
        txtOtsikko.setFont(Font.font ("Lucida Calligraphy", 40));
        txtOtsikko.setFill(Color.WHITE);
        lbKuvanTekija.setStyle("-fx-text-fill: white");
        lbKuvanTekija.setPadding(new Insets(5,5,5,0));
        Text maara = new Text("Valmistat pizzaa nro: "+ Pizza.getPizzojenLkm());
        maara.setFill(Color.WHITE);
        lbValitseTaytteet.setStyle("-fx-text-fill: white");
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

        // Maaritetaan ComboBox pohjan valitsemiselle
        ComboBox<String> cbo = new ComboBox<>();
        cbo.getItems().addAll("Vehnä","Ruis","Gluteeniton");
        cbo.setValue("Valitse pohja"); // alkuteksti

        // Maaritetaan tapahtumat pohjan valitsemisen jalkeen
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
        // Luetaan pizzan nimi tekstikentasta
        // ja asetetaan se olion nimeksi.
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
        tfPizzannimi.setOnMouseClicked(e -> tfPizzannimi.clear());

        // Maaritellaan painikkeille toimintoja

        // Toiminnot, kun painetaan "Lopeta"- painiketta
        btLopeta.setOnAction(e -> {
            luotuPizza.setNimi(tfPizzannimi.getText());
            lisaaTaytteet();
            pizzatKirjoitus.add(luotuPizza);
            talletaTiedosto();
            exit(0);});
        // Toiminnot, kun painetaan "Talleta & Luo uusi pizza"- painiketta
        btTalletaTiedot.setOnAction(e -> {
            luotuPizza.setNimi(tfPizzannimi.getText());
            lisaaTaytteet();
            pizzatKirjoitus.add(luotuPizza);
            luotuPizza = new KokoPizza();
            cbo.setValue("Valitse pohja");
            txtPohja.setText("");
            pizzanNimi.setText("");
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
            txtKuitti.setText("");
            maara.setText("Valmistat pizzaa nro: "+ Pizza.getPizzojenLkm());
            talletaTiedosto();
            textAlue.setFont(Font.font("Times New Roman,12"));
            textAlue.setWrapText(true);
            ilmoitusteksti.setText("Tadaa! Tiedot tallennettu.");
        });
        // Toiminnot, kun painetaan "Tyhjaa tiedot"- painiketta
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
                    textAlue.setText("");
                }
        );
        // Toiminnot, kun painetaan "Lue & tulosta tiedot"- painiketta
        btLueTiedot.setOnAction(e -> lueTiedosto());
        // Toiminnot, kun painetaan "Laske hinta"- painiketta
        btLaskeHinta.setOnAction(e -> {
            luotuPizza.tayteLista.clear();
            txtPohja.setText(luotuPizza.getPohja());
            ilmoitusteksti.setText("");
            lisaaTaytteet();
            txtTaytteet.setText("");
            txtTaytteet.setText(luotuPizza.getTaytteet());
            txtHinta.setText("Hinta: " + String.format("%.2f",luotuPizza.laskeHinta()));

        });

        // Maaritellaan eri paneeleihin tulevat elementit
        valitutTuotteet.getChildren().addAll(tfPizzannimi,chkKotipaketti,
                lbPohja,txtPohja,lbNimi,pizzanNimi,lbValitutTaytteet,txtTaytteet,txtHinta);
        paneeliCheckboxeille.getChildren().addAll(cbo, lbValitseTaytteet, chkAnanas,chkTomaatti,
                chkKatkarapu, chkKinkku, chkTonnikala,chkJauheliha, chkSienet,chkAuraJuusto,
                chkKana,chkValkoSipuli);
        paneeliKuitille.getChildren().addAll(textAlue, maara, ilmoitusteksti);
        keskiPaneeli.getChildren().addAll(paneeliCheckboxeille, valitutTuotteet);
        nappulaPaneeli.add(lbKuvanTekija,0,1);
        nappulaPaneeli.add(btLaskeHinta,1,0);
        nappulaPaneeli.add(btTyhjaa,1,1);
        nappulaPaneeli.add(btTalletaTiedot, 2,0);
        nappulaPaneeli.add(btLueTiedot,2,1);
        nappulaPaneeli.add(btLopeta,3,1);

        // Asetetaan taustapaneeliin muut paneelit/teksti
        taustaPaneeli.setTop(txtOtsikko);
        taustaPaneeli.setBottom(nappulaPaneeli);
        taustaPaneeli.setCenter(keskiPaneeli);
        taustaPaneeli.setRight(paneeliKuitille);

        // Asetetaan alimmaiseksi kuva ja sen paalle taustapaneli, jossa
        // muut paneelit
        taustaKuvalle.getChildren().addAll(kuvanaytto1, taustaPaneeli);

        // Sijoitetaan paneeli kehykseen
        Scene kehys = new Scene(taustaKuvalle,1000   ,600);
        alkuIkkuna.setResizable(false);                                // Ikkunan koko ei saa muuttaa
        alkuIkkuna.setTitle("Tervetuloa herkuttelemaan Pizzamaatilla!"); // Otsikko ikkunalle
        alkuIkkuna.setScene(kehys);                                     // Sijoitetaan kehys ikkunaan
        alkuIkkuna.show();                                              // Naytetaan ikkuna


    }

    /**
     * Ohjelma kaynnistyy talla metodilla
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metodi lisaa valitut taytteet olion taytteet-listalle
     */
    private void lisaaTaytteet(){

        if (chkAnanas.isSelected()) {
            luotuPizza.tayteLista.add("Ananas");
        }

        if (chkTomaatti.isSelected()) {
            luotuPizza.tayteLista.add("Tomaatti");
        }

        if (chkKatkarapu.isSelected()) {
            luotuPizza.tayteLista.add("Katkarapu");
        }

        if (chkKinkku.isSelected()) {
            luotuPizza.tayteLista.add("Kinkku");
        }

        if (chkTonnikala.isSelected()) {
            luotuPizza.tayteLista.add("Tonnikala");
        }

        if (chkValkoSipuli.isSelected()) {
            luotuPizza.tayteLista.add("Valkosipuli");
        }

        if (chkJauheliha.isSelected()) {
            luotuPizza.tayteLista.add("Jauheliha");
        }

        if (chkSienet.isSelected()) {
            luotuPizza.tayteLista.add("Herkkusieni");
        }

        if (chkKana.isSelected()){
            luotuPizza.tayteLista.add("Kana");
        }

        if (chkAuraJuusto.isSelected()) {
            luotuPizza.tayteLista.add("Aurajuusto");
        }

        if (chkKotipaketti.isSelected()){
            luotuPizza.setKotiPaketti(true);
        }
    }

    /**
     *  Metodi oliotaulukon tallentamiseen tiedostoon
     *  Sisaltaa poikkeustenkasittelyt.
     */
    private void talletaTiedosto(){

        try (ObjectOutputStream kirjoitusTiedosto= new ObjectOutputStream(
                new FileOutputStream(tiedostoNimi))) {
             {
                kirjoitusTiedosto.writeObject(pizzatKirjoitus);
             }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.out.println("Virhettä pukkaa.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ilmoitusteksti.setText("Tiedot tallennettu");
    }

    /**
     * Metodi lukee tiedot tiedostosta ja tulostaa naytolle & konsolille
     * Sisaltaa poikkeustenkasittelyt
     */
    private void lueTiedosto() {
        pizzatKirjoitus.clear();
        Date pvm = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String txtPvm = dateFormat.format(pvm);

        try (ObjectInputStream lueTiedosto= new ObjectInputStream(
                new FileInputStream(tiedostoNimi))) {
            {
                pizzatKirjoitus = (ArrayList<KokoPizza>) lueTiedosto.readObject();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Tiedostoa ei löytynyt, mutta ei haittaa! Voit jatkaa pizzojen tekoa!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ff){
            ff.printStackTrace();
            System.out.println("Luokkavirhe.");
        }

        String tarkistaNimi;
        String tarkistaPohja;
        String takeAway;
        String tarkistaTaytteet;
        String kuitinTeksti = ("Kuitti: ");

        textAlue.setFont(Font.font("Times New Roman",12));
        textAlue.setWrapText(true);
        for (KokoPizza luettuOlio : pizzatKirjoitus){
            if (luettuOlio.getNimi() == null || "Syötä nimi pizzalle".equals(luettuOlio.getNimi()))
                tarkistaNimi = "Ei valintaa";
            else
                tarkistaNimi = luettuOlio.getNimi();

            if (luettuOlio.getPohja() == null)
                tarkistaPohja = "Ei arvoa";
            else
                tarkistaPohja = luettuOlio.getPohja();
            if (!luettuOlio.getKotiPaketti())
                takeAway = "Ei";
            else takeAway = "Kyllä";

            if ("".equals(luettuOlio.getTaytteet()))
                tarkistaTaytteet = "Ei täytteitä";
            else tarkistaTaytteet = "\n" + luettuOlio.getTaytteet();

            kuitinTeksti += ("\n---- " + txtPvm + " ----") +
            "\nPizzan nimi: "+ tarkistaNimi +
                    "\nKotipaketti: " + takeAway +
                    "\nPohja: " + tarkistaPohja +
                    "\nTäytteet: " + tarkistaTaytteet +
                    "\nHinta: " + String.format("%.2f",luettuOlio.laskeHinta()) + " €";
            //tulostetaan konsoliin samat tiedot
            textAlue.setWrapText(true);
            textAlue.setEditable(false);
            textAlue.setText(kuitinTeksti);
            luettuOlio.tulostaKuitti();
            ilmoitusteksti.setText("Tiedot luettu ja tulostettu.");

        }

    }
}
