import java.sql.*;

public class JDBCTest {
    public void firstTest() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("org.sqlite.JDBC 를 찾지 못했습니다.");
            e.printStackTrace();
        }

        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\dlgur\\Downloads\\calender_db_v1.db");
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from room");

        while(resultSet.next()) {
            System.out.println(resultSet.getInt("rId") + resultSet.getString("rName"));
        }

        resultSet.close();
        connection.close();
    }
}
