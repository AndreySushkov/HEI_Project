import java.sql.*;

public class DBWorker {
    private String host = "jdbc:mysql://localhost/hei_db";
    private String username = "root";
    private String password = "roota";

    private Connection connection;

    public DBWorker() {
        try {
            connection = DriverManager.getConnection(host, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Подключение к базе данных успешно!");
    }

    public Connection getConnection() {return this.connection;}
}
