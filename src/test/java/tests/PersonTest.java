package tests;

import connection.ConnectionPool;
import dao.PersonDAO;
import org.testng.annotations.Test;
import utils.PersonUtil;

import java.sql.Connection;
import java.util.concurrent.*;

public class PersonTest {

    @Test
    public void testAddPerson() throws InterruptedException {
        int threadsNumber = 7;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);
        CountDownLatch latch = new CountDownLatch(threadsNumber);
        for (int i = 0; i < threadsNumber; i++) {
            executorService.submit(() -> {
                try {
                    Connection connection = ConnectionPool.getInstance().getConnection();
                    PersonDAO personDAO = new PersonDAO(connection);
                    personDAO.add(PersonUtil.generatePerson());
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();
        ConnectionPool.getInstance().closeAllConnections();
    }
}
