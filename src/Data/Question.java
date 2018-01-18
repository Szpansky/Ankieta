package Data;

import java.io.Serializable;

/**
 * Created by Marcin on 2018-01-16.
 */
public class Question implements Serializable{

    public String id;
    public String text;

    public Question(String id, String text) {
    this.id = id;
    this.text = text;
    }


}
