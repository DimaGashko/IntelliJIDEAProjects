package learn.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Connection connection;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test_db",
                    "root", "");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
