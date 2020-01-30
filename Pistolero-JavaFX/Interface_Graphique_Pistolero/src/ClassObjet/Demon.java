package ClassObjet;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import javax.swing.text.Segment;
import java.util.Random;

public class Demon extends Circle {

    double speed;
    String image;
    int sex;
    double dx;
    double dy;
    int nbCollisions;

    public Demon(){}
    public Demon(double x, double y, double speed, int sex){
        super(x,y,20);
        this.sex = sex;
        if(sex == 1)
            this.setFill(new ImagePattern(new Image("file:src/sample/pcmanrose.png"))); //FEMME
        else
            if(sex==2)
                this.setFill(new ImagePattern(new Image("file:src/sample/pcmanbleu.png"))); //ENFANT
            else
                this.setFill(new ImagePattern(new Image("file:src/sample/pcman.png"))); //HOMME
        this.image = "";
        this.dx = -1;
        this.dy = 0;
        if(sex < 2){
            this.speed = speed;
        }else{
            this.speed = speed++;
        }
    }


    public boolean collision(Shape s){
        return getBoundsInParent().intersects(s.getBoundsInParent());
    }

    public boolean collisionDemon(Demon d){
        double longueur = Math.sqrt(Math.pow(d.getCenterX()-this.getCenterX(),2)+Math.pow(d.getCenterY()-this.getCenterY(),2));
        return (longueur <= d.getRadius()+this.getRadius());
    }

    public int getNbCollisions() {
        return nbCollisions;
    }

    public void setNbCollisions(int nbCollisions) {
        this.nbCollisions = nbCollisions;
    }

    public void stepDemon(){
        Random rand = new Random();
        int randomNum = rand.nextInt((2 - 1) + 1) + 1;
        if(dy==0)
            if(randomNum == 1)
               setDy(-1);
            else
                setDy(1);
        else
            setDy(dy*-1);
    }


    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setSpeed(double s){
        if(sex < 2){
            this.speed = s;
        }else{
            this.speed = s++;
        }
    }

    public double getSpeed(){
        return this.speed;
    }
}
