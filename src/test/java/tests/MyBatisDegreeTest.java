package tests;

import connection.ConnectionPool;
import connection.CustomSqlSession;
import mappers.IDegreeMapper;
import models.DegreeModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.List;

public class MyBatisDegreeTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection = ConnectionPool.getInstance().getConnection();

    @Test
    public void testDegree() {
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IDegreeMapper iDegreeMapper = sqlSession.getMapper(IDegreeMapper.class);
        List<DegreeModel> degreeModelList = iDegreeMapper.getAll();
        LOGGER.info(degreeModelList);
    }
}
