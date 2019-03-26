package example.com.domain;

import java.util.ArrayList;
import java.util.Collections;

public class Question
{
    private String question;
    private String choice1, choice2, choice3;
    private String answer;

    public Question(String question, String choice1, String choice2, String choice3)
    {
        setQuestion(question);
        setChoice1(choice1);
        setChoice2(choice2);
        setChoice3(choice3);
        setAnswer(choice1);
    }

    public String getQuestion() {
        return question;
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

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public void setAnswer(String answer) {
        this.answer = answer;
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
