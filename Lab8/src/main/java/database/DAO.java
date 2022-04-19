package database;

import java.util.List;

public interface DAO<T> {
    void create(T object);
    List<T> getAll();
    T getByName(String name);
    T getById(int id);
    void deleteEntry(int id);
    void updateEntry(T object);
}
