package Client.Ui.Panels;

import Client.config.Client;

import javax.swing.*;
import java.awt.event.*;


public class Main extends BasePanel {
    JButton startQuestionnaire;
    JButton showAllStats;
    JButton exitButton;


    public Main(Client client) {
        super(client);

        startQuestionnaire = new JButton("Nowa ankieta");
        showAllStats = new JButton("Pokaż wszystkie statystyki");
        exitButton = new JButton("Wyjście");

        addToLayout(startQuestionnaire,1,1,0,0);
        addSpaceToLayout(1,1,0,1);
        addToLayout(showAllStats,1,1,0,2);
        addSpaceToLayout(1,1,0,3);
        addToLayout(exitButton,1,1,0,4);
        onStartClick();
        onShowAllStatsClick();
        onExitClick();
    }


    private void onStartClick() {
        startQuestionnaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.super.removeAll();
                Main.super.add(new Questionnaire(client));
                Main.super.updateUI();
            }
        });
    }


    private void onShowAllStatsClick() {
        showAllStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.super.removeAll();
                Main.super.add(new StatisticsPanel(client));
                Main.super.updateUI();
            }
        });
    }

    private void onExitClick(){
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
