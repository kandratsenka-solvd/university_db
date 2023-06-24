package mappers;

import models.Person;
import java.util.List;

public interface IPersonMapper extends ICustomMapper<Person> {

    @Override
    int add(Person person);

    @Override
    int getGeneratedKey();

    @Override
    List<Person> getAll();

    @Override
    Person getById(int personId);

    @Override
    void deleteById(int personId);
}

