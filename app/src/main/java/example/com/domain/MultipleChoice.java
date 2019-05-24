package example.com.domain;

import java.util.ArrayList;
import java.util.Collections;

public class MultipleChoice extends Question
{
    private String choice1, choice2, choice3;

    public MultipleChoice(String question, String choice1, String choice2, String choice3, String resource)
    {
        super(question, choice1, QuestionTypes.MULTIPLE_CHOICE, resource);
        setChoice1(choice1);
        setChoice2(choice2);
        setChoice3(choice3);
    }

    public MultipleChoice(String question, String choice1, String choice2, String choice3)
    {
        super(question, choice1, QuestionTypes.MULTIPLE_CHOICE);
        setChoice1(choice1);
        setChoice2(choice2);
        setChoice3(choice3);
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public ArrayList<String> getChoicesInList()
    {
        ArrayList<String> choiceList = new ArrayList<>();
        choiceList.add(choice1);
        choiceList.add(choice2);
        choiceList.add(choice3);
        Collections.shuffle(choiceList);
        return choiceList;
    }

}
