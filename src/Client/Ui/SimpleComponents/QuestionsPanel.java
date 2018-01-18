package Client.Ui.SimpleComponents;

import Data.Answers;
import Data.Question;
import Data.Questions;

import javax.swing.*;
import java.util.ArrayList;

public class QuestionsPanel extends JPanel {

    ArrayList<QuestionComponent> questionComponents = new ArrayList<>();

    public QuestionsPanel(Questions questions) {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        for (Question question :
                questions) {
            questionComponents.add(new QuestionComponent(question));

        }

        for (QuestionComponent questionCompontent :
                questionComponents) {
            add(questionCompontent);
        }
    }

    public Answers getAnswers(){
        Answers answers = new Answers();
        for (QuestionComponent questionCompontent :
                questionComponents) {
            answers.add(questionCompontent.getAnswer());
        }
        return answers;
    }

}
