package dao;

import models.Person;

import java.sql.ResultSet;

public interface ICommonDAO<T> {
    int add(Person person);
    ResultSet getAll();
    T getById(int id);
    void remove(T t);
}
