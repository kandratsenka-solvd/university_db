package mappers;

import models.Person;

import java.util.List;

public interface IPersonMapper {
//    @Insert("INSERT INTO person (title_id, full_name, date_of_birth, gender, address, email, phone_number) " +
//            "VALUES (#{titleId}, #{fullName}, #{dateOfBirth}, #{gender}, #{address}, #{email}, #{phoneNumber})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    int add(Person person);
    int getGeneratedKey();

//    @Select("SELECT * FROM person")
    List<Person> getAll();

}

