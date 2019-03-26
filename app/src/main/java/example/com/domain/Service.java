package example.com.domain;

import java.util.ArrayList;
import java.util.List;

import example.com.domain.db.CategoryDbInMemory;
import example.com.domain.db.Database;

public class Service {
    private Database<Category> categoryDatabase;

    public Service() {
        this.categoryDatabase = new CategoryDbInMemory();

        Category newCat = createCategory("Flowers", "Easy");
        newCat.addQuestion(createQuestion("Which part of the plant holds it in the soil?","Roots", "Stem", "Flower"));
        newCat.addQuestion(createQuestion("This part of the plant absorbs energy from the sun.","Leaves", "Fruit", "Seeds"));
        categoryDatabase.add(newCat);
        Category newCat2 = createCategory("Pokemon", "Medium");
        newCat2.addQuestion(createQuestion( "What is the evolution of Charmamander?","Charizard", "Vulpix", "Wartortle"));
        newCat2.addQuestion(createQuestion("What type of pokemon is Caterpie?","Bug", "Ice", "Flying"));
        categoryDatabase.add(newCat2);
        Category newCat3 = createCategory("Yugioh", "Hard");
        newCat3.addQuestion(createQuestion("What is the real name of the forbidden one?","Exodia", "Abaki", "Acorno"));
        newCat3.addQuestion(createQuestion("Who is the main character?","Yugi Muto", "Yami Yugi", "Jaden Yuki"));
        categoryDatabase.add(newCat3);
    }

    public Database<Category> getCategoryDatabase() {
        return categoryDatabase;
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categoryDatabase.getAll().values());
    }

    public List<String> getAllCategoryNames()
    {
        ArrayList<String> categoryNames = new ArrayList<>();
        List<Category> categories = getAllCategories();
        for (Category category : categories)
        {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }

    public Category getCategory(String categoryName)
    {
        for (Category category: this.getAllCategories())
        {
            if(category.getName().equals(categoryName)) return category;
        }
        return null;
    }

    public Category createCategory(String name, String difficulty)
    {
        return new Category(name, difficulty);
    }

    public void addNewCategory(String name, String difficulty)
    {
        categoryDatabase.add(createCategory(name, difficulty));
    }

    public Question createQuestion(String question, String choice1, String choice2, String choice3)
    {
        return new Question(question, choice1, choice2, choice3);
    }

}