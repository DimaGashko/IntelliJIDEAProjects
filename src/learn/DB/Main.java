package learn.DB;

import java.sql.*;
import java.util.Properties;

public class Main {
    Connection connection;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            connect();
            System.out.println("Connected!");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM employee");

            while(rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                System.out.println(firstName + " " + lastName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't connect to the Database");
        }
    }

    void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test_db",
                "db_user", "qqqqqqqqww");
    }
}
