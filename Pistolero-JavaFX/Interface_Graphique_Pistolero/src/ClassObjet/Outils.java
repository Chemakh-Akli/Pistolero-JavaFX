package ClassObjet;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Arene;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Outils {

    public static void saveArene(Pistolero pist, ArrayList<Mur> murs, ArrayList<Demon> demons, int NBballes, double vitesseDemons , double vitessePistolero , List<PairCommande> listeTouche) throws IOException {
        PrintWriter pr;
        pr =  new PrintWriter(new BufferedWriter
                (new FileWriter("saveArene.txt")));
        pr.println(pist.getCenterX()+";"+pist.getCenterY()+";"+pist.getSpeed());
        pr.println(vitesseDemons);
        pr.println(vitessePistolero);
        pr.println(NBballes);
        pr.println(demons.size());
        pr.println(murs.size());
        for(int i=0;i<demons.size();i++)
            pr.println(demons.get(i).getCenterX()+";"+demons.get(i).getCenterY()+";"+demons.get(i).getDx()+";"+demons.get(i).getDy()+";"+demons.get(i).getSex());
        for(int i=0;i<murs.size();i++)
            pr.println(murs.get(i).getX()+";"+murs.get(i).getY());

        pr.close();
    }

    public static void saveArene(Arene a) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("saveArene.txt"));
        String pistolero = br.readLine();
        String[] pist = pistolero.split(";");
        a.pist = new Pistolero(Double.parseDouble(pist[0]),Double.parseDouble(pist[1]),Double.parseDouble(pist[2]));
        String vitesseDemon = br.readLine();
        a.vitesseDemons = Double.parseDouble(vitesseDemon);
        String vitessePistolero = br.readLine();
        a.vitessePistolero = Double.parseDouble(vitessePistolero);
        String NBballes = br.readLine();
        a.NBballes = Integer.parseInt(NBballes);
        String NBdemons = br.readLine();
        a.NBdemons = Integer.parseInt(NBdemons);
        String NBmurs = br.readLine();
        ArrayList<Demon> demons = new ArrayList<Demon>();
        ArrayList<Mur> murs = new ArrayList<Mur>();
        for(int i=0;i<Integer.parseInt(NBdemons);i++){
            String demon = br.readLine();
            String[] demont = demon.split(";");
            Demon d = new Demon(Double.parseDouble(demont[0]),Double.parseDouble(demont[1]),a.vitesseDemons,Integer.parseInt(demont[4]));
            d.setDx(Double.parseDouble(demont[2]));
            d.setDy(Double.parseDouble(demont[3]));
            demons.add(d);
        }
        a.demons = demons;
        for(int i=0;i<Integer.parseInt(NBmurs);i++){
            String mur = br.readLine();
            String[] murt = mur.split(";");
            Mur m = new Mur(Double.parseDouble(murt[0]),Double.parseDouble(murt[1]));
            murs.add(m);

        }
        a.murs = murs;
        br.close();
    }
}
