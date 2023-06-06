package dao;

import models.Person;

import java.sql.Connection;
import java.util.List;

public interface ICommonDAO<T> {
    void add(Person person, Connection connection);
    List<T> getAll();
    T getById(int id);
    void remove(T t);
}
