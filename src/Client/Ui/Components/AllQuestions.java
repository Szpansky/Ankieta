package Client.Ui.Components;

import Data.Answers;
import Data.Question;
import Data.Questions;

import javax.swing.*;
import java.util.ArrayList;

public class AllQuestions extends JPanel {
    ArrayList<QuestionComponent> questionComponents;


    public AllQuestions(Questions questions) {
        questionComponents = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (Question question :
                questions) {
            questionComponents.add(new QuestionComponent(question));
        }

        for (QuestionComponent questionCompontent :
                questionComponents) {
            add(questionCompontent);
        }
    }


    public Answers getAnswers() {
        Answers answers = new Answers();
        for (QuestionComponent questionCompontent :
                questionComponents) {
            answers.add(questionCompontent.getAnswer());
        }
        return answers;
    }

}
