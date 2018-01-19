package Data;

import java.io.Serializable;

public class Statistic implements Serializable {

    public String id;
    public String text;
    public Integer countOfA;
    public Integer countOfB;
    public Integer countOfC;
    public Integer countOfD;
    public Integer countOfEmpty;
    public Integer countOfAll;

    public Statistic(String id, String text, Integer countOfA, Integer countOfB, Integer countOfC, Integer countOfD, Integer countOfEmpty, Integer countOfAll ) {
        this.id = id;
        this.text = text;
        this.countOfA = countOfA;
        this.countOfB = countOfB;
        this.countOfC = countOfC;
        this.countOfD = countOfD;
        this.countOfEmpty = countOfEmpty;
        this.countOfAll = countOfAll;
    }


}