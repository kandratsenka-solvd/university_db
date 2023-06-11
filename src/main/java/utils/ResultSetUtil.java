package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetUtil {

    public static List<String> getFirstRecord(ResultSet resultSet) {
        ResultSetMetaData resultSetMetaData;
        try {
            resultSetMetaData = resultSet.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            List<String> resultValues = new ArrayList<>();
            if (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    resultValues.add(resultSet.getString(i));
                }
            }
            return resultValues;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
