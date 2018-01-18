package Client.Ui.Fragments;


import Client.MainClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel {
    JButton startQuestionnaire = new JButton("Nowa ankieta");
    JButton showAllStats = new JButton("Pokaż wszystkie statystyki");
    Questionnaire frame;

    public Main() {
        setVisible(true);
        setLayout(new FlowLayout());
        add(startQuestionnaire);
        add(showAllStats);
        onStartClick();
        onShowAllStatsClick();
    }

    private void onStartClick(){
        startQuestionnaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.super.removeAll();
                Main.super.add(new Questionnaire("Ankieta"));
                Main.super.updateUI();
            }
        });
    }

    private void onShowAllStatsClick(){
        showAllStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
