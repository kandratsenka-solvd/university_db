package connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.FileManagerUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;


class DbConnection {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String URL = FileManagerUtil.getValue("config.json", "url");
    private static final String USERNAME = FileManagerUtil.getValue("credentials.json", "username");
    private static final String PASSWORD = FileManagerUtil.getValue("credentials.json", "password");

    private DbConnection(){}

    protected static Connection open() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(Objects.requireNonNull(URL), USERNAME, PASSWORD);
            LOGGER.info("Created connection: " + connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    protected static void close(Connection connection) {
        try {
            LOGGER.info("Closing the connection [{}]", connection);
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to close the connection [{}]", connection);
        }
    }
}
