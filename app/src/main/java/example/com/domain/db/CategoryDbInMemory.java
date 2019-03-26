package example.com.domain.db;

import java.util.LinkedHashMap;
import java.util.Map;

import example.com.domain.Category;

public class CategoryDbInMemory implements Database<Category>
{
    private final Map<Integer, Category> categoryMap = new LinkedHashMap<>();
    private static int instanceCounter = 0;

    public CategoryDbInMemory()
    {
        instanceCounter = 0;
    }

    @Override
    public void add(Category category)
    {
        instanceCounter++;
        categoryMap.put(instanceCounter, category);
    }

    @Override
    public void edit(Category updated) {

    }

    @Override
    public void delete(int id)
    {
        categoryMap.remove(id);
    }

    @Override
    public Category get(int id)
    {
        return categoryMap.get(id);
    }

    @Override
    public Map<Integer, Category> getAll() {
        return categoryMap;
    }

    @Override
    public int getLatestId() {
        return instanceCounter;
    }

    @Override
    public String getType() {
        return "MEMORY";
    }
}
