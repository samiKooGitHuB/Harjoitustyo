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
 * nimi ja tallentaa seka lukea tietoja tiedostosta. Tietoja voi myos
 * poistaa pizzan nimella.
 * @author samikoti
 * @version 2.0
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
     * Painike tietojen tallentamiselle taulukosta tiedostoon
     */
    private final Button btTalletaTiedot = new Button("Talleta & Luo uusi pizza");
    /**
     * Painike tietojen lukemiselle taulukkoon tiedostosta
     */
    private final Button btLueTiedot = new Button("Lue & Tulosta tiedot");
    /**
     * Painike naytolla olevien tietojen tyhjaamiseen
     */
    private final Button btTyhjaa = new Button("Tyhjää tiedot");
    /**
     * Painike pizzan poistamiseksi taulukosta
     */
    private final Button btPoista = new Button("Poista");
    /**
     * Otsikko valitulle pizzapohjalle
     */
    private final Label lbPohja = new Label("Valittu pohja:" );
    /**
     * Otsikko annetulle pizzan nimelle
     */
    private final Label lbNimi = new Label("Nimi: ");
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
     * Pizzan nimen tekstikentta
     */
    private final TextField tfPizzannimi = new TextField("Syötä nimi pizzalle");
    /**
     * Poistettavan pizzan nimen tekstikentta
     */
    private final TextField tfPoistettava = new TextField("Mikä pizza poistetaan?");
    /**
     * Otsikko ikkunan ylaosaan
     */
    private final Text txtOtsikko = new Text("Pizzamaatti");
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
     * Teksti poistettavalle pizzan nimen näyttämiselle
     */
    private final Text poistoNimi = new Text("");
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
        VBox paneeliKuitille = new VBox(15);


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
        paneeliKuitille.setPadding(new Insets(25,5,0,0));
        paneeliCheckboxeille.setPadding(new Insets(5, 50, 5, 5));
        valitutTuotteet.setPadding(new Insets(5,0,0,0));

        // Maaritetaan painikkeiden leveys
        btTyhjaa.setPrefWidth(90);
        btLueTiedot.setPrefWidth(150);
        btLopeta.setPrefWidth(90);
        btLaskeHinta.setPrefWidth(90);
        btTalletaTiedot.setPrefWidth(150);
        btPoista.setPrefWidth(90);

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
                Voit luoda erilaisia pizzoja, nimetä ne, tallentaa tiedostoon, tulostaa tiedot
                ja myös poistaa luomiasi pizzoja.

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
        poistoNimi.setFill(Color.WHITE);
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
            if (tfPizzannimi.getText().length() > 11){
                tfPizzannimi.setText("Liian pitkä, max 11 merkkiä");
            }
            else {
            String uusiNimi = tfPizzannimi.getText();
            luotuPizza.setNimi(uusiNimi);
            pizzanNimi.setText(luotuPizza.getNimi());
            pizzanNimi.setFill(Color.WHITE);
            }

        });
        tfPoistettava.setOnAction(e ->{
            if (tfPoistettava.getText().length() > 11){
                tfPoistettava.setText("Liian pitkä, max 11 merkkiä");
            } else poistoNimi.setText("Valittu poistettavaksi: " + tfPoistettava.getText());

        });
        tfPizzannimi.setOnMouseClicked(e -> tfPizzannimi.clear());
        tfPoistettava.setOnMouseClicked(e -> tfPoistettava.clear());

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
            if ("Syötä nimi pizzalle".equals(tfPizzannimi.getText())){
                luotuPizza.setNimi("Ei nimeä");
            }
            else luotuPizza.setNimi(tfPizzannimi.getText());
            luotuPizza.tayteLista.clear();
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

            textAlue.setText("Tadaa! Tiedot tallennettu.");
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
                    tfPoistettava.setText("Poista pizza listalta:");
                }
        );
        // Toiminnot, kun painetaan "Lue & tulosta tiedot"- painiketta
        btLueTiedot.setOnAction(e -> lueTiedosto());

        // Toiminnot, kun painetaan "Poista pizza"-painiketta
        // Tähän olioiden poistamisen toteuttavaan
        // koodiin olen katsonut vinkkiä Youtubesta:
        // https://www.youtube.com/watch?v=0viOmE1RqB0
        //
        btPoista.setOnAction(e ->{
            for (int i = pizzatKirjoitus.size() -1; i >= 0; i--)
            {
                if(pizzatKirjoitus.get(i).getNimi().equals(tfPoistettava.getText())
                        || pizzatKirjoitus.get(i).getNimi().equals("Ei nimeä")){
                    pizzatKirjoitus.remove(i);
                    talletaTiedosto();
                    textAlue.setText("Pizza poistettu listalta!");
                }
                else {
                    textAlue.setText("Eipä löytynyt sen nimistä!");}
            }
            poistoNimi.setText("");
            tfPoistettava.setText("Poista pizza listalta:");

        });
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
        valitutTuotteet.getChildren().addAll(tfPizzannimi,chkKotipaketti, maara,
                lbPohja,txtPohja,lbNimi,pizzanNimi,lbValitutTaytteet,txtTaytteet,txtHinta);
        paneeliCheckboxeille.getChildren().addAll(cbo, lbValitseTaytteet, chkAnanas,chkTomaatti,
                chkKatkarapu, chkKinkku, chkTonnikala,chkJauheliha, chkSienet,chkAuraJuusto,
                chkKana,chkValkoSipuli);
        paneeliKuitille.getChildren().addAll(textAlue,ilmoitusteksti,tfPoistettava,poistoNimi,btPoista);
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
     * Metodi lisaa valitut taytteet luotuPizza-olion taytteet-listalle
     */
    private void lisaaTaytteet(){

        if (chkAnanas.isSelected()) {
            luotuPizza.lisaaTayte("Ananas");
        }

        if (chkTomaatti.isSelected()) {
            luotuPizza.lisaaTayte("Tomaatti");
        }

        if (chkKatkarapu.isSelected()) {
            luotuPizza.lisaaTayte("Katkarapu");
        }

        if (chkKinkku.isSelected()) {
            luotuPizza.lisaaTayte("Kinkku");
        }

        if (chkTonnikala.isSelected()) {
            luotuPizza.lisaaTayte("Tonnikala");
        }

        if (chkValkoSipuli.isSelected()) {
            luotuPizza.lisaaTayte("Valkosipuli");
        }

        if (chkJauheliha.isSelected()) {
            luotuPizza.lisaaTayte("Jauheliha");
        }

        if (chkSienet.isSelected()) {
            luotuPizza.lisaaTayte("Herkkusieni");
        }

        if (chkKana.isSelected()){
            luotuPizza.lisaaTayte("Kana");
        }

        if (chkAuraJuusto.isSelected()) {
            luotuPizza.lisaaTayte("Aurajuusto");
        }

        if (chkKotipaketti.isSelected()){
            luotuPizza.setKotiPaketti(true);
        }
    }

    /**
     *  Metodi oliotaulukon tallentamiseen tiedostoon
     *  Sisaltaa poikkeustenkasittelyt:
     *  FileNotFoundException: konsolille ja ohjelmaan tulostetaan tieto virheesta
     *  IOException: heitetään RunTimeException
     */
    private void talletaTiedosto(){

        try (ObjectOutputStream kirjoitusTiedosto= new ObjectOutputStream(
                new FileOutputStream(tiedostoNimi))) {
             {
                kirjoitusTiedosto.writeObject(pizzatKirjoitus);
             }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.out.println("Tiedostoa ei löytynyt.");
            ilmoitusteksti.setText("Tiedostovirhe.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ilmoitusteksti.setText("Tiedot tallennettu");
    }

    /**
     * Metodi lukee tiedot tiedostosta ja tulostaa naytolle ja konsolille
     * Sisaltaa poikkeustenkasittelyt:
     * FileNotFoundException: jos tiedostoa ei loydy (alkuvaihe ohjelmaa ensimmaista kertaa
     * kaytettaessa), niin siita annetaan konsolissa ja ohjelmassa ilmoitus.
     * IOException: heitetään RuntimeException
     * ClassNotFoundException: konsolille ja ohjelmaan annetaan ilmoitus
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
            textAlue.setText("Tiedostoa ei löytynyt, mutta voit jatkaa!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ff){
            ff.printStackTrace();
            System.out.println("Luokkavirhe.");
            ilmoitusteksti.setText("Luokkavirhe tiedostonluvussa.");
        }

        String tarkistaNimi;
        String tarkistaPohja;
        String takeAway;
        String tarkistaTaytteet;
        String kuitinTeksti = ("Pizzamaatin Kuittimaatti: ");

        textAlue.setFont(Font.font("Times New Roman",12));
        textAlue.setWrapText(true);
        if (pizzatKirjoitus.isEmpty()){
            textAlue.setText("******** Tyhjä lista! ********");}

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

            kuitinTeksti += ("\n------ " + txtPvm + " ------") +
            "\nPizzan nimi: "+ tarkistaNimi +
                    "\nKotipaketti: " + takeAway +
                    "\nPohja: " + tarkistaPohja +
                    "\nTäytteet: " + tarkistaTaytteet +
                    "\nHinta: " + String.format("%.2f",luettuOlio.laskeHinta()) + " €" +
                    "\n------- Hyvää ruokahalua ! ------";
            //tulostetaan konsoliin samat tiedot
            textAlue.setWrapText(true);
            textAlue.setEditable(false);
            textAlue.setText(kuitinTeksti);
            luettuOlio.tulostaKuitti();
            ilmoitusteksti.setText("Tiedot luettu ja tulostettu.");

        }

    }

}
