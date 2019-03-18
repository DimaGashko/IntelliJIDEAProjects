package learn.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    Connection connection;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            connect();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test_db",
                "db_user", "qqqqqqqqww");
    }
}
