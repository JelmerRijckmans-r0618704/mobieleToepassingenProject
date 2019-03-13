package example.com;

public class QuestionLibrary {
    private String mQuestionsFlowers[] = {
      "Which part of the plant holds it in the soil?",
      "This part of the plant absorbs energy from the sun.",
      "This part of the plant  attracts bees, butterflies and hummingbirtds.",
       "The __________ holds the plant upright."
    };

    private String mQuestionsPokemon[] = {
            "What is the evolution of Charmamander?",
            "What type of pokemon is Caterpie?",
            "How many evolutions does Bulbasaur have?",
            "What is the starter pokemon of ash?"
    };

    private String mQuestionsYugioh[] = {
            "What is the real name of the forbidden one?",
            "Who is the main character?",
            "What type of game is Yu Gi Oh?",
            "The __________ eyes white dragon"
    };

    private String mChoicesFlowers[][]= {
        {"Roots", "Stem", "Flower"},
        {"Fruit", "Leaves", "Seeds"},
        {"Bark", "Flower", "Roots"},
        {"Flower", "Leaves", "Stem"}
    };

    private String mChoicesPokemon[][]= {
            {"Charizard", "Vulpix", "Wartortle"},
            {"Flying", "Ice", "Bug"},
            {"1", "2", "3"},
            {"Pikachu", "Charmander", "Bulbasaur"}
    };

    private String mChoicesYugioh[][]= {
            {"Exodia", "Abaki", "Acorno"},
            {"Yami Yugi", "Yugi Muto", "Jaden Yuki"},
            {"Card game", "Arcade game", "Dungeon game"},
            {"Green", "Blue", "Black"}
    };
    private String mCorrectAnswersFlowers[] = { "Roots", "Leaves", "Flower", "Stem"};
    private String mCorrectAnswersPokemon[] = { "Charizard", "Bug", "3", "Pikachu"};
    private String mCorrectAnswersYugioh[] = { "Exodia", "Yugi Muto", "Card game", "Blue"};


    public String getQuestion(int a, String categoryChoice){
        if (categoryChoice.equalsIgnoreCase(Category.Flowers.toString()))
            return   mQuestionsFlowers[a];
        else if (categoryChoice.equalsIgnoreCase(Category.Pokemon.toString()))
            return mQuestionsPokemon[a];
        else
            return mQuestionsYugioh[a];
    }

    public String getChoice1(int a, String categoryChoice){
        if(categoryChoice.equalsIgnoreCase(Category.Flowers.toString()))
             return  mChoicesFlowers[a][0];
        else if (categoryChoice.equalsIgnoreCase(Category.Pokemon.toString()))
            return mChoicesPokemon[a][0];
        else return mChoicesYugioh[a][0];
    }


    public String getChoice2(int a, String categoryChoice){
        if(categoryChoice.equalsIgnoreCase(Category.Flowers.toString()))
            return  mChoicesFlowers[a][1];
        else if (categoryChoice.equalsIgnoreCase(Category.Pokemon.toString()))
            return mChoicesPokemon[a][1];
        else return mChoicesYugioh[a][1];
    }

    public String getChoice3(int a, String categoryChoice){
        if(categoryChoice.equalsIgnoreCase(Category.Flowers.toString()))
            return  mChoicesFlowers[a][2];
        else if (categoryChoice.equalsIgnoreCase(Category.Pokemon.toString()))
            return mChoicesPokemon[a][2];
        else return mChoicesYugioh[a][2];
    }

    public String getCorrectAnswer(int a, String categoryChoice){
        if(categoryChoice.equalsIgnoreCase(Category.Flowers.toString()))
        return  mCorrectAnswersFlowers[a];
        else if (categoryChoice.equalsIgnoreCase(Category.Pokemon.toString()))
            return mCorrectAnswersPokemon[a];
        else return mCorrectAnswersYugioh[a];
    }

    public int getNumberOfQuestions(String categoryChoice) {
        if(categoryChoice.equalsIgnoreCase(Category.Flowers.toString()))
            return mCorrectAnswersFlowers.length;
        else if (categoryChoice.equalsIgnoreCase(Category.Pokemon.toString()))
            return mCorrectAnswersPokemon.length;
        else return mCorrectAnswersYugioh.length;
    }
}
