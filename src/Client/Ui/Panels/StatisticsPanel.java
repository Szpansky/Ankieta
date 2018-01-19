package Client.Ui.Panels;

import Client.Ui.Components.AllStatistics;
import Client.config.Client;
import Data.Statistics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StatisticsPanel extends BasePanel {
    AllStatistics allStatistics;
    Statistics statistics;
    JButton backButton;

    public StatisticsPanel(Client client) {
        super(client);

        backButton = new JButton("Wstecz");

        statistics = client.getStatistics();

        allStatistics = new AllStatistics(statistics);

        addToLayout(allStatistics,1,1,0,0);
        addToLayout(backButton,1,1,0,1);


        onBackClick();
    }


    private void onBackClick() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatisticsPanel.super.removeAll();
                StatisticsPanel.super.add(new Main(client));
                StatisticsPanel.super.updateUI();
            }
        });
    }
}
