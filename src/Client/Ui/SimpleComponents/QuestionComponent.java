package Client.Ui.SimpleComponents;

import Data.Answer;
import Data.Question;

import javax.swing.*;


public class QuestionComponent extends JPanel {

    JTextArea questionTextArea = new JTextArea();
    JCheckBox answerACheckBox = new JCheckBox();
    JCheckBox answerBCheckBox = new JCheckBox();
    JCheckBox answerCCheckBox = new JCheckBox();
    JCheckBox answerDCheckBox = new JCheckBox();

    String[] questionSplit;

    Question question;

    public QuestionComponent(Question question) {
        super();

        this.question = question;

        questionSplit = question.text.split("\n");

        add(questionTextArea);
        questionTextArea.setText(questionSplit[0]);

        add(answerACheckBox);
        add(answerBCheckBox);
        add(answerCCheckBox);
        add(answerDCheckBox);

        answerACheckBox.setText(questionSplit[1]);
        answerBCheckBox.setText(questionSplit[2]);
        answerCCheckBox.setText(questionSplit[3]);
        answerDCheckBox.setText(questionSplit[4]);



    }

    public Answer getAnswer(){
        String answer = "";

        if(answerACheckBox.isSelected()) answer = answer +'a';
        if(answerBCheckBox.isSelected()) answer = answer +'b';
        if(answerCCheckBox.isSelected()) answer = answer +'c';
        if(answerDCheckBox.isSelected()) answer = answer +'d';

        return new Answer(question.id,answer);
    }


}
