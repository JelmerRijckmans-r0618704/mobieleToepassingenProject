package example.com.domain;

public enum QuestionTypes {
    MULTIPLE_CHOICE("Multiple Choice"),
    TEXT_INPUT("Text Input");

    private final String type;

    QuestionTypes(String type)
    {
        this.type = type;
    }
}
