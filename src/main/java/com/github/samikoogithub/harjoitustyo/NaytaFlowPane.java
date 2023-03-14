package com.github.samikoogithub.harjoitustyo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class NaytaFlowPane extends Application
{
   @Override // korvaa metodin start luokassa Application
   public void start(Stage alkuIkkuna)
   {
      // luodaan paneeli 
      FlowPane paneeli = new FlowPane();
      paneeli.setPadding(new Insets(11, 12, 13, 14));
      paneeli.setHgap(5);
      paneeli.setVgap(5);
      
      // sijoitetaan paneeliin solmuja
      paneeli.getChildren().addAll(new Label("Etunimi:"), 
         new TextField(), new Label("Toinen nimi:"));
      TextField tKentta = new TextField();
      tKentta.setPrefColumnCount(1);
      paneeli.getChildren().addAll(tKentta, new Label("Sukunimi:"), 
         new TextField());   
           
      // sijoitetaan paneeli kehykseen
      Scene kehys = new Scene(paneeli, 200, 250);
      alkuIkkuna. setTitle("N�yt� FlowPane"); // otsikko ikkunalle
      alkuIkkuna.setScene(kehys); // sijoitetaan kehys ikkunaan
      alkuIkkuna.show(); // n�ytet��n ikkuna
   }
   
   public static void main(String [] args)
   {
      Application.launch(args);
   }   
}      
   