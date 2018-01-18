package Client.Ui.Fragments;

import Client.MainClient;
import Client.Ui.SimpleComponents.QuestionsPanel;
import Client.config.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Questionnaire extends JPanel {
    Client client = new Client();

    JButton sendButton = new JButton("Wyślij");

    QuestionsPanel questionsPanel;

    public Questionnaire(String title) throws HeadlessException {
        client.Connect();
        setVisible(true);
        setLayout(new FlowLayout());
        questionsPanel = new QuestionsPanel(client.getQuestions());
        add(questionsPanel);
        add(sendButton);
        onSendClick();
    }

    private void onSendClick() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendAnswers(questionsPanel.getAnswers());
                Questionnaire.super.removeAll();
                Questionnaire.super.add(new Main());
                Questionnaire.super.updateUI();
            }
        });
    }

}
