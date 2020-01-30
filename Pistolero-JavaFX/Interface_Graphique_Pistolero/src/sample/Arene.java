package sample;

import ClassObjet.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arene {

    public static AnimationTim ani;
    public String pseudo;
    public int NBballes;
    public int NBdemons;
    public int demonShoot;
    public Pistolero pist;
    public ArrayList<Demon> demons;
    public ArrayList<Mur> murs;
    public ArrayList<Balle> balles;
    public double width;
    public double height;
    public double vitesseDemons = 1/10;
    public double vitessePistolero = 1;
    public List<PairCommande> listeTouche;
    public double[] axeY;
    public ArrayList<Double> initAxeY;
    static boolean start = false;


    @FXML
    AnchorPane arene;

    @FXML
    Label nbBalleLabel;

    @FXML
    Label nbdemonsLabel;

    @FXML
    Label score;

    public Arene() {
    }


    public void initChamps(String p, String b, List<PairCommande> assign, double vd, double vp) {
        Stage s = (Stage) arene.getScene().getWindow();
        s.setResizable(false);
        this.pseudo = p;
        this.NBdemons = (int)(Math.random() * 50)+10;//Integer.parseInt(d);
        this.NBballes = Integer.parseInt(b);
        this.demons = new ArrayList<Demon>();
        this.murs = new ArrayList<Mur>();
        this.width = arene.getWidth();
        this.height = arene.getHeight();
        this.balles = new ArrayList<>();
        listeTouche = assign;
        this.vitesseDemons = vd;
        this.vitessePistolero = vp;
        axeY = new double[]{ height / 1.4, height / 2, height / 4};// new double[]{height, height / 1.4, height / 2, height / 4, 0};
        if (NBballes < 0)
            this.nbBalleLabel.setText("INFINI");
        else
            this.nbBalleLabel.setText(NBballes + "");
        nbdemonsLabel.setText(NBdemons+"");
        score.setText("0");
        initPistolero();
        initMur();
        initDemon();
        start();
    }


    public void initPistolero() {
        pist = new Pistolero(width / 6, height / 2, vitessePistolero);
        arene.getChildren().addAll(pist);
    }


    public void initMur() {
        Random r = new Random();
        int choix = r.nextInt((3 - 1) + 1) + 1;
        if (choix == 1) {
            murs.add(new Mur(width / 1.75, height / 1.25));
            murs.add(new Mur(width / 1.75, height / 2));
            murs.add(new Mur(width / 1.75, height / 5));
        } else if (choix == 2) {
            murs.add(new Mur(width / 1.5, height / 6.5));
            murs.add(new Mur(width / 1.5, height / 2.5));
            murs.add(new Mur(width / 3, height / 2));
            murs.add(new Mur(width / 3, height / 1.3));
        } else {
            murs.add(new Mur(width / 1.5, height / 1.5));
            murs.add(new Mur(width / 1.5, height / 5));
            murs.add(new Mur(width / 3, height / 5));
            murs.add(new Mur(width / 3, height / 1.5));
        }
        arene.getChildren().addAll(murs);


    }

    public void initDemon() {
        Demon demonCourant;
        boolean collision;
        int choix;
        int sex;
        Random r = new Random();
        int demonOK = 0;
        int nbDemonVague = 0;
        double x = width+100;
        while(demonOK < NBdemons) {
            nbDemonVague = (int) (Math.random() * (axeY.length+1));
            System.out.println(nbDemonVague);
            if(NBdemons - demonOK < axeY.length+1){
                nbDemonVague = NBdemons - demonOK;
            }
            for (int i = 0; i < nbDemonVague; i++) {
                collision = false;
                sex = (int) (Math.random() * 2);
                choix = (int) (Math.random() * (axeY.length));
                demonCourant = new Demon(x, axeY[choix], vitesseDemons, sex);
                for (int j = 0; j < demons.size(); j++) { //Collision avec les demons
                    if (demonCourant.collision(demons.get(j))) {
                        i--;
                        collision = true;
                        break;
                    }
                }
                if (!collision)
                    demons.add(demonCourant);
            }
            demonOK += nbDemonVague;
            x += 200;
        }
        arene.getChildren().addAll(demons);
    }

    public void initEnfant(Circle c){
        boolean collision = false;
        Demon demonCourant = new Demon();
        demonCourant = new Demon(width+100, height/2, vitesseDemons, 2);
        for (int j = 0; j < demons.size(); j++) { //Collision avec les demons
            if (demonCourant.collision(demons.get(j))) {
                collision = true;
                break;
            }
        }
        if (!collision) {
            demons.add(demonCourant);
            arene.getChildren().add(demonCourant);
            NBdemons++;
            nbdemonsLabel.setText(demons.size() + "");
            amination("file:src/sample/enfant.gif", c,300);
        }
    }

    public void start() {

        ani = new AnimationTim() { //Animation Timer redéfini pour prendre en charge le debut et la pause
            public void handle(long time) {

                boolean balle_supp = false;

                for (int i = 0; i < balles.size(); i++) {

                    for (int j = 0; j < murs.size(); j++) {
                        if (balles.get(i).collision(murs.get(j))) {
                            balles.get(i).setVisible(false);
                            balles.remove(i);
                            balle_supp = true;
                            break;
                        }
                    }
                    if (balle_supp) {
                        break;
                    }

                    for (int k = 0; k < demons.size(); k++) {
                        if (balles.get(i).collision(demons.get(k))) {
                            balles.get(i).setVisible(false);
                            demons.get(k).setVisible(false);
                            final Balle balleMortelle = balles.get(i);
                            balles.remove(i);
                            demons.remove(k);
                            demonShoot++;
                            NBdemons--;
                            balle_supp = true;
                            amination("file:src/sample/explosion.gif", balleMortelle,1000);
                            break;
                        }
                    }
                    if (balle_supp) {
                        break;
                    }

                    if (balles.get(i).getCenterX() == width) {
                        balles.remove(i);
                        balles.get(i).setVisible(false);
                        break;
                    }

                    double dx = balles.get(i).getDx() * balles.get(i).getSpeed();
                    double dy = balles.get(i).getDy() * balles.get(i).getSpeed();
                    double posX = balles.get(i).getCenterX(), posY = balles.get(i).getCenterY();
                    double nPosX = posX + dx, nPosY = posY + dy;
                    balles.get(i).setCenterX(nPosX);
                    balles.get(i).setCenterY(nPosY);
                }
                int enfant = 0;
                //Faire bouger le demon
                for (int i = 0; i < demons.size(); i++) {
                    Random rand = new Random();
                    boolean suppr = false;
                    int randomNum = rand.nextInt((10000 - 1) + 1) + 1;
                    if ((randomNum == 999 || demons.get(i).getNbCollisions()==5) && demons.get(i).getCenterX()-demons.get(i).getRadius() <= width) {
                        demons.get(i).stepDemon();
                        demons.get(i).setNbCollisions(0);
                    }
                    double dx = demons.get(i).getDx() * demons.get(i).getSpeed();
                    double dy = demons.get(i).getDy() * demons.get(i).getSpeed();
                    double posX = demons.get(i).getCenterX();
                    double posY = demons.get(i).getCenterY();
                    double nPosX = posX + dx, nPosY = posY + dy;
                    demons.get(i).setCenterX(nPosX);
                    demons.get(i).setCenterY(nPosY);

                    //Collisions avec les murs
                    for (int j = 0; j < murs.size(); j++) {
                        if (demons.get(i).collision(murs.get(j))) {
                            demons.get(i).setNbCollisions(demons.get(i).getNbCollisions() + 1);
                            if (demons.get(i).getCenterX() >= murs.get(j).getX() && demons.get(i).getCenterX() <= murs.get(j).getX() + murs.get(j).getWidth())
                                demons.get(i).setDy(-1);
                            else
                                demons.get(i).setDx(demons.get(i).getDx() * -1);
                            break;
                        }
                    }
                    //Collisions avec les demons

                    for (int j = 0; j < demons.size(); j++) {
                        if (demons.get(i) != demons.get(j) && demons.get(i).collisionDemon(demons.get(j)) && demons.get(i).getCenterX()-demons.get(i).getRadius() <= width) { //PAS SUR DE LA CONDITION
                            demons.get(i).setDx(demons.get(i).getDx()*-1);
                            if(demons.get(i).getSex() == demons.get(j).getSex()){
                                amination("file:src/sample/explosion.gif", demons.get(j),300);
                                demons.get(j).setVisible(false);
                                demons.remove(j);
                                NBdemons--;
                                suppr = true;
                            }else {
                                if(enfant==0 && (demons.get(i).getSex()<2 && demons.get(j).getSex()<2) ) {
                                    initEnfant(demons.get(i));
                                }
                                NBdemons++;
                                if (demons.get(i).getCenterY() >= demons.get(j).getCenterY()) {
                                    demons.get(i).setDy(1);
                                    demons.get(j).setDy(-1);
                                } else {
                                    demons.get(i).setDy(-1);
                                    demons.get(j).setDy(1);
                                }
                            }
                            break;
                        }
                    }
                    if(suppr)
                        break;

                    //Collisions Demons avec les bords du terrain de jeu
                    if (demons.get(i).getCenterY() + demons.get(i).getRadius() > height) { //BORD BAS
                        demons.get(i).setCenterY(height - demons.get(i).getRadius() - 1);
                        demons.get(i).setDy(demons.get(i).getDy() * -1);
                    }
                    if (demons.get(i).getCenterY() - demons.get(i).getRadius() < 0) { //BORD HAUT
                        demons.get(i).setCenterY(demons.get(i).getRadius() + 1);
                        demons.get(i).setDy(demons.get(i).getDy() * -1);
                    }
                    if (demons.get(i).getCenterX() - demons.get(i).getRadius() < 0) { //BORD GAUCHE
                        demons.get(i).setVisible(false);
                        demons.remove(i);
                        NBdemons--;
                        break;
                    }
                    if (demons.get(i).getCenterX() + demons.get(i).getRadius() > width  && demons.get(i).getDx() == 1) {//BORD DROIT
                        demons.get(i).setNbCollisions(demons.get(i).getNbCollisions() + 1);
                        demons.get(i).setDx(demons.get(i).getDx() * -1);
                        demons.get(i).setDy(0);
                    }

                    //Collision Pistolero
                    if (demons.get(i).collision(pist)) {
                        //PERDU AUTRE ECRAN
                        ani.stop();
                        try{
                            Thread.sleep(1000);
                            Stage primaryStage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                            primaryStage.setTitle("Projet Interface Graphique");
                            primaryStage.setScene(new Scene(root));
                            primaryStage.show();
                            Stage stage = (Stage) arene.getScene().getWindow();
                            stage.close();
                        }catch(Exception e){}
                    }
                }

                if (demons.size() == 0) {
                    //gagne AUTRE ECRAN
                    ani.stop();
                    try{
                        Stage primaryStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("Win.fxml"));
                        primaryStage.setTitle("Projet Interface Graphique");
                        primaryStage.setScene(new Scene(root));
                        primaryStage.show();
                        Stage stage = (Stage) arene.getScene().getWindow();
                        stage.close();
                    }catch(IOException e){}
                }
                nbdemonsLabel.setText(demons.size()+"");
                score.setText(demonShoot+"");
            }
        };

        arene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                for (int i = 0; i < listeTouche.size(); i++) {
                    if (listeTouche.get(i).getValue() == event.getCode()) {
                        switch (listeTouche.get(i).getKey()) {
                            case AVANCER:
                                if (start)
                                avancerPistolero();
                                break;
                            case RECULER:
                                if (start)
                                reculerPistolero();
                                break;
                            case PAUSE:
                                if (start) {
                                    ani.stop();
                                    start = false;
                                } else {
                                    ani.start();
                                    start = true;
                                }
                                break;
                            case TIRER:
                                if (start)
                                tirer();
                                break;
                            case DROITE:
                                if (start)
                                droitePistolero();
                                break;
                            case GAUCHE:
                                if (start)
                                gauchePistolero();
                                break;
                        }
                    }
                }
            }
        });

        arene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (start)
                    tirer();
            }
        });
    }

    public void setVitesse(double vd, double vp) {
        this.vitesseDemons = vd;
        this.vitessePistolero = vp;
        System.out.println(this.vitesseDemons + " " + this.vitessePistolero);
        for (int i = 0; i < demons.size(); i++) {
            demons.get(i).setSpeed(vitesseDemons);
        }
        ani.start();
    }

    public void initTouche(List<PairCommande> assign) {
        listeTouche = assign;
        ani.start();
    }

    public void ouvreVitesse(ActionEvent actionEvent) throws Exception {
        ani.stop();
        Stage primaryStage = new Stage();
        Vitesse.initVitesse(vitesseDemons, vitessePistolero);
        Parent root = FXMLLoader.load(getClass().getResource("Vitesse.fxml"));
        primaryStage.setTitle("Projet Interface Graphique");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Vitesse.setStage(primaryStage, this, null);
    }

    public void ouvreCommande(ActionEvent actionEvent) throws Exception {
        ani.stop();
        Stage primaryStage = new Stage();
        Commande.ToucheActualMap(listeTouche);
        Parent root = FXMLLoader.load(getClass().getResource("Commande.fxml"));
        primaryStage.setTitle("Projet Interface Graphique");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Commande.initCommande(primaryStage, this, null);
    }


    public void tirer() {
        if (NBballes > 0 || NBballes <= -1) {
            balles.add(new Balle(pist.getCenterX(), pist.getCenterY()));
            arene.getChildren().add(balles.get(balles.size() - 1));
            NBballes--;

            if (NBballes < 0)
                this.nbBalleLabel.setText("INFINI");
            else
                this.nbBalleLabel.setText(NBballes + "");
        }
    }

    public void avancerPistolero() {
        if (pist.getCenterY() - pist.getRadius() - pist.getRadius() / 2 <= 0) {
            pist.setCenterY(pist.getRadius());
        } else {
            double dy = pist.getDy() + 1 * vitessePistolero;
            double posY = pist.getCenterY();
            double nPosY = posY - dy;
            pist.setCenterY(nPosY);
            for (int j = 0; j < murs.size(); j++) {
                if (pist.collision(murs.get(j))) {
                    pist.setCenterY(murs.get(j).getY() + murs.get(j).getHeight() + pist.getRadius() + 1);
                    break;
                }
            }
        }
    }

    public void reculerPistolero() {
        if (pist.getCenterY() + pist.getRadius() + pist.getRadius() / 2 > height) {
            pist.setCenterY(height - pist.getRadius());
        } else {
            double dy = pist.getDy() + 1 * vitessePistolero;
            double posY = pist.getCenterY();
            double nPosY = posY + dy;
            pist.setCenterY(nPosY);
            for (int j = 0; j < murs.size(); j++) {
                if (pist.collision(murs.get(j))) {
                    pist.setCenterY(murs.get(j).getY() - pist.getRadius() - 1);
                    break;
                }
            }
        }
    }

    public void gauchePistolero() {
        if (pist.getCenterX() - pist.getRadius() - pist.getRadius() / 2 < 0) {
            pist.setCenterX(pist.getRadius());
        } else {
            double dx = pist.getDx() + 1 * vitessePistolero;
            double posX = pist.getCenterX();
            double nPosX = posX - dx;
            pist.setCenterX(nPosX);
            for (int j = 0; j < murs.size(); j++) {
                if (pist.collision(murs.get(j))) {
                    pist.setCenterX(murs.get(j).getX() + murs.get(j).getWidth() + pist.getRadius() + 1);
                    break;
                }
            }
        }
    }

    public void droitePistolero() {
        if (pist.getCenterX() + pist.getRadius() + pist.getRadius() / 2 > width / 5) {
            pist.setCenterX(width / 5 - pist.getRadius());
        } else {
            double dx = pist.getDx() + 1 * vitessePistolero;
            double posX = pist.getCenterX();
            double nPosX = posX + dx;
            pist.setCenterX(nPosX);
            for (int j = 0; j < murs.size(); j++) {
                if (pist.collision(murs.get(j))) {
                    pist.setCenterX(murs.get(j).getX() - pist.getRadius() - 1);
                    break;
                }
            }
        }
    }

    public void save(ActionEvent actionEvent) throws IOException{
        ani.stop();
        Outils.saveArene(pist,murs,demons,NBballes,vitesseDemons ,vitessePistolero ,listeTouche);
        ani.start();
    }

    public void restore(ActionEvent actionEvent) throws IOException{
        ani.stop();
        arene.getChildren().removeAll(demons);
        arene.getChildren().removeAll(murs);
        arene.getChildren().removeAll(pist);
        Outils.saveArene(this);
        arene.getChildren().addAll(demons);
        arene.getChildren().addAll(murs);
        arene.getChildren().addAll(pist);
        start = true;
        ani.start();
    }

    public void amination(String s, Circle c,int time){
        ImageView imageView1 = new ImageView();
        Image image = new Image(s);
        imageView1.setImage(image);
        imageView1.setX(c.getCenterX());
        imageView1.setY(c.getCenterY());
        imageView1.setFitHeight(50);
        imageView1.setFitWidth(50);
        imageView1.setPreserveRatio(true);
        arene.getChildren().add(imageView1);
        Runnable task = () -> {
            try {
                Thread.sleep(time);
            } catch (Exception e) {
            }
            Platform.runLater(() -> {
                try {
                    arene.getChildren().remove(imageView1);
                } catch (Exception e) {
                }

            });
        };
        new Thread(task).start();
    }
}
