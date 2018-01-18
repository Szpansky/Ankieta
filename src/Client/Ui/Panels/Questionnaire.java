package Client.Ui.Panels;

import Client.Ui.SimpleComponents.QuestionsPanel;
import Client.config.Client;
import Data.Questions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Questionnaire extends BasePanel {
    JButton sendButton = new JButton("Wyślij");
    QuestionsPanel questionsPanel;


    public Questionnaire(Client client) {
        super(client);
        setVisible(true);
        setLayout(new FlowLayout());

        Questions questions = client.getQuestionnaire();

        questionsPanel = new QuestionsPanel(questions);
        add(questionsPanel);
        add(sendButton);
        onSendClick();
    }


    private void onSendClick() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendObject(questionsPanel.getAnswers());
                Questionnaire.super.removeAll();
                Questionnaire.super.add(new Main(client));
                Questionnaire.super.updateUI();
            }
        });
    }

}
