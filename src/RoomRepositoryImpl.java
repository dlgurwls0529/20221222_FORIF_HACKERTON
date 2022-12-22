import java.sql.*;

public class RoomRepositoryImpl implements RoomRepository {

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
    public boolean save(String roomName) {
        Connection connection = getConnection();

        if(connection != null) {
            try {
                String sql = "INSERT INTO ROOM VALUES(NULL, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, roomName);

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
    public Integer findOneRIdByName(String name) {
        Connection connection = getConnection();
        
        Integer result = null;

        if(connection != null) {
            try {
                String sql = "SELECT rId FROM ROOM WHERE rName = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, name);

                ResultSet resultSet = pstmt.executeQuery();
                result = resultSet.getInt("rId");

                pstmt.close();
                connection.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            
            return result;
        }

        return null;
    }

    @Override
    public String findOneRoomNameById(int id) {
        Connection connection = getConnection();
        String result = null;

        if(connection != null) {
            try {
                String sql = "SELECT rName FROM ROOM WHERE rId = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, id);
                result = pstmt.executeQuery().getString("rName");
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return result;
    }
}
