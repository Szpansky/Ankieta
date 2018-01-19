package Client.Ui.Components;

import Client.Ui.Panels.BasePanel;
import Data.Answer;
import Data.Question;

import javax.swing.*;
import java.awt.*;

public class QuestionComponent extends BasePanel {

    JTextArea questionTextArea;
    JCheckBox answerACheckBox;
    JCheckBox answerBCheckBox;
    JCheckBox answerCCheckBox;
    JCheckBox answerDCheckBox;

    String[] questionSplit;

    Question question;

    public QuestionComponent(Question question) {
        super();
        this.question = question;

        questionTextArea = new MyJTextArea();
        answerACheckBox = new MyJCheckBox();
        answerBCheckBox = new MyJCheckBox();
        answerCCheckBox = new MyJCheckBox();
        answerDCheckBox = new MyJCheckBox();

        questionSplit = question.text.split("\n");

/*
        add(questionTextArea);
        add(answerACheckBox);
        add(answerBCheckBox);
        add(answerCCheckBox);
        add(answerDCheckBox);*/

        setLayout(new GridBagLayout());

        addToLayout(questionTextArea,4,4,0,0);
        addToLayout(answerACheckBox,1,1,0,1);
        addToLayout(answerBCheckBox,1,1,1,1);
        addToLayout(answerCCheckBox,1,1,2,1);
        addToLayout(answerDCheckBox,1,1,3,1);
        addSpaceToLayout(4,4,0,2);

        questionTextArea.setText(questionSplit[0]);
        answerACheckBox.setText(questionSplit[1]);
        answerBCheckBox.setText(questionSplit[2]);
        answerCCheckBox.setText(questionSplit[3]);
        answerDCheckBox.setText(questionSplit[4]);
    }

    public Answer getAnswer() {
        String answer = "";

        if (answerACheckBox.isSelected()) answer = answer + 'a';
        if (answerBCheckBox.isSelected()) answer = answer + 'b';
        if (answerCCheckBox.isSelected()) answer = answer + 'c';
        if (answerDCheckBox.isSelected()) answer = answer + 'd';

        return new Answer(question.id, answer);
    }
}
