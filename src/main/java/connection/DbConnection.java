package connection;

import utils.FileManagerUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DbConnection {
    private static Connection connection;

    protected static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(Objects.requireNonNull(FileManagerUtil
                            .getValue("config.json", "url")),
                    FileManagerUtil.getValue("credentials.json", "username"),
                    FileManagerUtil.getValue("credentials.json", "password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    protected static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        connection = null;
    }
}