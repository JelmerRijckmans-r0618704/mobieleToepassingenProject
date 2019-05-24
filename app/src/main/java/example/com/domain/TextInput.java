package example.com.domain;

public class TextInput extends Question
{
    public TextInput(String question, String answer)
    {
        super(question, answer, QuestionTypes.TEXT_INPUT);
    }

    public TextInput(String question, String answer, String resource)
    {
        super(question, answer, QuestionTypes.TEXT_INPUT, resource);
    }
}
