package tests;

import connection.ConnectionPool;
import connection.DbSqlSession;
import org.testng.annotations.AfterTest;

public class BaseTest {

    @AfterTest
    public void tearDown() {
        DbSqlSession.closeAllSessions();
        ConnectionPool.getInstance().closeAllConnections();
    }
}
