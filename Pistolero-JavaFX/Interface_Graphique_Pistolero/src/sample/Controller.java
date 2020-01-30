package sample;

import ClassObjet.PairCommande;
import ClassObjet.Pistolero;
import ClassObjet.Touche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextField balle;

    @FXML
    private TextField pseudo;

    @FXML
    private AnchorPane start;

    public List<PairCommande> listeTouche;
    public double vitesseDemons;
    public double vitessePistolero;

    public boolean premiere_partie = true;

    public Controller(){
        initConfigDefault();
    }

    public void initConfigDefault(){
        if(premiere_partie) {
            vitesseDemons = 1;
            vitessePistolero = 10;
            listeTouche = new ArrayList<>();
            listeTouche.add(new PairCommande(Touche.AVANCER,KeyCode.Z));
            listeTouche.add(new PairCommande(Touche.RECULER,KeyCode.S));
            listeTouche.add(new PairCommande(Touche.GAUCHE,KeyCode.Q));
            listeTouche.add(new PairCommande(Touche.DROITE,KeyCode.D));
            listeTouche.add(new PairCommande(Touche.TIRER,KeyCode.V));
            listeTouche.add(new PairCommande(Touche.PAUSE,KeyCode.P));
        }
    }

    public void submit(ActionEvent actionEvent) throws Exception {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("Arene.fxml"));
        load.load();
        Arene arene = load.getController();
        AnchorPane panel = load.getRoot();
        Scene s = new Scene(panel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(s);
        stage.show();
        premiere_partie = false;
        arene.initChamps(pseudo.getText(),balle.getText(),listeTouche,vitesseDemons,vitessePistolero);
    }

    public void touche(ActionEvent actionEvent) throws Exception{
        Stage primaryStage = new Stage();
        Commande.ToucheActualMap(listeTouche);
        Parent root = FXMLLoader.load(getClass().getResource("Commande.fxml"));
        primaryStage.setTitle("Projet Interface Graphique");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Commande.initCommande(primaryStage,null,this);
    }

    public void slider(ActionEvent actionEvent) throws Exception {
        Stage primaryStage = new Stage();
        Vitesse.initVitesse(vitesseDemons,vitessePistolero);
        Parent root = FXMLLoader.load(getClass().getResource("Vitesse.fxml"));
        primaryStage.setTitle("Projet Interface Graphique");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Vitesse.setStage(primaryStage,null,this);
    }

    public void initTouche(List<PairCommande> assign){
        listeTouche = assign;
    }

    public void setVitesse(double vd, double vp){
        this.vitesseDemons = vd;
        this.vitessePistolero = vp;
    }
}
