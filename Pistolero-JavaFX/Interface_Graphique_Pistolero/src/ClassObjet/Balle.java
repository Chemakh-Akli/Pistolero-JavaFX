package ClassObjet;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Balle extends Circle {

    double dx=0;
    double dy=0;
    double speed = 10;

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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Balle(double x, double y){
        super(x,y,15);
        this.setFill(new ImagePattern(new Image("file:src/sample/blast2.png")));
        this.dx = 1;
        this.dy = 0;
    }
    public boolean collision(Shape s){
        return getBoundsInParent().intersects(s.getBoundsInParent());
    }
}
