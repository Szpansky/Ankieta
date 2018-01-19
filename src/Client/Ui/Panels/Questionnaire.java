package Client.Ui.Panels;

import Client.Ui.Components.AllQuestions;
import Client.config.Client;
import Data.Questions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Questionnaire extends BasePanel {
    JButton sendButton;
    AllQuestions allQuestions;
    Questions questions;
    JButton backButton;

    public Questionnaire(Client client) {
        super(client);

        sendButton = new JButton("Wyślij");
        backButton = new JButton("Wstecz");
        questions = client.getQuestionnaire();

        allQuestions = new AllQuestions(questions);

        addToLayout(allQuestions,2,2,0,0);
        addToLayout(sendButton,1,1,0,1);
        addToLayout(backButton,1,1,1,1);

        onSendClick();
        onBackClick();
    }




    private void onSendClick() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendObject(allQuestions.getAnswers());
                Questionnaire.super.removeAll();
                Questionnaire.super.add(new Main(client));
                Questionnaire.super.updateUI();
            }
        });
    }

    private void onBackClick() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Questionnaire.super.removeAll();
                Questionnaire.super.add(new Main(client));
                Questionnaire.super.updateUI();
            }
        });
    }

}
