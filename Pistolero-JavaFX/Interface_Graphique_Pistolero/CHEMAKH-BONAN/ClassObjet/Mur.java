package ClassObjet;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.Arene;

import java.util.ArrayList;

public class Mur extends Rectangle {


    public Mur(double x, double y){
        super(x,y,15,50);
        this.setFill(new ImagePattern(new Image("file:src/sample/wall.png")));

    }

    public boolean collision(Shape s){
        return getBoundsInParent().intersects(s.getBoundsInParent());
    }
}
