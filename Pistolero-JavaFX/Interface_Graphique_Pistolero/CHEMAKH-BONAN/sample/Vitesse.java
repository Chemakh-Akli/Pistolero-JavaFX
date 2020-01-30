package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class Vitesse {

    @FXML
    Slider sliderDemon;

    @FXML
    Slider sliderPistolero;

    public static double vdemon;
    public static double vpist;

    public static Stage primaryStage;
    public static Arene arene;
    public static Controller controller;


    public Vitesse(){
        initSlider();
    }


    public void initSlider(){
        sliderDemon = new Slider();
        sliderPistolero = new Slider();
    }

    public static void setStage(Stage pr, Arene ar, Controller c) {
        primaryStage = pr;
        arene = ar;
        controller = c;
    }

    @FXML
    public void initialize(){
        sliderDemon.setValue(vdemon);
        sliderPistolero.setValue(vpist);
    }

    public void validerSlider(ActionEvent actionEvent) {
        System.out.println(sliderDemon.getValue());
        System.out.println(sliderPistolero.getValue());
        if(controller==null)
            arene.setVitesse(sliderDemon.getValue(),sliderPistolero.getValue());
        else
            controller.setVitesse(sliderDemon.getValue(),sliderPistolero.getValue());
        primaryStage.close();
    }

    public static void initVitesse(double vd, double vp){
        vdemon = vd;
        vpist = vp;
    }


}
