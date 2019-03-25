package example.com;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CategoryLibrary {
    private ArrayList<Category> categories;

    public CategoryLibrary(){
        categories = new ArrayList<>();
        intializeCategoryFlowers();
        initializeCategoryPokemon();
        initializeCategoryYugioh();
    }

    public void addCategoryToCategoryLibrary(Category category){
        this.categories.add(category);
    }

    private void intializeCategoryFlowers() {
        String categoryFlowerName = CategoryEnum.Flowers.toString();
        String categoryFlowerQuestions [] = {
                "Which part of the plant holds it in the soil?",
                "This part of the plant absorbs energy from the sun.",
                "This part of the plant  attracts bees, butterflies and hummingbirtds.",
                "The __________ holds the plant upright."
        };
        String categoryFlowerChoices[][] = {
                {"Roots", "Stem", "Flower"},
                {"Fruit", "Leaves", "Seeds"},
                {"Bark", "Flower", "Roots"},
                {"Flower", "Leaves", "Stem"}
        };

        String categoryFlowerRightAnswers[] = { "Roots", "Leaves", "Flower", "Stem"};
        Category categoryFlowers = new Category(categoryFlowerName, categoryFlowerQuestions,
                categoryFlowerChoices, categoryFlowerRightAnswers);
        categories.add(categoryFlowers);

    }
    private void initializeCategoryPokemon() {
        String categoryPokemonName = CategoryEnum.Pokemon.toString();
        String categoryPokemonQuestions [] = {
                "What is the evolution of Charmamander?",
                "What type of pokemon is Caterpie?",
                "How many evolutions does Bulbasaur have?",
                "What is the starter pokemon of ash?"
        };
        String categoryPokemonChoices[][]= {
                {"Charizard", "Vulpix", "Wartortle"},
                {"Flying", "Ice", "Bug"},
                {"1", "2", "3"},
                {"Pikachu", "Charmander", "Bulbasaur"}
        };

         String categoryPokemonRightAnswers[] = { "Charizard", "Bug", "3", "Pikachu"};

        String mCorrectAnswersPokemon[] = { "Charizard", "Bug", "3", "Pikachu"};
        Category categoryPokemon = new Category(categoryPokemonName, categoryPokemonQuestions,
                categoryPokemonChoices, categoryPokemonRightAnswers);
        categories.add(categoryPokemon);
    }
    private void initializeCategoryYugioh() {
        String categoryYugiohName = CategoryEnum.Yugioh.toString();
        String categoryYugiohQuestions [] = {
                "What is the real name of the forbidden one?",
                "Who is the main character?",
                "What type of game is Yu Gi Oh?",
                "The __________ eyes white dragon"
        };
        String categoryYugiohChoices[][]= {
                {"Exodia", "Abaki", "Acorno"},
                {"Yami Yugi", "Yugi Muto", "Jaden Yuki"},
                {"Card game", "Arcade game", "Dungeon game"},
                {"Green", "Blue", "Black"}
        };

        String categoryYugiohRightAnswers[] = { "Exodia", "Yugi Muto", "Card game", "Blue"};

        String mCorrectAnswersPokemon[] = { "Charizard", "Bug", "3", "Pikachu"};
        Category categoryYugioh = new Category(categoryYugiohName, categoryYugiohQuestions,
                categoryYugiohChoices, categoryYugiohRightAnswers);
        categories.add(categoryYugioh);
    }

    public ArrayList<Category> getCategories(){
        return this.categories;
    }



    public String getQuestionOfCategoryWithIndex(int index, String categoryChoice){
        String question = "";
        boolean found = false;
        for(Category category : categories){
            if(category.getName().equalsIgnoreCase(categoryChoice)) {
                question = category.getQuestionWithIndex(index);
                found = true;
                break;
            }
        }
        if (!found)
            throw new IllegalArgumentException("the question for category " + categoryChoice + "with index " + index +"was not found");
        else
            return question;
    }

    public String getChoice1OfCategoryWithIndex(int index, String categoryChoice){
        String choice = "";
        boolean found = false;
        for(Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryChoice)) {
                choice = category.getChoices()[index][0];
                found = true;
                break;
            }
        }
        if(!found)
            throw new IllegalArgumentException("the first choice for category " + categoryChoice + "with index " + index +"was not found");
        else
            return choice;
    }


    public String getChoice2OfCategoryWithIndex(int index, String categoryChoice){
            String choice = "";
            boolean found = false;
            for(Category category : categories) {
                if (category.getName().equalsIgnoreCase(categoryChoice)) {
                    choice = category.getChoices()[index][1];
                    found = true;
                    break;
                }
            }
                if(!found)
                    throw new IllegalArgumentException("the second choice for category " + categoryChoice + "with index " + index +"was not found");
                else
                    return choice;
    }

    public String getChoice3OfCategoryWithIndex(int index, String categoryChoice){
        String choice = "";
        boolean found = false;
        for(Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryChoice)) {
                choice = category.getChoices()[index][2];
                found = true;
                break;
            }
        }
            if(!found)
                throw new IllegalArgumentException("the third choice for category " + categoryChoice + "with index " + index +"was not found");
            else
                return choice;
    }

    public String getCorrectAnswerOfCategoryWithIndex(int index, String categoryChoice){
        String correctAnswer = "";
        boolean found = false;
        for(Category category : categories){
            if(category.getName().equalsIgnoreCase(categoryChoice)){
                correctAnswer = category.getCorrectAnswerWithIndex(index);
                found = true;
                break;
            }
        }
        if(!found)
            throw new IllegalArgumentException("the correct answer for category " + categoryChoice + "with index " + index +"was not found");
        else
            return correctAnswer;
    }

    public int getNumberOfQuestionsOfCategory(String categoryChoice) {
        int numberOfQuestions = 0;
        boolean found = false;
        for(Category category : categories){
            if(category.getName().equalsIgnoreCase(categoryChoice)){
                numberOfQuestions = category.getNumberOfQuestions();
                found = true;
                break;
            }
        }
        if(!found)
            throw new IllegalArgumentException("the number of questions for category " + categoryChoice +"was not found");
        else
            return numberOfQuestions;
    }
}
