package dao;

import models.Person;

import java.sql.ResultSet;

public interface ICommonDAO<T> {
    void add(Person person);
    ResultSet getAll();
    T getById(int id);
    void remove(T t);
}
