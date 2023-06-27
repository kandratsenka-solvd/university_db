package tests;

import connection.ConnectionPool;
import dao.PersonDAO;
import factories.PersonFactory;
import models.Person;
import models.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.PersonUtil;

import java.sql.Connection;
import java.util.concurrent.*;

public class PersonTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testPersonFactory() {
        Person person = PersonFactory.get("person", 0);
        LOGGER.info(person);
        Student student = (Student) PersonFactory.get("student", 0);
        LOGGER.info(student);
        Person lecturer = PersonFactory.get("lecturer", 0);
        LOGGER.info(lecturer.getClass());
    }

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
