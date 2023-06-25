package tests;

import connection.ConnectionPool;
import connection.CustomSqlSession;
import org.testng.annotations.AfterTest;

public class BaseTest {

    @AfterTest
    public void tearDown() {
        CustomSqlSession.closeAllSessions();
        ConnectionPool.getInstance().closeAllConnections();
    }
}
