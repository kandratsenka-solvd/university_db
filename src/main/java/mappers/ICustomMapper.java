package mappers;

import java.util.List;

public interface ICustomMapper<T> {
    int add(T t);
    int getGeneratedKey();
    List<T> getAll();
    T getById(int id);
    void deleteById(int id);
}
