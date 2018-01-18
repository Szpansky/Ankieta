package Client.Ui.Panels;

import Client.config.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main extends BasePanel {
    JButton startQuestionnaire = new JButton("Nowa ankieta");
    JButton showAllStats = new JButton("Pokaż wszystkie statystyki");


    public Main(Client client) {
        super(client);
        setVisible(true);
        setLayout(new FlowLayout());
        add(startQuestionnaire);
        add(showAllStats);
        onStartClick();
        onShowAllStatsClick();
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

            }
        });
    }
}
