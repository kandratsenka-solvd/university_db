package tests;

import connection.ConnectionPool;
import dao.PersonService;
import org.testng.annotations.Test;
import utils.PersonUtil;


import java.util.concurrent.*;

public class ConnectionTest {

    @Test
    public void testAddPerson() throws InterruptedException {
        int threadsNumber = 7;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);
        CountDownLatch latch = new CountDownLatch(threadsNumber);
        for (int i = 0; i < threadsNumber; i++) {
            executorService.submit(() -> {
                try {
                    PersonService personService = new PersonService();
                    personService.add(PersonUtil.generateStudent());
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
