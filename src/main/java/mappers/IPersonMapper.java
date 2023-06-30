package mappers;

import models.Person;
import org.apache.ibatis.annotations.Param;

public interface IPersonMapper extends ICustomMapper<Person> {

    Person getPersonByFullName(@Param("fullName") String fullName);
    void updateEmailByFullName(@Param("fullName") String fullName, @Param("email") String email);
}

