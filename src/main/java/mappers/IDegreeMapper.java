package mappers;

import enums.Degree;
import models.DegreeModel;

public interface IDegreeMapper extends ICustomMapper<DegreeModel> {

    void insert(Degree degree);
}
