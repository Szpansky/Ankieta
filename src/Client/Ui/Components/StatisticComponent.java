package Client.Ui.Components;

import Client.Ui.Panels.BasePanel;
import Data.Statistic;

import javax.swing.*;
import java.awt.*;

public class StatisticComponent extends BasePanel {

    JTextArea questionTextArea;
    JTextArea countOfATextArea;
    JTextArea countOfBTextArea;
    JTextArea countOfCTextArea;
    JTextArea countOfDTextArea;
    JTextArea countOfEmptyTextArea;
    JTextArea countOfAllTextArea;


    Statistic statistic;

    public StatisticComponent(Statistic statistic) {
        super();
        this.statistic = statistic;


        questionTextArea = new MyJTextArea();
        countOfATextArea = new MyJTextArea();
        countOfBTextArea = new MyJTextArea();
        countOfCTextArea = new MyJTextArea();
        countOfDTextArea = new MyJTextArea();
        countOfEmptyTextArea = new MyJTextArea();
        countOfAllTextArea = new MyJTextArea();

        setLayout(new GridBagLayout());

        addToLayout(questionTextArea, 5, 5, 0, 0);
        addToLayout(countOfATextArea, 1, 1, 0, 1);
        addToLayout(countOfBTextArea, 1, 1, 1, 1);
        addToLayout(countOfCTextArea, 1, 1, 2, 1);
        addToLayout(countOfDTextArea, 1, 1, 3, 1);
        addToLayout(countOfEmptyTextArea, 1, 1, 4, 1);
        addSpaceToLayout(5,5,0,2);
        addToLayout(countOfAllTextArea, 5, 5, 0, 3);
        addSpaceToLayout(5,5,0,4);
        addSpaceToLayout(5,5,0,5);

        Integer percentA = calcPercentage(statistic.countOfA, statistic.countOfAll);
        Integer percentB = calcPercentage(statistic.countOfB, statistic.countOfAll);
        Integer percentC = calcPercentage(statistic.countOfC, statistic.countOfAll);
        Integer percentD = calcPercentage(statistic.countOfD, statistic.countOfAll);
        Integer percentEmpty = calcPercentage(statistic.countOfEmpty, statistic.countOfAll);

        questionTextArea.setText(statistic.text);
        countOfATextArea.setText("a) " + percentA.toString() + "%");
        countOfBTextArea.setText("b) " + percentB.toString() + "%");
        countOfCTextArea.setText("c) " + percentC.toString() + "%");
        countOfDTextArea.setText("d) " + percentD.toString() + "%");
        countOfEmptyTextArea.setText("Brak) " + percentEmpty.toString() + "%");
        countOfAllTextArea.setText("Udzieliło odpowiedzi : " + statistic.countOfAll.toString() + " osób");
    }

    private Integer calcPercentage(Integer countOf, Integer countOfAll) {
        Integer percent;
        try {
            percent = (countOf * 100) / countOfAll;
        } catch (ArithmeticException e) {
            return 0;
        }
        return percent;
    }

}
