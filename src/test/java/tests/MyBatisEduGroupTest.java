package tests;

import connection.ConnectionPool;
import connection.DbSqlSession;
import mappers.IEduGroupMapper;
import models.EduGroup;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.List;

public class MyBatisEduGroupTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection = ConnectionPool.getInstance().getConnection();

    @Test
    public void testEduGroup() {
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        IEduGroupMapper iEduGroupMapper = sqlSession.getMapper(IEduGroupMapper.class);
        List<EduGroup> eduGroupList = iEduGroupMapper.getAll();
        LOGGER.info(eduGroupList.get(0));

    }
}
