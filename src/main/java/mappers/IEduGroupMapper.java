package mappers;

import models.EduGroup;
import java.util.List;

public interface IEduGroupMapper extends ICustomMapper<EduGroup> {
    @Override
    int add(EduGroup eduGroup);

    @Override
    int getGeneratedKey();

    @Override
    List<EduGroup> getAll();

    @Override
    EduGroup getById(int eduGroupId);

    @Override
    void deleteById(int eduGroupId);
}
