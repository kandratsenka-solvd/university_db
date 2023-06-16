package tests;

import connection.ConnectionPool;
import org.testng.annotations.AfterTest;

public class BaseTest {

    @AfterTest
    public void tearDown() {
        ConnectionPool.getInstance().closeAllConnections();
    }
}
