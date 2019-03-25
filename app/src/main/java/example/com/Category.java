package example.com;

public class Category {
    private String name;
    private String choices[][];
    private String CorrectAnswers[];
    private String questions[];

    public Category (String categoryName){
        setName(categoryName);
    }


    public Category (String categoryName, String questions[], String choices[][], String CorrectAnswers[]){
        setName(categoryName);
        setQuestions(questions);
        setChoices(choices);
        setCorrectAnswers(CorrectAnswers);
    }

    private void setQuestions(String questions[]) {
        this.questions = questions;
    }

    private void setCorrectAnswers(String correctAnswers[]) {
        this.CorrectAnswers = correctAnswers;
    }

    private void setChoices(String [][] choices) {
        this.choices = choices;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public String getQuestionWithIndex(int index){
        return this.questions[index];
    }
    public String [][] getChoices(){
        return this.choices;
    }

    public String getCorrectAnswerWithIndex(int index) {
        return this.CorrectAnswers[index];
    }
    public int getNumberOfQuestions(){
        return this.questions.length;
    }
}
