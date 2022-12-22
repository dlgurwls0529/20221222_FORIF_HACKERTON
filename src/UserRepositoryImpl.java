import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\dlgur\\Downloads\\calender_db_v1.db");
            return connection;
        } catch (Exception e) {
            System.out.println("DBUtil.getConnection() : " + e.toString());
        }

        return null;
    }

    @Override
    public boolean save(String userName) {
        Connection connection = getConnection();

        if(connection != null) {
            try {
                String sql = "INSERT INTO USER VALUES(NULL, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, userName);

                int r = pstmt.executeUpdate();
                pstmt.close();
                connection.close();

                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return false;
    }

    @Override
    public Integer findOneUIdByName(String name) {
        Connection connection = getConnection();
        Integer result = null;

        if(connection != null) {
            try {
                String sql = "SELECT uId FROM USER WHERE username = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, name);
                result = pstmt.executeQuery().getInt("uId");

                pstmt.close();
                connection.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return result;
    }

    @Override
    public String findOneUserNameById(int id) {
        Connection connection = getConnection();
        String result = null;

        if(connection != null) {
            try{
                String sql = "SELECT username FROM USER WHERE uId = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, id);
                result = pstmt.executeQuery().getString("username");
                pstmt.close();
                connection.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return result;
    }

    @Override
    public List<String> findManyByUIdList(List<Integer> l) {
        StringBuilder sql = new StringBuilder("SELECT username FROM USER WHERE uId IN (");
        sql.append(l.get(0));

        List<String> result = new ArrayList<>();

        for(int i = 1; i < l.size(); i++) {
            sql.append(", ").append(l.get(i));
        }

        sql.append(")");

        Connection connection = getConnection();

        if(connection != null) {
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql.toString());

                while(resultSet.next()) {
                    result.add(resultSet.getString("username"));
                }

                resultSet.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            result = null;
        }

        return result;
    }
}
