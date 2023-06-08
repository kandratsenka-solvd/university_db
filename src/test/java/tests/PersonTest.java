package tests;

import connection.ConnectionPool;
import dao.PersonDAO;
import org.testng.annotations.Test;
import utils.PersonUtil;

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
                    PersonDAO personDAO = new PersonDAO();
                    personDAO.add(PersonUtil.generatePerson());
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

    @Test
    public void testGetAllPerson() {
        PersonDAO personDAO = new PersonDAO();
        personDAO.getAll();
    }
}
