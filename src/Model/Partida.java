package Model;

import java.util.ArrayList;

/**
 *
 * @author 010900
 */
public class Partida {

    Time time1, time2;
    int id, gtime1, gtime2;
    ArrayList<Integer> gols;

    public Partida() {
        gols = new ArrayList();
        time1 = new Time();
        time2 = new Time();
    }

    public ArrayList<Integer> getGols() {
        return gols;
    }

    public void setGols(ArrayList<Integer> gols) {
        this.gols = gols;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public int getGtime1() {
        return gtime1;
    }

    public void setGtime1(int gtime) {
        this.gtime1 = gtime;
    }

    public int getGtime2() {
        return gtime2;
    }

    public void setGtime2(int gtime2) {
        this.gtime2 = gtime2;
    }

    @Override

    public String toString() {
        return "Time de casa: " + time1.getNome() + "\nTime de fora: " + time2.getNome() + "\nPlacar: " + gtime1 + " X " + gtime2;
    }

}
