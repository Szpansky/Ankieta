package Client.Ui.Components;

import Data.Statistic;
import Data.Statistics;

import javax.swing.*;
import java.util.ArrayList;

public class AllStatistics extends JPanel {
    ArrayList<StatisticComponent> statisticComponents;


    public AllStatistics(Statistics statistics) {
        statisticComponents = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (Statistic statistic :
                statistics) {
            statisticComponents.add(new StatisticComponent(statistic));
        }

        for (StatisticComponent statisticComponent :
                statisticComponents) {
            add(statisticComponent);
        }
    }

}
