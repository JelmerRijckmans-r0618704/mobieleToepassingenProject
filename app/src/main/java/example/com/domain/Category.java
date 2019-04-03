package example.com.domain;

import java.util.ArrayList;
import java.util.List;

public class Category
{
    private String name;
    private String difficulty;
    private List<Question> questionArrayList = new ArrayList<>();

    public Category(String name, String difficulty)
    {
        setName(name);
        setDifficulty(difficulty);
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }


    public List<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void addQuestion(Question newQuestion)
    {
        questionArrayList.add(newQuestion);
    }


}
