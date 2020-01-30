package ClassObjet;

import javafx.scene.input.KeyCode;


public class PairCommande {
    public Touche key;
    public KeyCode value;


    public PairCommande(Touche a, KeyCode b){
        key = a;
        value = b;
    }

    public Touche getKey(){
        return this.key;
    }
    public KeyCode getValue(){
        return value;
    }
    public void setKey(Touche a){
        key =a ;
    }
    public void setValue(KeyCode a){
        value = a;
    }
}
