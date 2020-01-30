package ClassObjet;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Pistolero extends Circle {

    double speed;
    String image;
    double dx=0;
    double dy=0;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Pistolero(){}

    public Pistolero(double x, double y, double speed){
        super(x,y,20);
        this.speed = speed;
        this.image = "";
        this.setFill(new ImagePattern(new Image("file:src/sample/pistolero.png")));
    }

    public boolean collision(Shape s){
        return getBoundsInParent().intersects(s.getBoundsInParent());
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
