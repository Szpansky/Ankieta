package Data;

import java.io.Serializable;

public class Statistic implements Serializable {

    public String id;
    public String text;
    public String percentOfA;
    public String percentOfB;
    public String percentOfC;
    public String percentOfD;

    public Statistic(String id, String text, String percentOfA, String percentOfB, String percentOfC, String percentOfD ) {
        this.id = id;
        this.text = text;
        this.percentOfA = percentOfA;
        this.percentOfB = percentOfB;
        this.percentOfC = percentOfC;
        this.percentOfD = percentOfD;
    }


}