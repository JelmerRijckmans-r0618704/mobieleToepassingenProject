package example.com.domain;

public abstract class Question
{
    private String question;
    private String answer;
    private QuestionTypes type;
    private String resource;


    public Question(String question, String answer, QuestionTypes type) {
        setQuestion(question);
        setAnswer(answer);
        setType(type);
    }

    public String getQuestion() {
        return question;
    }

    public String getResource() {
        return resource;
    }

    public String getAnswer() {
        return answer;
    }

    public QuestionTypes getType() {
        return type;
    }

    public String getTypeString() {
        return type.toString();
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setType(QuestionTypes type) {
        this.type = type;
    }

    public void setResource(String resource)
    {
        this.resource = resource;
    }
}
