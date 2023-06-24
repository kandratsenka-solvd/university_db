package mappers;

import models.Student;
import java.util.List;

public interface IStudentMapper extends ICustomMapper<Student> {

    @Override
    int add(Student person);

    @Override
    int getGeneratedKey();

    @Override
    List<Student> getAll();

    @Override
    Student getById(int studentId);

    @Override
    void deleteById(int studentId);
}
