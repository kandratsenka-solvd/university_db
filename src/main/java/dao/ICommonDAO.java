package dao;

import models.Person;
import java.util.List;

public interface ICommonDAO<T> {
    void add(Person person);
    List<T> getAll();
    T getById(int id);
    void remove(T t);
}
