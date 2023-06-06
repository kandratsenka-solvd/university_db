package tests;

import org.testng.annotations.Test;
import connection.DbConnectionUtil;
import utils.DbQueryUtil;

public class UniversityTest {

    @Test
    public void testStudent() {
        DbConnectionUtil.getConnection();
//        DbQueryUtil.addSomeToDB();
//        DbQueryUtil.addPersonToDB();
        DbQueryUtil.addStudentToDB();
        DbConnectionUtil.closeConnection();
    }
}
