package example.com.domain;

import java.util.ArrayList;
import java.util.List;

public class Category
{
    private String name;
    private String difficulty;
    private List<MultipleChoice> multipleChoiceArrayList = new ArrayList<>();

    public Category(String naam, String difficulty)
    {
        setName(naam);
        setDifficulty(difficulty);
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<MultipleChoice> getMultipleChoiceArrayList() {
        return multipleChoiceArrayList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void addQuestion(MultipleChoice newMultipleChoice)
    {
        multipleChoiceArrayList.add(newMultipleChoice);
    }

}
