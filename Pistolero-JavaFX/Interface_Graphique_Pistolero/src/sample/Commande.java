package sample;

import ClassObjet.PairCommande;
import ClassObjet.Touche;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class Commande {

    @FXML
    AnchorPane commande_pane;

    @FXML
    Button select_avancer ;
    KeyCode KeyAvancer;

    @FXML
    Button select_droite ;
    KeyCode KeyDroite;

    @FXML
    Button select_gauche ;
    KeyCode KeyGauche;

    @FXML
    Button select_reculer ;
    KeyCode KeyReculer;

    @FXML
    Button select_tirer ;
    KeyCode KeyTirer;

    @FXML
    Button select_pause ;
    KeyCode KeyPause;

    public static Arene arene;
    public static Controller controller;
    public static Stage PrimaryStage;

    public static List<PairCommande> etat_touche;

    public Commande(){
        initButton();
    }

    public static void ToucheActualMap( List<PairCommande> map){
        etat_touche = map;
        for(int i = 0 ; i < etat_touche.size() ; i++){
            System.out.println(etat_touche.get(i).getValue().toString());
        }
    }

    public void initButton(){
        select_avancer = new Button();
        select_reculer = new Button();
        select_gauche = new Button();
        select_droite = new Button();
        select_tirer = new Button();
        select_pause = new Button();
    }

    @FXML
    public void initialize() {
        for (int i = 0; i < etat_touche.size(); i++) {
            switch (etat_touche.get(i).getKey()) {
                case AVANCER:
                    select_avancer.setText(etat_touche.get(i).getValue().toString());
                    break;
                case RECULER:
                    select_reculer.setText(etat_touche.get(i).getValue().toString());
                    break;
                case PAUSE:
                    select_pause.setText(etat_touche.get(i).getValue().toString());
                    break;
                case TIRER:
                    select_tirer.setText(etat_touche.get(i).getValue().toString());
                    break;
                case DROITE:
                    select_droite.setText(etat_touche.get(i).getValue().toString());
                    break;
                case GAUCHE:
                    select_gauche.setText(etat_touche.get(i).getValue().toString());
                    break;
            }
        }
    }







    public void avancer(ActionEvent actionEvent) {
        commande_pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyAvancer = event.getCode();
                System.out.println(KeyAvancer);
                select_avancer.setText(event.getText());
                for (int i = 0; i < etat_touche.size(); i++) {
                    switch (etat_touche.get(i).getKey()) {
                        case AVANCER:
                            etat_touche.get(i).setValue(KeyAvancer);
                            break;
                        case RECULER:
                            break;
                        case PAUSE:
                            break;
                        case TIRER:
                            break;
                        case DROITE:
                            break;
                        case GAUCHE:
                            break;
                    }
                }
            }
        });
    }

    public void reculer (ActionEvent actionEvent){
        commande_pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyReculer= event.getCode();
                System.out.println(KeyReculer);
                select_reculer.setText(event.getText());
                for (int i = 0; i < etat_touche.size(); i++) {
                    switch (etat_touche.get(i).getKey()) {
                        case AVANCER:
                            break;
                        case RECULER:
                            etat_touche.get(i).setValue(KeyReculer);
                            break;
                        case PAUSE:
                            break;
                        case TIRER:
                            break;
                        case DROITE:
                            break;
                        case GAUCHE:
                            break;
                    }
                }
            }
        });
    }

    public void gauche(ActionEvent actionEvent) {
        commande_pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyGauche= event.getCode();
                System.out.println(KeyGauche);
                select_gauche.setText(event.getText());
                for (int i = 0; i < etat_touche.size(); i++) {
                    switch (etat_touche.get(i).getKey()) {
                        case AVANCER:
                            break;
                        case RECULER:
                            break;
                        case PAUSE:
                            break;
                        case TIRER:
                            break;
                        case DROITE:
                            break;
                        case GAUCHE:
                            etat_touche.get(i).setValue(KeyGauche);
                            break;
                    }
                }
            }
        });
    }

    public void droite(ActionEvent actionEvent) {
        commande_pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyDroite= event.getCode();
                System.out.println(KeyDroite);
                select_droite.setText(event.getText());
                for (int i = 0; i < etat_touche.size(); i++) {
                    switch (etat_touche.get(i).getKey()) {
                        case AVANCER:
                            break;
                        case RECULER:
                            break;
                        case PAUSE:
                            break;
                        case TIRER:
                            break;
                        case DROITE:
                            etat_touche.get(i).setValue(KeyDroite);
                            break;
                        case GAUCHE:
                            break;
                    }
                }
            }
        });
    }

    public void tirer(ActionEvent actionEvent) {
        commande_pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyTirer= event.getCode();
                System.out.println(KeyTirer);
                select_tirer.setText(event.getText());
                for (int i = 0; i < etat_touche.size(); i++) {
                    switch (etat_touche.get(i).getKey()) {
                        case AVANCER:
                            break;
                        case RECULER:
                            break;
                        case PAUSE:
                            break;
                        case TIRER:
                            etat_touche.get(i).setValue(KeyTirer);
                            break;
                        case DROITE:
                            break;
                        case GAUCHE:
                            break;
                    }
                }
            }
        });
    }

    public void pause(ActionEvent actionEvent) {
        commande_pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyPause= event.getCode();
                System.out.println(KeyPause);
                select_pause.setText(event.getText());
                for (int i = 0; i < etat_touche.size(); i++) {
                    switch (etat_touche.get(i).getKey()) {
                        case AVANCER:
                            break;
                        case RECULER:
                            break;
                        case PAUSE:
                            etat_touche.get(i).setValue(KeyPause);
                            break;
                        case TIRER:
                            break;
                        case DROITE:
                            break;
                        case GAUCHE:
                            break;
                    }
                }
            }
        });
    }

    public void confirm(ActionEvent actionEvent) {
        if(arene == null)
            controller.initTouche(etat_touche);
        else
            arene.initTouche(etat_touche);
        PrimaryStage.close();
    }

    public static void initCommande(Stage s, Arene a, Controller c){
        PrimaryStage = s;
        arene = a;
        controller = c;
    }


}