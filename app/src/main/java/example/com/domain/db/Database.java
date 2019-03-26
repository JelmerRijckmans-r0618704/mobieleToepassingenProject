package example.com.domain.db;

import java.util.Map;

public interface Database<T>
{
    void add(T object);
    void edit(T updated);
    void delete(int id);
    T get(int id);
    Map<Integer, T> getAll();
    int getLatestId();
    String getType();
}
