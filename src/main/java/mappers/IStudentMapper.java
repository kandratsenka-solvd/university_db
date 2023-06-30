package mappers;

import models.Student;
import models.StudentDetails;

import java.util.List;

public interface IStudentMapper extends ICustomMapper<Student> {

    List<StudentDetails> getStudentDetailsList();
}
